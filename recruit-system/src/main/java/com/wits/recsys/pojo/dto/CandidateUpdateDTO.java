package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateUpdateDTO {
    @NotNull(message = "候选人id不能为空")
    private Long id;
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;
    @Size(max = 20, message = "联系电话长度不能超过20个字符")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;
    @Size(max = 20, message = "学历长度不能超过20个字符")
    private String education;
    private String workExperience;
}
