package com.zz.demoai.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MysqlChatMemory implements ChatMemory {
    @Override
    public void add(String conversationId, List<Message> messages) {

    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        return List.of();
    }

    @Override
    public void clear(String conversationId) {

    }
}
