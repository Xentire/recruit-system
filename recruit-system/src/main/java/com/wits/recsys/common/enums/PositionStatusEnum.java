// PositionStatusEnum.java
package com.wits.recsys.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum PositionStatusEnum implements IEnum<Integer> {
    RECRUITING(1, "招聘中"),
    PAUSED(2, "已暂停"),
    CLOSED(3, "已关闭");

    private final Integer value;
    private final String desc;

    PositionStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static PositionStatusEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }

        for (PositionStatusEnum status : PositionStatusEnum.values()) {
            if (status.value == value) return status;
        }
        throw new IllegalArgumentException("无效的岗位状态值: " + value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}