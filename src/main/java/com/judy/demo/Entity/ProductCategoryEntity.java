package com.judy.demo.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.judy.demo.util.Date2LongSerializer;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 7:21 2018/12/19
 */
@Entity
//动态更新数据库的时间
@DynamicUpdate
@Data
//类目
@Table(name="product_category")

public class ProductCategoryEntity implements Serializable{

    /** 类目id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    public ProductCategoryEntity(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategoryEntity() {
    }



}

