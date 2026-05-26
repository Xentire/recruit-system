package com.wits.recsys.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PositionPageDTO extends PageDTO {

    private String positionName;
    private String team;
    private String workCity;
    private Integer status;
}
