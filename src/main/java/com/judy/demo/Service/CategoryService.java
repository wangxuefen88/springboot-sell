package com.judy.demo.Service;


import com.judy.demo.Entity.ProductCategoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 9:23 2018/12/26
 */

public interface CategoryService {

    ProductCategoryEntity findById(Integer CategoryId);

    List<ProductCategoryEntity> findAll();

    List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> productCategories);

    ProductCategoryEntity save(ProductCategoryEntity productCategoryEntity);

    Integer selectByCategoryType(Integer categoryType );
}
