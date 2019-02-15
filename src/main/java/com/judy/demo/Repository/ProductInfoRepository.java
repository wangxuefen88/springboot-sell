package com.judy.demo.Repository;

import com.judy.demo.Entity.ProductInfoCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 6:46 2018/12/27
 */
@Component
public interface ProductInfoRepository extends JpaRepository<ProductInfoCategoryEntity, Integer> {

    List<ProductInfoCategoryEntity> findByProductStatus(Integer ProductStatus);

//    @Query(value = "select product_name from product_info where product_id = 1")
//    ProductInfoCategoryEntity updateTwo();
//
//    @Query(value =  "Update product_info set product_name = '水果' where product_id =1 ")
//    @Modifying
//    @Transactional
//    ProductInfoCategoryEntity  selectTwo();
}
