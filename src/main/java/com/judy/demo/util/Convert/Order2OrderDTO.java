package com.judy.demo.util.Convert;

import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Entity.OrderMasterEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 21:18 2019/1/14
 */
public class Order2OrderDTO{
    public static OrderDTO convert(OrderMasterEntity orderMasterEntity){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterEntity,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMasterEntity> orderMasterEntityList){
        return orderMasterEntityList.stream().map(el->convert(el)).collect(Collectors.toList());
    }
}
