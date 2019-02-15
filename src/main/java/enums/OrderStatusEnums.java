package enums;


import lombok.Data;
import lombok.Getter;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 22:10 2019/1/13
 */
@Getter
public enum OrderStatusEnums implements CodeEnums {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

//    public static OrderStatusEnums getOrderStatusEnums(Integer code){
//        for (OrderStatusEnums orderStatusEnums : OrderStatusEnums.values()){
//            if (orderStatusEnums.getCode().equals(code)){
//                return orderStatusEnums;
//            }
//        }
//        return null;
//    }
}
