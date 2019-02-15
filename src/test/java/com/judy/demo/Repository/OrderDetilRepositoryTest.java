package com.judy.demo.Repository;

import com.judy.demo.Entity.OrderDetailEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 11:22 2019/1/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetilRepositoryTest {
    @Autowired
    public  OrderDetilRepository orderDetilRepository;
    @Test
    public void save(){
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setDetailId(123);
        orderDetailEntity.setProductIcon("http://113223");
        orderDetailEntity.setOrderId(1);
        orderDetailEntity.setProductName("皮蛋粥");
        orderDetailEntity.setProductPrice(new BigDecimal(2.2));
        orderDetailEntity.setProductQuantity(2);
        OrderDetailEntity save = orderDetilRepository.save(orderDetailEntity);
        System.out.println(save.toString());
    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetailEntity> byOrderId = orderDetilRepository.findByOrderId(1);
        System.out.println(byOrderId.toString());
    }

}