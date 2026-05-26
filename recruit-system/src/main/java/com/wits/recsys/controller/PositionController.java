package com.wits.recsys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wits.recsys.common.result.Result;
import com.wits.recsys.pojo.dto.PositionAddDTO;
import com.wits.recsys.pojo.dto.PositionPageDTO;
import com.wits.recsys.pojo.dto.PositionUpdateDTO;
import com.wits.recsys.pojo.vo.PositionVO;
import com.wits.recsys.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/position")
public class PositionController {

    // 标准注入方式，规避构造注入坑
    private final PositionService positionService;

    // 新增岗位接口
    @PostMapping("/add")
    public Result<Void> addPosition(@Valid @RequestBody PositionAddDTO positionAddDTO) {
        positionService.add(positionAddDTO);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updatePosition(@Valid @RequestBody PositionUpdateDTO positionUpdateDTO) {
        positionService.update(positionUpdateDTO);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<IPage<PositionVO>> list(@Valid PositionPageDTO positionPageDTO) {
        IPage<PositionVO> res = positionService.list(positionPageDTO);

        return Result.success(res);
    }
}