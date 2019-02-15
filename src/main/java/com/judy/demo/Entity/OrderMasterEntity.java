package com.judy.demo.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.judy.demo.util.Date2LongSerializer;
import enums.OrderStatusEnums;
import enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 22:06 2019/1/13
 */

@Entity
@Table(name = "order_master")
@Data
@DynamicUpdate
public class OrderMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();
    private Integer payStatus = PayStatusEnums.WAIT.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
