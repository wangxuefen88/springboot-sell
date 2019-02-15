package com.judy.demo.Service;

import com.judy.demo.DTO.OrderDTO;
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
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 15:46 2019/1/17
 */
@Controller
@RequestMapping("/sell/order")
public class SellOrderControler {
 @Autowired
 private  OrderService orderService;

    @GetMapping("/sell")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page -1 ,size);
        Page<OrderDTO> orderDTPage =   orderService.findList(request);
        map.put("orderDTPage",orderDTPage);
        return new ModelAndView("templates/order/list",map);
    }
}
