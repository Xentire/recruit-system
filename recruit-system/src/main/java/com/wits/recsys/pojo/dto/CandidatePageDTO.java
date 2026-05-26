package com.wits.recsys.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CandidatePageDTO extends PageDTO{

    private String name;
    private String education;
}
