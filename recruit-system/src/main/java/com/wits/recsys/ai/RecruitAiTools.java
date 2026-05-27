package com.wits.recsys.ai;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wits.recsys.common.enums.RecommendStatusEnum;
import com.wits.recsys.pojo.po.Recommend;
import com.wits.recsys.pojo.po.RecruitPosition;
import com.wits.recsys.pojo.vo.RecruitSummaryVO;
import com.wits.recsys.service.PositionService;
import com.wits.recsys.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecruitAiTools {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final PositionService positionService;
    private final RecommendService recommendService;

    @Tool(description = "获取所有有效岗位的招聘数据：岗位名称，总招聘人数，已推荐人数，已入职人数，剩余招聘数，团队每日招聘产能，岗位招聘状态")
    public List<Map<String, Object>> getFullRecruitData() {
        List<RecruitPosition> positionList = positionService.list(
                Wrappers.lambdaQuery(RecruitPosition.class)
                        .eq(RecruitPosition::getStatus, 1)
        );


        return positionList.stream().map(pos -> {
            Map<String, Object> data = new HashMap<>();
            data.put("positionName", pos.getPositionName());
            data.put("totalRecruit", pos.getRecruitCount());

            long recommendNum = recommendService.count(
                    Wrappers.lambdaQuery(Recommend.class)
                            .eq(Recommend::getPositionId, pos.getId())
            );
            data.put("recommendCount", recommendNum);

            long hireNum = recommendService.count(
                    Wrappers.lambdaQuery(Recommend.class)
                            .eq(Recommend::getPositionId, pos.getId())
                            .eq(Recommend::getRecommendStatus, RecommendStatusEnum.EMPLOYED)
            );
            data.put("hireCount", hireNum);
            int remainCount = pos.getRecruitCount() - (int) hireNum;
            // 兜底：名额不能为负数
            remainCount = Math.max(remainCount, 0);
            data.put("remainCount", remainCount);

            // 可选：传递团队日产能，用于AI结合产能计算推荐目标
            data.put("teamDailyCapacity", pos.getTeamDailyCapacity());

            data.put("positionStatus", pos.getStatus());

            return data;
        }).collect(Collectors.toList());
    }

    private static final String BASE_PATH = "output/recruit_statistics";

    @Tool(description = "接收招聘统计数据列表，生成excel文件并保存到本地，入参为统计数据集合")
    public String generateRecruitExcel(@ToolParam(description = "统计结果数据")List<RecruitSummaryVO> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return "数据为空，无法生成excel表格";
        }
        String nowTime = LocalDateTime.now().format(FORMATTER);
        String excelPath = BASE_PATH + "_" + nowTime + ".xlsx";

        File file = new File(excelPath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            boolean createSuccess = parentDir.mkdirs();
            if (!createSuccess) {
                return "目录创建失败，无法生成excel";
            }
        }

        try {
            EasyExcel.write(new File(excelPath), RecruitSummaryVO.class)
                    .sheet("招聘数据统计表")
                    .doWrite(dataList);
            File file1 = new File(excelPath);
            return "Excel生成成功，文件路径： " + file1.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "Excel生成失败: 原因: " + e.getMessage();

        }

    }

}
