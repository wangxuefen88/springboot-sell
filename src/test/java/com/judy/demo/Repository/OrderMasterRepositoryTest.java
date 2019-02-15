package com.judy.demo.Repository;

import com.judy.demo.Entity.OrderMasterEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 11:30 2019/1/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    public  OrderMasterRepository orderMasterRepository;
    private final String OPENID="123";
    @Test
    public void save() throws Exception {
        OrderMasterEntity orderMasterEntity =new OrderMasterEntity();
        orderMasterEntity.setOrderId(12345);
        orderMasterEntity.setBuyerAddress("廊坊");
        orderMasterEntity.setBuyerName("皮蛋肉");
        orderMasterEntity.setBuyerOpenid(OPENID);
        orderMasterEntity.setBuyerPhone("18631687403");
        orderMasterEntity.setOrderAmount( new BigDecimal(1000));
        orderMasterRepository.save(orderMasterEntity);
      }

      @Test
    public void findByBuyerOpenid() {
           PageRequest request1 =new PageRequest(0,1);
           orderMasterRepository.findByBuyerOpenid( OPENID,request1);
      }
}