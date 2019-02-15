package com.judy.demo.ExceptionDemo;

import enums.ResultEnum;
import lombok.Getter;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 16:40 2019/1/14
 */
@Getter
public class SellException  extends  RuntimeException {
  private  Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
