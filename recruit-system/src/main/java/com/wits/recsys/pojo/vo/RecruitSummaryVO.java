package com.wits.recsys.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RecruitSummaryVO implements Serializable {

    @ExcelProperty("岗位名称")
    private String positionName;
    @ExcelProperty("计划招聘总人数")
    private Integer totalRecruit;
    @ExcelProperty("已推荐人数")
    private Integer recommendCount;
    @ExcelProperty("已入职人数")
    private Integer hireCount;
    @ExcelProperty("剩余招聘名额")
    private Integer remainCount;
    @ExcelProperty("本周推荐目标")
    private Integer weeklyTarget;
    @ExcelProperty("每日平均推荐目标")
    private Integer dailyTarget;

}
