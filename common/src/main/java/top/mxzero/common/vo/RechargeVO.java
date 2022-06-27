package top.mxzero.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RechargeVO {
    @NotNull
    private Long amount;
}
