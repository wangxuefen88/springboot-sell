package com.judy.demo.Controler;

import Vo.ProductInfoVo;
import Vo.ProductVo;
import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Entity.ProductCategoryEntity;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.ExceptionDemo.SellException;
import com.judy.demo.Service.CategoryService;
import com.judy.demo.Service.OrderService;
import com.judy.demo.Service.ProductInfoService;
import com.judy.demo.util.Form.ProductForm;
import enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.MariaDB10Dialect;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @Author: judy
 * @Description:
 * @Date: Created in 19:59 2019/2/9
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<ProductInfoCategoryEntity> productInfoPage = productInfoService.findAll(request);
        if (productInfoPage == null) {
            log.warn("查询订单为空");
            return null;
        }
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return  new ModelAndView("product/list",map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
//    @GetMapping 是@RequestMapping(method=RequestMethod.GET)
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam(value = "productId") Integer productId,
                             Map<String,Object> map){
        try {
             productInfoService.onSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/List");
            return  new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/List");
        return new ModelAndView("common/success",map);
    }
    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") Integer productId,
                               Map<String,Object> map){
        try {
            productInfoService.offSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 查询类目,用于修改商品类目
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam("productId") Integer productId,
                                Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfoCategoryEntity productInfo = productInfoService.findById(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategoryEntity> categroryList= categoryService.findAll();
        map.put("categroryList",categroryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm , BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfoCategoryEntity productInfoCategoryEntity= new ProductInfoCategoryEntity();
        try {
            if (!StringUtils.isEmpty(productForm.getProductId())){
                 productInfoCategoryEntity = productInfoService.findById(productForm.getProductId());
            }
            BeanUtils.copyProperties(productForm,productInfoCategoryEntity);
            productInfoService.save(productInfoCategoryEntity);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
