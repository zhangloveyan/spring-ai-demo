package com.zz.demoai.config;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zz.demoai.bean.ChatDetail;
import com.zz.demoai.service.ChatDetailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Component
public class MysqlChatMemory implements ChatMemory {
    @Resource
    ChatDetailService chatDetailService;

    @Override
    public void add(String conversationId, List<Message> messages) {
        log.info("id:{} messages:{}", conversationId, messages);
        messages.forEach(message -> {
                    String jsonString = JSONObject.toJSONString(message);
                    ChatDetail entity = new ChatDetail();
                    entity.setChatId(Long.parseLong(conversationId));
                    entity.setRole(message.getMessageType().getValue());
                    entity.setContent(jsonString);
                    chatDetailService.save(entity);
                }
        );
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        log.info("id:{} lastN:{}", conversationId, lastN);

        List<ChatDetail> list = chatDetailService.lambdaQuery()
                .eq(ChatDetail::getChatId, Long.parseLong(conversationId))
                .orderByDesc(ChatDetail::getId)
                .last("limit " + lastN)
                .list();

        List<Message> messageList = new ArrayList<>();
        for (ChatDetail chatDetail : list) {
            // 也可通过 jsonObject.get("messageType") 获取
            String role = chatDetail.getRole();
            String content = chatDetail.getContent();
            JSONObject jsonObject = JSONObject.parseObject(content);
            log.info("role:{} content:{}", role, content);
            if ("user".equals(role)) {
                UserMessage userMessage = new UserMessage(jsonObject.getString("text"));
                messageList.add(userMessage);
            } else if ("assistant".equals(role)) {
                AssistantMessage assistantMessage = new AssistantMessage(jsonObject.getString("text"));
                messageList.add(assistantMessage);
            }
        }
        return messageList;
    }

    @Override
    public void clear(String conversationId) {
        LambdaQueryWrapper<ChatDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatDetail::getChatId, conversationId);
        chatDetailService.remove(queryWrapper);
    }
}
