package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import top.mxzero.common.enums.UserType;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private UserType type;
    private Double balance;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    @JsonIgnore
    private boolean deleted;
}
