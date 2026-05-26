package com.wits.recsys.pojo.po;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wits.recsys.common.enums.PositionStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@TableName("recruit_position")
@Data
public class RecruitPosition {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String positionName;

    private String team;

    private Integer recruitCount;

    private String workCity;

    private String requiredSkills;

    private PositionStatusEnum status;

    private Integer teamDailyCapacity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    //private Integer isDeleted;

}
