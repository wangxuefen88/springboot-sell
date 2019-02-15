package com.judy.demo.ServiceIml;

import com.judy.demo.DTO.CartDTO;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.ExceptionDemo.SellException;
import com.judy.demo.Repository.ProductInfoRepository;
import com.judy.demo.Service.ProductInfoService;
import enums.ProductStatusEnums;
import enums.ResultEnum;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 6:48 2018/12/27
 */
@Service
public class ProductInfoServiceIml implements ProductInfoService {
    @Resource
    private ProductInfoRepository productInfoRepository;
    @Resource
    private ProductInfoService productInfoService;

    @Override
    public Page<ProductInfoCategoryEntity> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfoCategoryEntity findById(Integer id) {
        Optional<ProductInfoCategoryEntity> byId = productInfoRepository.findById(id);
        ProductInfoCategoryEntity productInfoCategoryEntity = new ProductInfoCategoryEntity();
        if (byId.isPresent()) {
            return productInfoCategoryEntity = byId.get();
        }
        return byId.orElse(null);
    }

    @Override
    public List<ProductInfoCategoryEntity>  findUpALL() {
        return productInfoRepository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public ProductInfoCategoryEntity save(ProductInfoCategoryEntity productInfoCategoryEntity) {
        return productInfoRepository.save(productInfoCategoryEntity);
    }

    @Override
    public void increaseStock( List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfoCategoryEntity productInfoCategoryEntity = findById(cartDTO.getProductId());
            if(productInfoCategoryEntity !=null){
                productInfoCategoryEntity.setProductStock(productInfoCategoryEntity.getProductStock()+cartDTO.getProductQuantity());
                productInfoRepository.save(productInfoCategoryEntity);
            }
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfoCategoryEntity productInfoCategoryEntity = findById(cartDTO.getProductId());
            int count = productInfoCategoryEntity.getProductStock() - cartDTO.getProductQuantity();
            if(count<0){
                throw new SellException(ResultEnum.PARAM_ERROR);
            }
            productInfoCategoryEntity.setProductStock(count);
            productInfoRepository.save(productInfoCategoryEntity);
        }

    }

    @Override
    public ProductInfoCategoryEntity onSale(Integer productId) {
        ProductInfoCategoryEntity productInfoCategoryEntity = productInfoService.findById(productId);
        if (productInfoCategoryEntity ==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (productInfoCategoryEntity.getProductStatus().equals(ProductStatusEnums.UP)){
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        productInfoCategoryEntity.setProductStatus(ProductStatusEnums.UP.getCode());
        ProductInfoCategoryEntity infoCategoryEntity = productInfoService.save(productInfoCategoryEntity);
        return infoCategoryEntity;
    }

    /**
     * 1 先判断是否有
     * 2 在判断是否要改变
     * @param productId
     * @return
     */
    @Override
    public ProductInfoCategoryEntity offSale(Integer productId) {
        ProductInfoCategoryEntity productInfoCategoryEntity = productInfoService.findById(productId);
        if (productInfoCategoryEntity ==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (productInfoCategoryEntity.getProductStatus().equals(ProductStatusEnums.DOWN)){
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        productInfoCategoryEntity.setProductStatus(ProductStatusEnums.DOWN.getCode());
        ProductInfoCategoryEntity infoCategoryEntity = productInfoService.save(productInfoCategoryEntity);
        return infoCategoryEntity;
    }

}
