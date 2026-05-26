package com.wits.recsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wits.recsys.pojo.po.RecruitCandidate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CandidateMapper extends BaseMapper<RecruitCandidate> {
}
