package top.mxzero.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddressVO {
    @NotBlank(message = "province not blank")
    private String province;
    @NotBlank(message = "city not blank")
    private String city;
    @NotBlank(message = "area not blank")
    private String area;
    @NotBlank(message = "detail not blank")
    private String detail;
    @NotBlank(message = "name not blank")
    private String name;
    @NotBlank(message = "phone not blank")
    @Length(max = 11, min = 11, message = "phone length must 11")
    private String phone;
    private String postalCode;
}
