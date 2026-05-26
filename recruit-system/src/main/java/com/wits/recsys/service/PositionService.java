package com.wits.recsys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wits.recsys.pojo.dto.PositionAddDTO;
import com.wits.recsys.pojo.dto.PositionPageDTO;
import com.wits.recsys.pojo.dto.PositionUpdateDTO;
import com.wits.recsys.pojo.po.RecruitPosition;
import com.wits.recsys.pojo.vo.PositionVO;

public interface PositionService extends IService<RecruitPosition> {

    void add(PositionAddDTO positionAddDTO);

    void update(PositionUpdateDTO positionUpdateDTO);

    IPage<PositionVO> list(PositionPageDTO positionPageDTO);
}
