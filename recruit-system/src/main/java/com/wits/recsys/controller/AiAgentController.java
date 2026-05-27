package com.wits.recsys.controller;

import com.wits.recsys.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiAgentController {

    private final ChatClient chatClient;

    @PostMapping("/recruit/chat")
    public Result<String> recruitStat(@RequestBody String question) {
        return Result.success(chatClient.prompt()
                .user(question)
                .call()
                .content());
    }
}
