package com.judy.demo.util.Convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Entity.OrderDetailEntity;
import com.judy.demo.util.Form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 19:48 2019/1/16
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    /**
     *
     * @param orderForm 校验
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        List<OrderDetailEntity> orderDetailEntitiesList = new ArrayList<>();
        try {
            //字符串json转换为list
//            orderDetailEntitiesList = gson.fromJson(orderForm.getItem(), new TypeToken<List<OrderDetailEntity>>() {
//            }.getType());
        }catch (Exception e){
            log.error("购物车json转换为list失败,orderForm -{}",orderForm);
        }
        orderDTO.setOrderDetailEntityList(orderDetailEntitiesList);
        return orderDTO;
    }
}
