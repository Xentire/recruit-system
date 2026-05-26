package com.wits.recsys.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wits.recsys.common.enums.RecommendStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("recommend")
public class Recommend {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotNull(message = "推荐岗位不能为空")
    private Long positionId;
    @NotNull(message = "推荐候选人不能为空")
    private Long candidateId;
    private RecommendStatusEnum recommendStatus;
    private LocalDateTime recommendTime;
    private LocalDateTime interviewTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
