package com.judy.demo.Service;

import com.judy.demo.Entity.SellerInfo;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 9:31 2019/2/10
 */
public interface SellerService {
    SellerInfo findByOpenid(String openid);
}

