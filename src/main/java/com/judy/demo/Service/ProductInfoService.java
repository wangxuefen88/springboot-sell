package com.judy.demo.Service;

import Vo.ProductInfoVo;
import com.judy.demo.DTO.CartDTO;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 6:48 2018/12/27
 */
public interface ProductInfoService {
    Page<ProductInfoCategoryEntity> findAll(Pageable pageable);
    ProductInfoCategoryEntity findById(Integer id);
    List<ProductInfoCategoryEntity> findUpALL();
    ProductInfoCategoryEntity save(ProductInfoCategoryEntity productInfoCategoryEntity);
    //增加商品数量
    void increaseStock( List<CartDTO> cartDTO);

    //减少商品数量
    void decreaseStock(List<CartDTO> cartDTOList);

    //商品上架
    ProductInfoCategoryEntity onSale(Integer productId);

    //商品下架
    ProductInfoCategoryEntity offSale(Integer productId);
}
