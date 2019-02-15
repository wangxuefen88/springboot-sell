package com.judy.demo.Controler;

import com.judy.demo.DTO.OrderDTO;
import com.judy.demo.Service.OrderService;
import enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 23:16 2019/1/21
 */

@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController{
    @Autowired
    private OrderService orderService;

    /**
     * 查询订单为空
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object>map){
        PageRequest request = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        if (orderDTOPage == null) {
            log.warn("查询订单为空");
            return null;
        }
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return  new ModelAndView("order/list",map);
    }

    /**
     *  取消订单
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") Integer orderId,
                               Map<String,Object> map){


        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findById(orderId);
            OrderDTO cancel = orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.warn("查询订单为空,orderId-{}",orderId);
            map.put("orderDTO",orderDTO);
            map.put("url","/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/seller/order/list");
        return new ModelAndView("order/cancel");
    }


    /**
     *  订单详情
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") Integer orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findById(orderId);
        } catch (Exception e) {
            log.warn("查询订单为空,orderId-{}",orderId);
            map.put("orderDTO",orderDTO);
            map.put("url","/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO );
        return new ModelAndView("order/detail",map);
    }


    /**
     *  完成订单
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "orderId") Integer orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findById(orderId);
            orderService.finsh(orderDTO);
        } catch (Exception e) {
            log.warn("查询订单为空,orderId-{}",orderId);
            map.put("orderDTO",orderDTO);
            map.put("url","/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/seller/order/list");
        return new ModelAndView("common/success");
    }


}
