package top.mxzero.common.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderVO {
    @NotNull(message = "productId not null")
    private Long productId;
    @NotNull(message = "quantity not null")
    @Max(value = 99, message = "max 99")
    @Min(value = 1, message = "min 1")
    private Integer quantity;
    @NotNull(message = "addressId not null")
    private Long addressId;
}
