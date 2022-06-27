package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Address {
    @TableId(type=IdType.ASSIGN_ID)
    private Long id;
    private String province;
    private String city;
    private String area;
    private String detail;
    private String name;
    private String phone;
    private String postalCode;
    private Long userId;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    @JsonIgnore
    private boolean deleted;
}
