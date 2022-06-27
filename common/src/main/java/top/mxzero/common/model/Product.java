package top.mxzero.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName(autoResultMap = true)
public class Product {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String code;
    private String title;
    private String introduction;
    private Integer categoryId;
    @TableField(exist = false)
    private Category category;
    private String mainPicture;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> subPictures;
    private Double toPrice;
    private Double costPrice;
    private String unit;
    private Integer stock;
    private boolean hot;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    @JsonIgnore
    private boolean deleted;
}
