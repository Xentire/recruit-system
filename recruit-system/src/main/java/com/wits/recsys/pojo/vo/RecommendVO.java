package com.wits.recsys.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecommendVO {
    private Long id;
    private String positionName;
    private Long positionId;
    private String candidateName;
    private Long candidateId;

    private Integer recommendStatus;
    private String recommendStatusDesc;

    private LocalDateTime recommendTime;
    private LocalDateTime interviewTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
