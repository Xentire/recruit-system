package com.wits.recsys.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String education;
    private String workExperience;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
