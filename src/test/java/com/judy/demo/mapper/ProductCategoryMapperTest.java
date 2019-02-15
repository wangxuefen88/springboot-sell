package com.judy.demo.mapper;

import com.judy.demo.Entity.ProductCategoryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 16:36 2019/2/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategoryEntity byCategoryType = mapper.findByCategoryType(2);
        Assert.assertNotNull(byCategoryType);
    }
    @Test
    public void findByCategoryName() throws Exception {
       List<ProductCategoryEntity>  productCategoryEntityList = mapper.findByCategoryName("皮蛋粥");
        Assert.assertNotEquals(0,productCategoryEntityList.size());
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setCategoryType(2);
        productCategoryEntity.setCategoryName("皮蛋粥");
        int insertByObject = mapper.insertByObject(productCategoryEntity);
        Assert.assertEquals(1,insertByObject);
    }

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object>map= new HashMap<>();
        map.put("category_name","泡泡糖");
        map.put("category_type",2);
        int insert = mapper.insertByMap(map);
        Assert.assertEquals(1,insert);
    }

    @Test
    public void updateByCategoryType() throws Exception {
        String categoryName="苹果";
        Integer categoryType=2;
        int insertByObject = mapper.updateByCategoryType(categoryName,categoryType);
        Assert.assertEquals(1,insertByObject);
    }
    @Test
    public void updateByCategoryTypeEntity() throws Exception {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setCategoryType(2);
        productCategoryEntity.setCategoryName("西红柿");
        int insertByObject = mapper.updateByCategoryTypeEntity(productCategoryEntity);
        Assert.assertEquals(1,insertByObject);
    }
    @Test
    public void deleteByCategory() throws Exception {
        int insertByObject = mapper.deleteByCategory(2);
        Assert.assertEquals(1,insertByObject);
    }
}