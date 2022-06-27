package top.mxzero.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductVO {
    @Length(max = 20)
    private String code;
    @NotBlank(message = "title not blank")
    private String title;
    @NotBlank(message = "title not blank")
    private String introduction;
    @NotNull(message = "title not null")
    private Integer categoryId;
    @NotNull(message = "imageId not null")
    private Long imageId;
    private List<Long> images;
    @NotNull(message = "toPrice not null")
    private Double toPrice;
    @NotNull(message = "costPrice not null")
    private Double costPrice;
    @NotBlank(message = "unit not blank")
    private String unit;
    @NotNull(message = "stock not null")
    private Integer stock;
    private boolean hot;
}
