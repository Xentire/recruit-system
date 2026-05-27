package com.wits.recsys.config;

import com.wits.recsys.ai.AiPromptConst;
import com.wits.recsys.ai.RecruitAiTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AiConfig {
    private final RecruitAiTools recruitAiTools;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, RecruitAiTools recruitAiTools) {
        return chatClientBuilder
                .defaultSystem(AiPromptConst.SYSTEM_PROMPT)
                .defaultTools(recruitAiTools)
                .build();
    }
}
