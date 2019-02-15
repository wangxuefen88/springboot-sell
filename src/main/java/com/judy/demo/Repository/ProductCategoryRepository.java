package com.judy.demo.Repository;


import com.judy.demo.Entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 8:50 2018/12/25
 */
//JpaRepository<ProductCategory,Integer>  实体使用的是ProductCategory,主键是integer类型的
@Component
public interface ProductCategoryRepository  extends JpaRepository<ProductCategoryEntity, Integer> {

    List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> integers);



}
