package com.wits.recsys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wits.recsys.common.result.Result;
import com.wits.recsys.pojo.dto.CandidateAddDTO;
import com.wits.recsys.pojo.dto.CandidatePageDTO;
import com.wits.recsys.pojo.dto.CandidateUpdateDTO;
import com.wits.recsys.pojo.vo.CandidateVO;
import com.wits.recsys.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;


    @PostMapping("/add")
    public Result<Void> addCandidate(@Valid @RequestBody CandidateAddDTO candidateAddDTO) {
        candidateService.add(candidateAddDTO);

        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateCandidate(@Valid @RequestBody CandidateUpdateDTO candidateUpdateDTO) {
        candidateService.updateCandidate(candidateUpdateDTO);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<IPage<CandidateVO>> list(@Valid CandidatePageDTO candidatePageDTO) {
        IPage<CandidateVO> candidateList = candidateService.list(candidatePageDTO);
        return Result.success(candidateList);
    }



}
