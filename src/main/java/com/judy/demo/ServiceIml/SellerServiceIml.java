package com.judy.demo.ServiceIml;

import com.judy.demo.Entity.SellerInfo;
import com.judy.demo.Repository.SellerInfoRepository;
import com.judy.demo.Service.SellerService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 9:31 2019/2/10
 */
@Service
public class SellerServiceIml implements SellerService{
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
