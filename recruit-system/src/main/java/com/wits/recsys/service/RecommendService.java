package com.wits.recsys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wits.recsys.pojo.dto.RecommendAddDTO;
import com.wits.recsys.pojo.dto.RecommendPageDTO;
import com.wits.recsys.pojo.dto.RecommendUpdateDTO;
import com.wits.recsys.pojo.po.Recommend;
import com.wits.recsys.pojo.vo.RecommendVO;

public interface RecommendService extends IService<Recommend> {
    void addRecommend(RecommendAddDTO recommendAddDTO);

    void updateRecommend(RecommendUpdateDTO recommendUpdateDTO);

    IPage<RecommendVO> list(RecommendPageDTO recommendPageDTO);
}
