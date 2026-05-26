package com.wits.recsys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wits.recsys.common.enums.PositionStatusEnum;
import com.wits.recsys.common.exception.BusinessException;
import com.wits.recsys.mapper.PositionMapper;
import com.wits.recsys.pojo.dto.PositionAddDTO;
import com.wits.recsys.pojo.dto.PositionPageDTO;
import com.wits.recsys.pojo.dto.PositionUpdateDTO;
import com.wits.recsys.pojo.po.RecruitPosition;
import com.wits.recsys.pojo.vo.PositionVO;
import com.wits.recsys.service.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, RecruitPosition> implements PositionService {

    @Override
    public void add(PositionAddDTO positionAddDTO) {

        RecruitPosition position = new RecruitPosition();
        BeanUtil.copyProperties(positionAddDTO, position);

        position.setCreateTime(LocalDateTime.now());
        position.setUpdateTime(LocalDateTime.now());

        boolean suc = save(position);
        if (!suc) {
            throw new BusinessException("岗位新增失败");
        }
    }

    @Override
    public void update(PositionUpdateDTO positionUpdateDTO) {

        Long id = positionUpdateDTO.getId();
        boolean exists = this.lambdaQuery()
                .eq(RecruitPosition::getId, id)
                .exists();
        if (!exists) {
            throw new BusinessException("该岗位信息不存在，无法修改");
        }

        RecruitPosition position = new RecruitPosition();
        BeanUtil.copyProperties(positionUpdateDTO, position);
        position.setUpdateTime(LocalDateTime.now());
        if (positionUpdateDTO.getStatus() != null) {
            position.setStatus(PositionStatusEnum.getByValue(positionUpdateDTO.getStatus()));
        }
        position.setUpdateTime(LocalDateTime.now());
        boolean suc = this.updateById(position);

        if (!suc) {
            throw new BusinessException("岗位修改失败");
        }
    }

    @Override
    public IPage<PositionVO> list(PositionPageDTO positionPageDTO) {
        Page<RecruitPosition> poPage =  new Page<>(positionPageDTO.getPageNum(), positionPageDTO.getPageSize());
        LambdaQueryWrapper<RecruitPosition> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(positionPageDTO.getPositionName()), RecruitPosition::getPositionName, positionPageDTO.getPositionName())
                .like(StringUtils.hasText(positionPageDTO.getTeam()), RecruitPosition::getTeam, positionPageDTO.getTeam())
                .like(StringUtils.hasText(positionPageDTO.getWorkCity()), RecruitPosition::getWorkCity, positionPageDTO.getWorkCity())
                .eq(positionPageDTO.getStatus() != null, RecruitPosition::getStatus, positionPageDTO.getStatus());

        wrapper.orderByDesc(RecruitPosition::getCreateTime);

        IPage<RecruitPosition> pageResult = this.page(poPage, wrapper);
        List<RecruitPosition> poList = pageResult.getRecords();

        List<PositionVO> voList = BeanUtil.copyToList(poList, PositionVO.class);

        for (int i = 0; i < poList.size(); i++) {
            RecruitPosition po = poList.get(i);
            PositionVO vo = voList.get(i);
            vo.setStatusEnum(po.getStatus());
        }

        IPage<PositionVO> voPage = new Page<>();
        BeanUtil.copyProperties(pageResult, voPage);

        return voPage;
    }
}
