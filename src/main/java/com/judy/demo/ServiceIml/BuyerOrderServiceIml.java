package com.judy.demo.ServiceIml;

import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Service.BuyerOrderService;
import com.judy.demo.Service.OrderService;
import com.judy.demo.util.Convert.Order2OrderDTO;
import com.judy.demo.util.Convert.OrderForm2OrderDTOConverter;
import com.judy.demo.util.Form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 19:31 2019/1/16
 */
@Service
@Slf4j
public class BuyerOrderServiceIml implements BuyerOrderService {
    @Autowired
    private OrderService orderService;
    @Override
    public Map<String,Integer> create(OrderForm orderForm) {

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (orderDTO == null) {
            log.error("orderDTO:is null");
        }
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        Map<String,Integer> map= new HashMap<>();
        map.put("orderId",orderDTO1.getOrderId());
        return map;
    }

    @Override
    public List<OrderDTO> findList(String openid, Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> list = orderService.findList(openid, pageRequest);
        return list.getContent();
    }

    @Override
    public OrderDTO detail(String openid, Integer orderId) {
        OrderDTO conver = this.conver(openid, orderId);
        return conver;
    }

    @Override
    public OrderDTO cancel(String openid, Integer orderId) {
        OrderDTO conver = this.conver(openid, orderId);
        if(conver!=null){
            OrderDTO cancel = orderService.cancel(conver);
            return cancel;
        }
        return null;
    }

    public OrderDTO conver(String openid,Integer orderid){
        OrderDTO byId = orderService.findById(orderid);
        if(byId.getBuyerOpenid().equals(openid)){
            return byId;
        }
        return null ;

    }
}
