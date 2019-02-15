package enums;

import com.judy.demo.Entity.ProductInfoCategoryEntity;
import lombok.Data;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 10:01 2019/1/13
 */
@Getter
public enum ProductStatusEnums implements CodeEnums {

    UP(1 , "在架"),
    DOWN(0 , "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
