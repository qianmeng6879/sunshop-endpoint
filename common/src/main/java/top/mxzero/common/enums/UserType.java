package top.mxzero.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    user(1, "user"),
    staff(2, "staff");
    @EnumValue
    private int value;
    public String name;
}
