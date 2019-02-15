package com.judy.demo.Controler;

import com.judy.demo.Entity.ProductCategoryEntity;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.ExceptionDemo.SellException;
import com.judy.demo.Service.CategoryService;
import com.judy.demo.util.Form.CategoryForm;
import com.judy.demo.util.Form.ProductForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 23:05 2019/2/9
 */

@Controller
@RequestMapping("/seller/category")
public class SellCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategoryEntity> categoryList= categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId") Integer categoryId,
                              Map<String,Object> map){

        if (categoryId !=null){
            ProductCategoryEntity productCategory = categoryService.findById(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    /**
     * 保存更新类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategoryEntity productInfoCategoryEntity= new ProductCategoryEntity();
        try {
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())){
                productInfoCategoryEntity = categoryService.findById(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productInfoCategoryEntity);
            categoryService.save(productInfoCategoryEntity);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }

}
