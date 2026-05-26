package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PositionAddDTO {
    @NotBlank(message = "岗位名称不能为空")
    private String positionName;
    @NotBlank(message = "所属小组不能为空")
    private String team;
    @NotNull(message = "招聘人数不能为0")
    @Min(value = 1, message = "招聘人数必须大于等于1")
    private Integer recruitCount;
    @NotBlank(message = "工作城市不能为空")
    private String workCity;
    private String requiredSkills;
    private Integer status = 1;
    private Integer teamDailyCapacity = 10;
}
