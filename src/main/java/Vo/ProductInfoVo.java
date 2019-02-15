package Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 9:44 2018/12/27
 */
@Data
public class ProductInfoVo {

    @JsonProperty("Id")
    private Integer productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
