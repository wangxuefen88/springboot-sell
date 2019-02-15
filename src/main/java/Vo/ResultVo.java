package Vo;
import lombok.Data;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 7:13 2018/12/27
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private String message;
    private T data;
}
