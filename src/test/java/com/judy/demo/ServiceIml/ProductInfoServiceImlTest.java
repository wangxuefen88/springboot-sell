package com.judy.demo.ServiceIml;

import com.judy.demo.Entity.ProductInfoCategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 10:07 2019/1/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImlTest {

    @Autowired
    private ProductInfoServiceIml productInfoServiceIml;

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<ProductInfoCategoryEntity> ProductInfoCategoryall = productInfoServiceIml.findAll(pageRequest);
        System.out.println("judy1"+ProductInfoCategoryall.getTotalElements());
    }

    @Test
    public void findById() throws Exception {
        ProductInfoCategoryEntity byId = productInfoServiceIml.findById(1);
        System.out.println("judy2"+byId.toString());
    }

    @Test
    public void findUpALL() throws Exception {
        List<ProductInfoCategoryEntity> upALL = productInfoServiceIml.findUpALL();
        System.out.println("judy3"+upALL.toString());
    }

    @Test
    public void save() throws Exception {
        ProductInfoCategoryEntity productInfoCategoryEntity = new ProductInfoCategoryEntity();
        productInfoCategoryEntity.setProductId(3);
        productInfoCategoryEntity.setCategoryType(3);
        productInfoCategoryEntity.setProductDescription("3");
        productInfoCategoryEntity.setProductIcon("http://judy.icon");
        productInfoCategoryEntity.setProductName("紫玫红花");
        productInfoCategoryEntity.setProductPrice(BigDecimal.valueOf(20.1));
        productInfoCategoryEntity.setProductStock(100);
        productInfoCategoryEntity.setProductStatus(2);
        ProductInfoCategoryEntity save = productInfoServiceIml.save(productInfoCategoryEntity);
        System.out.print(save.toString());
    }

}