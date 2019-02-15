package com.judy.demo.Service;

import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.util.Form.OrderForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 19:30 2019/1/16
 */
@Service
public interface BuyerOrderService {

    Map<String,Integer> create(OrderForm orderForm);
    List<OrderDTO> findList(String openid, Integer page, Integer size);

    OrderDTO detail (String openid,Integer orderId);
    OrderDTO  cancel(String openid,Integer orderId);
}
