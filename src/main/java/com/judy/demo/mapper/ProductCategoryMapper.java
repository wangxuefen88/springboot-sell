package com.judy.demo.mapper;

import com.judy.demo.Entity.ProductCategoryEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 16:28 2019/2/10
 */

public interface ProductCategoryMapper {
    @Insert(value = "insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    /**
     * 插入
     * @param productCategoryEntity
     * @return
     */
    @Insert(value = "insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategoryEntity productCategoryEntity);

    /**
     * 查询类目类型
     * @param categoryType
     * @return
     */
    @Select("select * from product_category where category_type = #{categoryType}")
    //得出的返回结果与实体进行对应
    @Results ({
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName")
    })
    ProductCategoryEntity findByCategoryType(Integer categoryType);

    /**
     * 根据类目名称查询
     * @param categoryName
     * @return
     */
    @Select("select * from product_category where category_name = #{categoryName}")
    //得出的返回结果与实体进行对应
    @Results ({
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName")
    })
     List<ProductCategoryEntity>findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type= #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type= #{categoryType}")
    int updateByCategoryTypeEntity(ProductCategoryEntity productCategoryEntity);


    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategory(Integer categoryType);

//    ProductCategoryEntity PRODUCT_CATEGORY_ENTITY(Integer categoryType);
}