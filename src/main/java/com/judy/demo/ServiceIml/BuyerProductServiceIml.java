package com.judy.demo.ServiceIml;

import Vo.ProductInfoVo;
import Vo.ProductVo;
import com.judy.demo.Entity.ProductCategoryEntity;
import com.judy.demo.Entity.ProductCategoryEntity;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.Service.BuyerProductService;
import com.judy.demo.Service.CategoryService;
import com.judy.demo.Service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 11:24 2018/12/27
 */
@Service
public class BuyerProductServiceIml implements BuyerProductService {
    @Resource
   private CategoryService categoryService;

    @Resource
    private ProductInfoService productInfoService;



    @Override
    public List<ProductVo> list() {

        //查询所有上架商品
        List<ProductInfoCategoryEntity> productInfoServiceUpALL = productInfoService.findUpALL();
        List<Integer> productCategoryList= productInfoServiceUpALL.stream()
                .map(el -> (el.getCategoryType()))
                .collect(Collectors.toList());

        //查询商品的类目
        List<ProductCategoryEntity> listProductCategoryType = categoryService.findByCategoryTypeIn(productCategoryList);

        List<ProductVo> productVosList = new ArrayList<>();
        for (ProductCategoryEntity productCategory : listProductCategoryType) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
              //这个商品的属于那个里面下的
            for (ProductInfoCategoryEntity productInfoCategoryEntity : productInfoServiceUpALL) {
                 if(productInfoCategoryEntity.getCategoryType().equals(productCategory.getCategoryType())) {
                  ProductInfoVo productInfoVo = new ProductInfoVo();
                  BeanUtils.copyProperties(productInfoCategoryEntity,productInfoVo);
                  productInfoVos.add(productInfoVo);
              }
            }
            productVo.setProductInfoVos(productInfoVos);
            productVosList.add(productVo);
        }
        return productVosList;

    }
}
