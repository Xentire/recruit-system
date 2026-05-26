package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecommendUpdateDTO {
    @NotNull(message = "推荐信息id不能为空")
    private Long id;
    @NotNull(message = "修改的状态不能为空")
    @Min(value = 1, message = "状态异常")
    @Max(value = 5, message = "状态异常")
    private Integer recommendStatus;
}
