package com.wits.recsys.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecommendPageDTO extends PageDTO{
    private Integer recommendStatus;
}
