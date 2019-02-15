package com.judy.demo.Service;

import com.judy.demo.DTO.OrderDTO;
import org.apache.catalina.LifecycleState;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 16:08 2019/1/14
 */
@Service
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);
    //查询单个订单
    OrderDTO findById(Integer orderId);
    //查询所有列表订单
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //取消订单
     OrderDTO cancel(OrderDTO orderDTO);
    //完结订单
     OrderDTO finsh (OrderDTO orderDTO);
    //支持订单
     OrderDTO paid(OrderDTO orderDTO);

     //查询所有列表
    Page<OrderDTO> findList(Pageable pageable);
}
