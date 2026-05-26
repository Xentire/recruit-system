package com.wits.recsys.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@TableName("recruit_candidate")
public class RecruitCandidate {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String education;
    private String workExperience;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //private Integer isDeleted;
}
