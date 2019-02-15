package com.judy.demo.ServiceIml;

import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Entity.OrderDetailEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 18:01 2019/1/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImlTest {
    @Autowired
    private OrderServiceIml orderServiceIml;
    private final static String openid="123";


    @Test
    public void create() throws Exception {
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setBuyerAddress("廊坊");
        orderDTO.setBuyerName("辣条");
        orderDTO.setBuyerOpenid("11");
        orderDTO.setBuyerPhone("186335734564");
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        OrderDetailEntity orderDetailEntity1= new OrderDetailEntity();
        orderDetailEntity1.setOrderId(1);
        orderDetailEntity1.setProductId(1);
        orderDetailEntity1.setProductQuantity(1);
        orderDetailEntities.add(orderDetailEntity1);
        orderDTO.setOrderDetailEntityList(orderDetailEntities);
        orderServiceIml.create(orderDTO);


    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,1);
        Page<OrderDTO> list = orderServiceIml.findList(openid, request);
        System.out.println(list.getContent());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO=orderServiceIml.findById(1);
        OrderDTO cancel = orderServiceIml.cancel(orderDTO);
    }

    @Test
    public void finsh() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO=orderServiceIml.findById(1);
        OrderDTO cancel = orderServiceIml.finsh(orderDTO);
    }

    @Test
    public void paid() throws Exception {
    }

    @Test
    public void list(){
        PageRequest request = new PageRequest(0,1);
        Page<OrderDTO> orderDTOPage=orderServiceIml.findList(request);
        Assert.assertEquals(0,orderDTOPage.getTotalElements());
    }

}