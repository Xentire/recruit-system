package com.wits.recsys.pojo.vo;

import com.wits.recsys.common.enums.PositionStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PositionVO {
    private Long id;
    private String positionName;
    private String team;
    private Integer recruitCount;
    private String workCity;
    private String requiredSkills;

    // 状态数值
    private Integer status;
    // 状态中文名称，前端直接展示
    private String statusDesc;

    private Integer teamDailyCapacity;
    private LocalDateTime createTime;

    // 枚举转中文描述
    public void setStatusEnum(PositionStatusEnum statusEnum) {
        if (statusEnum != null) {
            this.status = statusEnum.getValue();
            this.statusDesc = statusEnum.getDesc();
        }
    }
}