package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import top.mxzero.common.enums.OrderState;

import java.util.Date;
import java.util.List;

@Data
@TableName("orders")
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String code;
    private Long productId;
    @TableField(exist = false)
    private Product product;
    private Integer quantity;
    private Double price;
    private Long addressId;
    @TableField(exist = false)
    private Address address;
    private OrderState state;
    private Long userId;
    @TableField(exist = false)
    private User user;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    @JsonIgnore
    private boolean deleted;
}

