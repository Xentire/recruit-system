package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PositionUpdateDTO {

    @NotNull(message = "岗位id不能为空")
    private Long id;
    private String positionName;
    private String team;
    @Min(value = 1, message = "招聘人数必须大于等于1")
    private Integer recruitCount;
    private String workCity;
    private String requiredSkills;
    private Integer status;
    private Integer teamDailyCapacity;

}
