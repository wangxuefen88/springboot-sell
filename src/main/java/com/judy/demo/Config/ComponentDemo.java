package com.judy.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 20:02 2019/2/15
 */

//表示这个类是bean定义的源
@Configuration
public class ComponentDemo{

    //负责告诉容器返回的对象会交给springioc容器管理
    @Bean
    public String judy(){
        String a = new String();
        return a;
    }

    @Bean
    public Integer judyInt(){
        Integer a = new Integer(2);
        return a;
    }
}
