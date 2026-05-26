package com.wits.recsys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wits.recsys.pojo.dto.CandidateAddDTO;
import com.wits.recsys.pojo.dto.CandidatePageDTO;
import com.wits.recsys.pojo.dto.CandidateUpdateDTO;
import com.wits.recsys.pojo.po.RecruitCandidate;
import com.wits.recsys.pojo.vo.CandidateVO;
import jakarta.validation.Valid;

public interface CandidateService extends IService<RecruitCandidate> {

    void add(CandidateAddDTO candidateAddDTO);

    void updateCandidate(@Valid CandidateUpdateDTO candidateUpdateDTO);

    IPage<CandidateVO> list(@Valid CandidatePageDTO candidatePageDTO);
}
