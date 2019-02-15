package Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.Data;

import java.util.List;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 9:39 2018/12/27
 */
@Data
public class ProductVo {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVos;
}
