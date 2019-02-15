package com.judy.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.judy.demo.Entity.OrderDetailEntity;
import com.judy.demo.Entity.OrderMasterEntity;
import com.judy.demo.Repository.OrderMasterRepository;
import com.judy.demo.util.Date2LongSerializer;
import enums.EnumUtil;
import enums.OrderStatusEnums;
import enums.PayStatusEnums;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @Author: judy
 * @Description:
 * @Date: Created in 16:28 2019/1/14
 */
@Data

//避免给前端null值
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO  {
    private int orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus ;
    private Integer payStatus ;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();

    //表示当需要json的时候会自动忽略这个方法
    @JsonIgnore
    public  OrderStatusEnums getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }
    @JsonIgnore
    public PayStatusEnums getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnums.class);
    }


}
