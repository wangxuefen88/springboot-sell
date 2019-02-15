package com.judy.demo.Repository;

import com.judy.demo.Entity.OrderMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 22:19 2019/1/13
 */
@Component
public interface OrderMasterRepository extends JpaRepository<OrderMasterEntity,Integer> {
    //根据订单查询主表,分页查询
     Page<OrderMasterEntity> findByBuyerOpenid(String buyerOpenid , Pageable pageable);
}
