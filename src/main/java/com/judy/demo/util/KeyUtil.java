package com.judy.demo.util;

import java.util.Random;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 17:07 2019/1/14
 */
public class KeyUtil {
    //用于生成唯一主键
    public static String genUniqueKey(){
        Random random =new Random();
        Integer a=  random.nextInt(9000000)+1000000;
        return System.currentTimeMillis()+String.valueOf(a);
    }
}
