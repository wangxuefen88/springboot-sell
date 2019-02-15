package com.judy.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 22:03 2019/1/13
 */
@Entity
@Table(name="order_detail")
@DynamicUpdate
@Data
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailId;
    private Integer productId;
    private Integer orderId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
}

