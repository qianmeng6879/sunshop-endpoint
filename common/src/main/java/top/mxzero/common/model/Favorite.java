package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Favorite {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    @NotNull(message = "productId not null")
    private Long productId;
    @TableField(exist = false)
    private Product product;
    private Date createTime;
}
