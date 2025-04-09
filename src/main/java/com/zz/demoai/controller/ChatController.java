package com.zz.demoai.controller;

import com.zz.demoai.bean.Chat;
import com.zz.demoai.bean.ChatDetail;
import com.zz.demoai.config.R;
import com.zz.demoai.service.ChatDetailService;
import com.zz.demoai.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class ChatController {
    @Resource
    ChatClient chatClient;
    @Resource
    ChatService chatService;
    @Resource
    ChatDetailService chatDetailService;

    @RequestMapping("/chat/call")
    public String chat(@RequestParam String prompt) {
        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam String prompt, @RequestParam String chatId) {
        return chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(
                        AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId
                ))
                .user(prompt)
                .stream()
                .content();
    }

    @GetMapping("/chat/list")
    public R<List<Chat>> list(@RequestParam("userId") Long userId) {
        List<Chat> list = chatService.listByUserId(userId);
        return R.ok(list);
    }

    @GetMapping("/chat/detail/{id}")
    public R<List<ChatDetail>> detailList(@RequestParam("userId") Long userId, @RequestParam("id") Long id) {
        List<ChatDetail> list = chatDetailService.detailList(userId, id);
        return R.ok(list);
    }
}
