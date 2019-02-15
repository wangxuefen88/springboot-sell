package com.judy.demo.Repository;

import com.judy.demo.Entity.ProductInfoCategoryEntity;
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
 * @Date: Created in 9:04 2019/1/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Test
    public void save(){
        ProductInfoCategoryEntity productInfoCategoryEntity = new ProductInfoCategoryEntity();
        productInfoCategoryEntity.setProductId(1);
        productInfoCategoryEntity.setCategoryType(2);
        productInfoCategoryEntity.setProductDescription("2");
        productInfoCategoryEntity.setProductIcon("http://judy.icon");
        productInfoCategoryEntity.setProductName("辣条");
        productInfoCategoryEntity.setProductPrice(BigDecimal.valueOf(20));
        productInfoCategoryEntity.setProductStock(100);
        productInfoCategoryEntity.setProductStatus(2);
        ProductInfoCategoryEntity save = productInfoRepository.save(productInfoCategoryEntity);
        System.out.print(save.toString());
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfoCategoryEntity> byProductStatus = productInfoRepository.findByProductStatus(2);
        System.out.print(byProductStatus.toString());
    }

}