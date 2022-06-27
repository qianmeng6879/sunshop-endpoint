package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Category {
    @NotNull
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String name;
    private Integer parentId;
    private boolean hot;
    @TableField(exist = false)
    private List<Category> subs;
}

