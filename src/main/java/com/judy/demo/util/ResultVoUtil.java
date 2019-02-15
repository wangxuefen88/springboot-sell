package com.judy.demo.util;

import Vo.ResultVo;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 6:56 2019/1/4
 */
public class ResultVoUtil {
    public static ResultVo success(Object date){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0000);
        resultVo.setMessage("成功");
        resultVo.setData(date);
        return resultVo;
    }
    public static ResultVo error(Integer code, String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(msg);
        return resultVo;
    }
}
