package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecommendAddDTO {
    @NotNull(message = "推荐岗位不能为空")
    private Long positionId;
    @NotNull(message = "推荐候选人不能为空")
    private Long candidateId;
}
