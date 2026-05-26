package com.wits.recsys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wits.recsys.common.exception.BusinessException;
import com.wits.recsys.mapper.CandidateMapper;
import com.wits.recsys.pojo.dto.CandidateAddDTO;
import com.wits.recsys.pojo.dto.CandidatePageDTO;
import com.wits.recsys.pojo.dto.CandidateUpdateDTO;
import com.wits.recsys.pojo.po.RecruitCandidate;
import com.wits.recsys.pojo.vo.CandidateVO;
import com.wits.recsys.service.CandidateService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, RecruitCandidate> implements CandidateService {
    @Override
    public void add(CandidateAddDTO candidateAddDTO) {
        RecruitCandidate recruitCandidate = new RecruitCandidate();
        BeanUtil.copyProperties(candidateAddDTO, recruitCandidate);

        recruitCandidate.setUpdateTime(LocalDateTime.now());
        recruitCandidate.setCreateTime(LocalDateTime.now());

        boolean suc = this.save(recruitCandidate);

        if (!suc) {
            throw new BusinessException("候选人新增失败");
        }
    }

    @Override
    public void updateCandidate(CandidateUpdateDTO candidateUpdateDTO) {

        Long id = candidateUpdateDTO.getId();
        boolean exists = lambdaQuery()
                .eq(RecruitCandidate::getId, id)
                .exists();
        if (!exists) {
            throw new BusinessException("该候选人信息不存在，无法修改");
        }

        RecruitCandidate candidate = new RecruitCandidate();
        BeanUtil.copyProperties(candidateUpdateDTO, candidate);

        candidate.setUpdateTime(LocalDateTime.now());

        boolean suc = this.updateById(candidate);
        if (!suc) {
            throw new BusinessException("候选人修改失败");
        }
    }

    @Override
    public IPage<CandidateVO> list(CandidatePageDTO candidatePageDTO) {
        Page<RecruitCandidate> caPage = new Page<>(candidatePageDTO.getPageNum(), candidatePageDTO.getPageSize());

        LambdaQueryWrapper<RecruitCandidate> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(candidatePageDTO.getName()), RecruitCandidate::getName, candidatePageDTO.getName())
                .like(StringUtils.hasText(candidatePageDTO.getEducation()), RecruitCandidate::getEducation, candidatePageDTO.getEducation());
        wrapper.orderByDesc(RecruitCandidate::getCreateTime);

        return this.page(caPage, wrapper).convert(po -> {
            CandidateVO vo = new CandidateVO();
            BeanUtil.copyProperties(po, vo);
            return vo;
        });
    }
}
