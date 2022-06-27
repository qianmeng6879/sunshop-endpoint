package top.mxzero.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
    canceled(0, "canceled"),
    unpaid(1, "unpaid"),
    paid(2, "paid"),
    shipped(3, "shipped"),
    completed(4, "completed");

    @EnumValue
    private final int value;
    private final String label;

    public static OrderState transition(int value) {
        if (value == 1) {
            return unpaid;
        }

        if (value == 2) {
            return paid;
        }

        if (value == 3) {
            return shipped;
        }

        if (value == 4) {
            return canceled;
        }

        if (value == 5) {
            return completed;
        }

        return null;
    }

    public static OrderState transition(String name) {
        if (name == null || "".equals(name.trim())) {
            return null;
        }

        if ("unpaid".equals(name)) {
            return unpaid;
        }

        if ("paid".equals(name)) {
            return paid;
        }

        if ("shipped".equals(name)) {
            return shipped;
        }

        if ("canceled".equals(name)) {
            return canceled;
        }

        if ("completed".equals(name)) {
            return completed;
        }

        return null;
    }
}
