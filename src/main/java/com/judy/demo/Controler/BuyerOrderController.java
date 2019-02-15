package com.judy.demo.Controler;

import Vo.ResultVo;
import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Service.BuyerOrderService;
import com.judy.demo.Service.OrderService;
import com.judy.demo.util.Form.OrderForm;
import com.judy.demo.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 19:21 2019/1/16
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private BuyerOrderService buyerOrderService;

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,Integer>> create (@Valid OrderForm orderForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("创建订单参数不正确 orderForm-{}",orderForm);
        }
        Map<String,Integer> resultMap = buyerOrderService.create(orderForm);

        return ResultVoUtil.success(resultMap);
    }

    //订单列表
    @GetMapping("/List")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue  = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "1")Integer size
                                         ){
        if(StringUtils.isEmpty(openid)){
            log.error("openid is null");
        }
        List<OrderDTO> list = buyerOrderService.findList(openid, page, size);
        return ResultVoUtil.success(list);
    }




    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openId") String openid,
                                      @RequestParam("orderId") Integer orderId){

        OrderDTO orderDTO = orderService.findById(orderId);
        return ResultVoUtil.success(orderDTO);
    }



    //取消订单
    @PostMapping("/cancel")
    public ResultVo<OrderDTO> cancel(@RequestParam("openId") String openid,
                                     @RequestParam("orderId") Integer orderId){
        OrderDTO orderDTO = orderService.findById(orderId);
        return ResultVoUtil.success(orderDTO);
    }

}
