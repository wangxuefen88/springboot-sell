package com.judy.demo.aspect;

import Vo.ResultVo;
import com.judy.demo.ExceptionDemo.SellException;
import com.judy.demo.ExceptionDemo.SellerAuthorizeException;
import com.judy.demo.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.ModalExclude;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 15:30 2019/2/10
 */
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView();
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }
}
