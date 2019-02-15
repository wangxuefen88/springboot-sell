package com.judy.demo.Controler;

import Vo.ProductVo;
import Vo.ResultVo;
import com.judy.demo.Service.BuyerProductService;
import com.judy.demo.Service.CategoryService;
import com.judy.demo.util.ResultVoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 8:23 2018/12/27
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductContoller {
    @Resource
    private CategoryService categoryService;
    @Resource
    private BuyerProductService buyerProductService;

    @GetMapping("/list")
    public ResultVo list(){
        List<ProductVo> list = buyerProductService.list();
        return ResultVoUtil.success(list);
     }
}
