package com.judy.demo.ServiceIml;

import com.judy.demo.DTO.CartDTO;
import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Entity.OrderDetailEntity;
import com.judy.demo.Entity.OrderMasterEntity;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.ExceptionDemo.SellException;
import com.judy.demo.Repository.OrderDetilRepository;
import com.judy.demo.Repository.OrderMasterRepository;
import com.judy.demo.Service.OrderService;
import com.judy.demo.Service.ProductInfoService;
import com.judy.demo.util.Convert.Order2OrderDTO;
import enums.OrderStatusEnums;
import enums.PayStatusEnums;
import enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 16:27 2019/1/14
 */
@Component
@Slf4j
public class OrderServiceIml implements OrderService{

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetilRepository orderDetilRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private WebSocketIml webSocketIml;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

       Integer Orderid = 2;

        //查询商品和价格
        BigDecimal orderAmount  = new BigDecimal(BigInteger.ONE);
        for (OrderDetailEntity orderDetailEntity : orderDTO.getOrderDetailEntityList()) {
            //查询根据订单详情知道商品信息
            ProductInfoCategoryEntity productInfoCategoryEntity = productInfoService.findById(orderDetailEntity.getProductId());
            if (productInfoCategoryEntity == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            BeanUtils.copyProperties(productInfoCategoryEntity,orderDetailEntity);
            //价格乘数量,然后add
            orderAmount= productInfoCategoryEntity.getProductPrice().multiply(new BigDecimal(orderDetailEntity.getProductQuantity())).add(orderAmount);
            orderDetailEntity.setOrderId(Orderid);

            //保存到商品详情表
            orderDetilRepository.save(orderDetailEntity);
        }

        //保存到订单主表
        OrderMasterEntity orderMasterEntity= new OrderMasterEntity();
        BeanUtils.copyProperties(orderDTO,orderMasterEntity);
        //价格
        orderMasterEntity.setOrderAmount(orderAmount);
        orderMasterEntity.setOrderId(Orderid);
        orderMasterEntity.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMasterEntity.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterRepository.save(orderMasterEntity);
        //扣库存
        List<CartDTO> cartDTOS= orderDTO.getOrderDetailEntityList().stream().map(el->new CartDTO(el.getProductId(),el.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOS);
        webSocketIml.sendMessage("有新的订单");
        return null;
    }
    @Transactional
    @Override
    public OrderDTO findById(Integer orderId) {
        //查询主订单
        Optional<OrderMasterEntity> byId = orderMasterRepository.findById(orderId);
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity();
        if(byId.isPresent()){
             orderMasterEntity = byId.get();
        }else {
            orderMasterEntity = null;
            throw  new SellException( ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单详情
        List<OrderDetailEntity> orderDetailEntities = orderDetilRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailEntities)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //组合
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterEntity,orderDTO);
        orderDTO.setOrderDetailEntityList(orderDetailEntities);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMasterEntity> orderMasterEntityPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> convert = Order2OrderDTO.convert(orderMasterEntityPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(convert,pageable,orderMasterEntityPage.getTotalElements());
        return orderDTOPage;
    }
    @Transactional
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //收下判断是否是取消订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.CANCEL.getCode())){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //修改订单状态
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity();
        BeanUtils.copyProperties(orderDTO,orderMasterEntity);
        orderMasterEntity.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        OrderMasterEntity masterEntity = orderMasterRepository.save(orderMasterEntity);
        if (masterEntity==null){
            log.error("更新订单状态失败,订单信息-{}",masterEntity);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //返回库存
        if(orderDTO.getOrderDetailEntityList()==null){
            log.error("没有商品无需更新");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        List<CartDTO> cartDTOStream = orderDTO.getOrderDetailEntityList().stream().map(el -> new CartDTO(el.getProductId(), el.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOStream);
        return null;
    }
    @Transactional
    @Override
    public OrderDTO finsh(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnums.FINISHED.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity();
        BeanUtils.copyProperties(orderDTO,orderMasterEntity);
        orderMasterRepository.save(orderMasterEntity);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnums.FINISHED.getCode())) {
            log.error("支持订单错误,订单状态不是完成订单,orderDTO-{}",orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态0表示等待支持
        if(!orderDTO.getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("订单支持完成,订单状态不是完成订单,orderDTO-{}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(1);//完成订单
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity();
        BeanUtils.copyProperties(orderDTO,orderMasterEntity);
        OrderMasterEntity   orderMasterEntity1 = orderMasterRepository.save(orderMasterEntity);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMasterEntity> orderMasterEntityPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = Order2OrderDTO.convert(orderMasterEntityPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterEntityPage.getTotalElements());
    }
}
