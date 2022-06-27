package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Recharge {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private Long amount;
    private Double oldBalance;
    private Double newBalance;
    private Date createTime;
}
