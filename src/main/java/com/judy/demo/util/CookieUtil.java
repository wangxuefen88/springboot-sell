package com.judy.demo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 10:40 2019/2/10
 */
public class CookieUtil {
    /**
     *  设置cookies
     * @param response 请求
     * @param name key
     * @param value value
      * @param maxAge 过期时间
     */
    public static void set(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie= new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 得到cookie
     * @param request  响应
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,String name){
        Map<String, Cookie> CookieMap = readCookieMap(request);
        if(CookieMap!=null){
            if(CookieMap.containsKey(name)){
                return CookieMap.get(name);
            }else {
                return null;
            }
        }
        return  null;
    }

    /**
     * 制定map
     * @param request
     * @return
     */
    public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
