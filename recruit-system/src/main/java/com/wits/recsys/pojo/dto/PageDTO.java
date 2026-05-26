package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageDTO {
    @Min(value = 1, message = ("页码最小为1"))
    private Long pageNum = 1L;

    @Min(value = 1, message = ("每页条数最小为1"))
    private Long pageSize = 10L;
}
