package com.judy.demo.util.Form;

import com.judy.demo.Entity.OrderDetailEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * @param
 * @Author: judy
 * @Description: controller校验是否为空
 * @Date: Created in 19:23 2019/1/16
 */
@Data
public class OrderForm {
    // 买家名字
    @NotEmpty(message = "姓名必填")
    private String name;
    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;
    //买家电话
    @NotEmpty(message = "电话必填")
    private String phone;
    //买家openid
    @NotEmpty(message = "订单号openid必填")
    private String openid;
    //购物车不能为空
    @NotEmpty(message = "购物车不能为空")
   private String item;
}
