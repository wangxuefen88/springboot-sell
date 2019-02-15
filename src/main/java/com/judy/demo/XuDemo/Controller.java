package com.judy.demo.XuDemo;
import com.judy.demo.Entity.ProductInfoCategoryEntity;
import com.judy.demo.Repository.ProductInfoRepository;
import com.judy.demo.Service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 23:31 2019/1/14
 */
@RestController
@RequestMapping("/Xu/Demo")
public class Controller{
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @GetMapping("/judy.html")
    public ProductInfoCategoryEntity productFindOne(){
        ProductInfoCategoryEntity productInfoCategoryEntity = new ProductInfoCategoryEntity();
        ProductInfoCategoryEntity productInfoServiceById = productInfoService.findById(3);
        productInfoServiceById.setProductName("薯条");
        productInfoService.save(productInfoServiceById);
        return productInfoServiceById;
    }


    @GetMapping("judy2.html")
    public String ThreadTest() throws InterruptedException {
        ProductInfoCategoryEntity productInfoServiceById = productInfoService.findById(2);
        productInfoServiceById.setProductName("淇淋");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProductInfoCategoryEntity productInfoServiceById = productInfoService.findById(2);
                productInfoServiceById.setProductName("克力");
               productInfoService.save(productInfoServiceById);
            }
        }).start();
        Thread.sleep(10000);
        productInfoService.save(productInfoServiceById);
        return "is judy";
    }

//    @GetMapping("judy2.html")
//    public String ThreadTwoTest() throws InterruptedException {
//        ProductInfoCategoryEntity   productInfoServiceById = productInfoRepository.updateTwo();
//        productInfoServiceById.setProductName("冰淇淋");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ProductInfoCategoryEntity productInfoServiceById = productInfoRepository.updateTwo();
//                productInfoServiceById.setProductName("巧克力");
//                productInfoRepository.selectTwo();
//            }
//        }).start();
//        Thread.sleep(10000);
//        productInfoRepository.selectTwo();
//        return "is judy";
//    }

}
