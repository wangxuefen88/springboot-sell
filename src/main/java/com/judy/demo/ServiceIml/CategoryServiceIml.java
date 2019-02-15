package com.judy.demo.ServiceIml;

import com.judy.demo.Entity.ProductCategoryEntity;
import com.judy.demo.Repository.ProductCategoryRepository;
import com.judy.demo.Service.CategoryService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 9:25 2018/12/26
 */
@Service
public class CategoryServiceIml implements CategoryService {
    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryEntity findById(Integer CategoryId) {
          Optional<ProductCategoryEntity> productCategoryEntity= productCategoryRepository.findById(CategoryId);
           ProductCategoryEntity productCategoryEntity1 = new ProductCategoryEntity();
          if(productCategoryEntity.isPresent()){
            return   productCategoryEntity1=productCategoryEntity.get();
           }
           return productCategoryEntity.orElse(null);
    }

    @Override
    public List<ProductCategoryEntity> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> productCategories) {
        return productCategoryRepository.findByCategoryTypeIn(productCategories);
    }

    @Override
    public ProductCategoryEntity save(ProductCategoryEntity productCategoryEntity) {
        return productCategoryRepository.save(productCategoryEntity);
    }

    @Override
    public Integer selectByCategoryType(Integer categoryType) {
        return null;
    }


}
