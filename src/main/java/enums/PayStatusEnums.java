package enums;

import lombok.Getter;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 22:10 2019/1/13
 */
@Getter
public enum PayStatusEnums implements CodeEnums {
    WAIT(0,"等待支持"),
    SUCCESS(1,"支持成功");
    private Integer code;
    private String message;
    PayStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
