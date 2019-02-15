package com.judy.demo.DTO;

import lombok.Data;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 17:25 2019/1/14
 */
@Data
public class CartDTO {
    private Integer productId;
    private Integer productQuantity;

    public CartDTO(Integer productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
