package com.wits.recsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wits.recsys.pojo.po.RecruitPosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper extends BaseMapper<RecruitPosition> {
}
