package com.judy.demo.ServiceIml

import com.judy.demo.Entity.ProductCategoryEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Assert

import javax.annotation.Resource

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 22:55 2019/1/12
 * @param user
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImlTest {
    @Autowired
    private CategoryServiceIml categoryServiceIml;

    @Test
    void testFindOne() {
        ProductCategoryEntity productCategoryEntity= categoryServiceIml.findById(2)
        System.out.println(productCategoryEntity.toString())
    }
    @Test
    void testFindAll() {
        List<ProductCategoryEntity> productCategoryEntities  = categoryServiceIml.findAll();
        System.out.println(productCategoryEntities.toString())
    }
    @Test
    void testFindByCategoryTypeIn() {
        List<ProductCategoryEntity> productCategoryEntities= categoryServiceIml.findByCategoryTypeIn(Arrays.asList(1,2,3))
        System.out.println(productCategoryEntities.toString())
    }
    @Test
    void testSave(){
        ProductCategoryEntity productCategoryEntity1=new ProductCategoryEntity("8",8);
        ProductCategoryEntity productCategoryEntity = categoryServiceIml.save(productCategoryEntity1)
        System.out.println(productCategoryEntity.toString())
    }
}
