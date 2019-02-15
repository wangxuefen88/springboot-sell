package com.judy.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.judy.demo.util.Date2LongSerializer;
import enums.EnumUtil;
import enums.ProductStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 6:36 2018/12/27
 */
@Entity(name = "product_info")
@DynamicUpdate
@Data
@Table(name = "product_info")
//商品详情信息
public class ProductInfoCategoryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
    private Integer productStatus = ProductStatusEnums.UP.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @Version
    private Integer  version;

    @JsonIgnore
    public ProductStatusEnums getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnums.class);

    }
}
