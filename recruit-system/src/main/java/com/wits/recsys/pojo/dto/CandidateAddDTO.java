package com.wits.recsys.pojo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandidateAddDTO {
    @NotBlank(message = "候选人姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;
    @NotBlank(message = "候选人电话不能为空")
    @Size(max = 20, message = "联系电话长度不能超过20个字符")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    @NotBlank(message = "候选人邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;
    @NotBlank(message = "候选人学历不能为空")
    @Size(max = 20, message = "学历长度不能超过20个字符")
    private String education;
    private String workExperience;



}
