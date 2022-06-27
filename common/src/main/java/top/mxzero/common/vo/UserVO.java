package top.mxzero.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {
    @NotBlank
    @Length(max = 10, min = 2)
    private String username;
    @NotBlank
    @Length(max = 16, min = 4)
    private String password;

    private String nickname;
}
