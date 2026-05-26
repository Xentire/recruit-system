package com.wits.recsys.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum RecommendStatusEnum implements IEnum<Integer> {
    RECOMMENDED(1, "已推荐"),
    WAIT_INTERVIEW(2, "待面试"),
    INTERVIEW_PASS(3, "面试通过"),
    EMPLOYED(4, "已入职"),
    INTERVIEW_FAIL(5, "面试淘汰");

    private final Integer value;
    private final String desc;

    RecommendStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RecommendStatusEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }

        for (RecommendStatusEnum status : RecommendStatusEnum.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}