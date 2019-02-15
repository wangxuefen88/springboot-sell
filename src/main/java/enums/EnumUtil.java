package enums;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 18:11 2019/2/7
 */
public class EnumUtil {
    public  static <T extends CodeEnums> T getByCode(Integer code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
