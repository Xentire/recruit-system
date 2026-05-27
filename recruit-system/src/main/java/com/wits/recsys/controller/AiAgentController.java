package com.wits.recsys.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiAgentController {

    private final ChatClient chatClient;

    @GetMapping("/recruit/stat")
    public String recruitStat() {
        return chatClient.prompt()
                .user("请完成招聘数据统计并且生成表格数据")
                .call()
                .content();
    }
}
