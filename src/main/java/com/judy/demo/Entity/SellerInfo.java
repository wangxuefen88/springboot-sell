package com.judy.demo.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.judy.demo.util.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @param user
 * @Author: judy
 * @Description:
 * @Date: Created in 9:20 2019/2/10
 */
@Entity(name = "seller_info")
@DynamicUpdate
@Data
@Table(name = "seller_info")
public class SellerInfo {
    /** 类目id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String  Password;
    private String openid;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
