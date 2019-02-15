package com.judy.demo.aspect;

import com.judy.demo.Constant.CookieConstant;
import com.judy.demo.Constant.RedisConstant;
import com.judy.demo.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 14:36 2019/2/10
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect extends Throwable {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.judy.demo.Controler.Seller*.*(..))"+"&& !execution(public * com.judy.demo.Controler.SellerUserController.*(..))")
    public void verify(){}

   @Before("verify()")
    public void doVerify() throws SellerAuthorizeAspect {
       ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpServletRequest request = attributes.getRequest();
       Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
       if(cookie==null){
           log.warn("登陆失败,cookies中查不到token");
           throw  new SellerAuthorizeAspect();
       }
       //去redis中查询
       String tokenValue=redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getName()));
       if (StringUtils.isEmpty(tokenValue)){
           log.warn("登陆校验失败redis中没有token");
           throw new SellerAuthorizeAspect();
       }
   }
}
