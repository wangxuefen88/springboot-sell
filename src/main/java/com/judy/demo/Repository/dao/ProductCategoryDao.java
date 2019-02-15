package com.judy.demo.Repository.dao;

import com.judy.demo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 17:44 2019/2/12
 */

public class ProductCategoryDao {
     @Autowired
     ProductCategoryMapper mapper;
     public int insertByObject(Map<String,Object> map){
      return mapper.insertByMap(map);
  }

}
