package com.zz.demoai.controller;

import com.zz.demoai.bean.Chat;
import com.zz.demoai.bean.ChatDetail;
import com.zz.demoai.config.R;
import com.zz.demoai.service.ChatDetailService;
import com.zz.demoai.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@RestController
@RequestMapping("/ai")
public class ChatController {
    @Resource
    ChatClient chatClient;
    @Resource
    ChatService chatService;
    @Resource
    ChatDetailService chatDetailService;

    /**
     * 等有结果再返回 非流式
     * @param prompt
     * @return
     */
    @RequestMapping("/chat/call")
    public String chat(@RequestParam String prompt) {
        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }


    /**
     * 对话列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/chat/list")
    public R<List<Chat>> list(@RequestParam("userId") Long userId) {
        List<Chat> list = chatService.listByUserId(userId);
        return R.ok(list);
    }

    /**
     * 对话详情
     *
     * @param userId
     * @param chatId
     * @return
     */
    @GetMapping("/chat/detail/{id}")
    public R<List<ChatDetail>> detailList(@RequestParam("userId") Long userId, @PathVariable("chatId") Long chatId) {
        chatService.checkUserAndChat(userId,chatId);
        List<ChatDetail> list = chatDetailService.detailList(userId, chatId);
        return R.ok(list);
    }

    /**
     * 对话创建
     *
     * @param userId
     * @return
     */
    @PostMapping("/chat")
    public R<Chat> create(@RequestParam("userId") Long userId) {
        Chat chat = chatService.create(userId);
        return R.ok(chat);
    }

    /**
     * 开始对话
     *
     * @param prompt
     * @param chatId
     * @return
     */
//    @RequestMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("userId") Long userId,
                             @RequestParam Long chatId,
                             @RequestParam String prompt) {
        chatService.checkUserAndChat(userId,chatId);
        return chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(prompt)
                .stream()
                .content();
    }

    @Resource
    ChatClient deepSeekChatClient;

    @RequestMapping(value = "/chat-dp", produces = "text/html;charset=utf-8")
    public Flux<String> deepSeekChat(@RequestParam("userId") Long userId,
                             @RequestParam Long chatId,
                             @RequestParam String prompt) {
        chatService.checkUserAndChat(userId,chatId);
        return deepSeekChatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(prompt)
                .stream()
                .content();
    }
}
