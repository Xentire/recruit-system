package com.wits.recsys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wits.recsys.common.enums.RecommendStatusEnum;
import com.wits.recsys.common.exception.BusinessException;
import com.wits.recsys.mapper.RecommendMapper;
import com.wits.recsys.pojo.dto.RecommendAddDTO;
import com.wits.recsys.pojo.dto.RecommendPageDTO;
import com.wits.recsys.pojo.dto.RecommendUpdateDTO;
import com.wits.recsys.pojo.po.Recommend;
import com.wits.recsys.pojo.po.RecruitCandidate;
import com.wits.recsys.pojo.po.RecruitPosition;
import com.wits.recsys.pojo.vo.RecommendVO;
import com.wits.recsys.service.CandidateService;
import com.wits.recsys.service.PositionService;
import com.wits.recsys.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    private final CandidateService candidateService;
    private final PositionService positionService;


    @Override
    public void addRecommend(RecommendAddDTO recommendAddDTO) {
        Recommend recommend = new Recommend();

        RecruitCandidate candidate = candidateService.getById(recommendAddDTO.getCandidateId());
        if (candidate == null) {
            throw new BusinessException("该候选人不存在");
        }
        RecruitPosition position = positionService.getById(recommendAddDTO.getPositionId());
        if (position == null) {
            throw new BusinessException("该岗位不存在");
        }
        Long count = this.lambdaQuery()
                .eq(Recommend::getCandidateId, recommendAddDTO.getCandidateId())
                        .eq(Recommend::getPositionId, recommendAddDTO.getPositionId())
                                .count();
        if (count > 0) {
            throw new BusinessException("该候选人已推荐至当前岗位，请勿重复操作");
        }

        BeanUtil.copyProperties(recommendAddDTO, recommend);

        recommend.setUpdateTime(LocalDateTime.now());
        recommend.setRecommendTime(LocalDateTime.now());
        recommend.setCreateTime(LocalDateTime.now());

        boolean suc = this.save(recommend);
        if (!suc) {
            throw new BusinessException("推荐失败");
        }

    }

    @Override
    public void updateRecommend(RecommendUpdateDTO recommendUpdateDTO) {
        Recommend recommendId = this.getById(recommendUpdateDTO.getId());
        if (recommendId == null) {
            throw new BusinessException("推荐信息不存在");
        }

        Recommend recommend = new Recommend();
        recommend.setId(recommendUpdateDTO.getId());

        recommend.setUpdateTime(LocalDateTime.now());
        if (recommendUpdateDTO.getRecommendStatus().equals(RecommendStatusEnum.INTERVIEW_FAIL.getValue()) || recommendUpdateDTO.getRecommendStatus().equals(RecommendStatusEnum.INTERVIEW_PASS.getValue())) {
            recommend.setInterviewTime(LocalDateTime.now());
        }

        recommend.setRecommendStatus(RecommendStatusEnum.getByValue(recommendUpdateDTO.getRecommendStatus()));

        boolean suc = this.updateById(recommend);
        if (!suc) {
            throw new BusinessException("更新失败");
        }
    }

    @Override
    public IPage<RecommendVO> list(RecommendPageDTO recommendPageDTO) {
        Page<Recommend> rePage = new Page<>(recommendPageDTO.getPageNum(), recommendPageDTO.getPageSize());

        LambdaQueryWrapper<Recommend> wrapper = Wrappers.lambdaQuery();

        Integer statusVal = recommendPageDTO.getRecommendStatus();
        if (statusVal != null) {
            RecommendStatusEnum status = RecommendStatusEnum.getByValue(statusVal);
            wrapper.eq(Recommend::getRecommendStatus, status);
        }

        wrapper.orderByDesc(Recommend::getCreateTime);

        IPage<Recommend> poPage = this.page(rePage, wrapper);

        return poPage.convert(po -> {
            RecommendVO recommendVO = new RecommendVO();
            BeanUtil.copyProperties(po, recommendVO);

            RecruitPosition positionName = positionService.getById(po.getPositionId());
            RecruitCandidate candidateName = candidateService.getById(po.getCandidateId());
            recommendVO.setCandidateName(candidateName.getName());
            recommendVO.setPositionName(positionName.getPositionName());

            RecommendStatusEnum status = po.getRecommendStatus();
            if (status != null) {
                recommendVO.setRecommendStatusDesc(status.getDesc());
                recommendVO.setRecommendStatus(status.getValue());
            }

            return recommendVO;
        });




//        LambdaQueryWrapper<RecommendVO> wrapper = new LambdaQueryWrapper<>();
//
//        wrapper.eq(recommendPageDTO.getRecommendStatus() != null, RecommendVO::getRecommendStatus, recommendPageDTO.getRecommendStatus())
//                .like(StringUtils.hasText(recommendPageDTO.getCandidateName()), RecommendVO::getCandidateName, recommendPageDTO.getCandidateName())
//                .like(StringUtils.hasText(recommendPageDTO.getPositionName()), RecommendVO::getPositionName, recommendPageDTO.getPositionName());
//
//        return this.page(rePage, wrapper).convert(po -> {
//            RecommendVO vo = new RecommendVO();
//            BeanUtil.copyProperties(po, vo);
//
//            RecommendStatusEnum status = po.getRecommendStatus();
//        });



    }
}
