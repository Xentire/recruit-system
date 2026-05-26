package com.wits.recsys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wits.recsys.common.result.Result;
import com.wits.recsys.pojo.dto.RecommendAddDTO;
import com.wits.recsys.pojo.dto.RecommendPageDTO;
import com.wits.recsys.pojo.dto.RecommendUpdateDTO;
import com.wits.recsys.pojo.po.Recommend;
import com.wits.recsys.pojo.vo.RecommendVO;
import com.wits.recsys.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping("/add")
    public Result<Void> addRecommend(@RequestBody RecommendAddDTO recommendAddDTO) {
        recommendService.addRecommend(recommendAddDTO);

        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateRecommend(@RequestBody RecommendUpdateDTO recommendUpdateDTO) {
        recommendService.updateRecommend(recommendUpdateDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<IPage<RecommendVO>> list(RecommendPageDTO recommendPageDTO) {
         IPage<RecommendVO> page = recommendService.list(recommendPageDTO);

         return Result.success(page);
    }

}
