package com.judy.demo.Repository;

import com.judy.demo.Entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @param3456
 *
 * @Author: judy
 * @Description:
 * @Date: Created in 22:17 2019/1/13
 */
@Component
public interface OrderDetilRepository  extends JpaRepository<OrderDetailEntity,Integer> {
   //订单详情表
    List<OrderDetailEntity> findByOrderId(Integer orderId);
}
