package com.judy.demo.Repository

import com.judy.demo.Entity.ProductCategoryEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import javax.transaction.Transactional

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 20:27 2019/1/12
 * @param user
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryRepositoryTest  {

    @Autowired
    public ProductCategoryRepository productCategoryRepository;

    @Test
     void productCategoryRepository (){
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryRepository.findById(1)
        ProductCategoryEntity productCategoryEntity1 = new ProductCategoryEntity()
        //判断是否为空,true表示有值
        if(productCategoryEntity.isPresent()){
            productCategoryEntity1 = productCategoryEntity.get()
        }else {
             productCategoryEntity1 = productCategoryEntity.orElse((Object)null)
        }
        System.out.print("this is judy "+ productCategoryEntity1.toString())
    }
    @Test
    void Seve(){
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity()
        productCategoryEntity.setCategoryName("wangxuefen")
        productCategoryEntity.setCategoryType(6)
        ProductCategoryEntity save = productCategoryRepository.save(productCategoryEntity)
        System.out.print("this is judy "+ save.toString())
    }
    @Test
    //这个事物和我service的事物还是不一样的,这个事物表示的是执行成功也不会假如到数据库中,防止测试数据进入数据库
    @Transactional
    void update (){
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryRepository.findById(1)
        ProductCategoryEntity productCategoryEntity1 = new ProductCategoryEntity()
        //判断是否为空,true表示有值
        if(productCategoryEntity.isPresent()){
            productCategoryEntity1 = productCategoryEntity.get()
            productCategoryEntity1.setCategoryName("2")
            productCategoryRepository.save(productCategoryEntity1)
        }else {
            productCategoryEntity1 = productCategoryEntity.orElse((Object)null)
        }
        System.out.print("this is judy "+ productCategoryEntity1.toString())
    }


    @Test
    void findCategoryTypeList(){
        List<Integer> list =new ArrayList<>(3,5,7);
        List<ProductCategoryEntity> productCategoryEntities = productCategoryRepository.findByCategoryTypeIn(list)
        Assert.assertNotEquals(null,productCategoryEntities);
    }










}
