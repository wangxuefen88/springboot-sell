package com.judy.demo.Controler;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.judy.demo.Constant.CookieConstant;
import com.judy.demo.Constant.RedisConstant;
import com.judy.demo.Entity.SellerInfo;
import com.judy.demo.Service.SellerService;
import com.judy.demo.util.CookieUtil;
import enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 10:08 2019/2/10
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerUserController{
    @Autowired
    private SellerService sellerService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登陆
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response, Map<String,Object>map){
        //openid和数据库中的数据配对
        SellerInfo sellerInfo = sellerService.findByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","sell/seller/order/List");
            return new ModelAndView("common/error");
        }
        //把token放到redis中
        String token = UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);
        //设置token到cookies中,从token中取到value值,然后从value值中得到reids中根据tokenid得到openid
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return new ModelAndView("redirect:/sell/seller/order/list");
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response , Map<String,Object> map){
        //cookies里面查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getName()));
            // 清楚cookies
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
