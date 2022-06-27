package top.mxzero.common.vo;

import lombok.Data;
import top.mxzero.common.enums.UserType;

@Data
public class UserEditVO {
    private String username;
    private String nickname;
    private UserType userType;
}
