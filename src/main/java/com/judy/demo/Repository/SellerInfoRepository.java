package com.judy.demo.Repository;

import com.judy.demo.Entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 9:24 2019/2/10
 */
@Component
public interface SellerInfoRepository extends JpaRepository<SellerInfo,Integer> {
    SellerInfo findByOpenid(String openid);
}
