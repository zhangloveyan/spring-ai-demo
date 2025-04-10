package com.zz.demoai.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.ChatDetail;
import com.zz.demoai.config.MysqlChatMemory;
import com.zz.demoai.mapper.ChatDetailMapper;
import com.zz.demoai.service.ChatDetailService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zln
 * @description 针对表【chat_detail】的数据库操作Service实现
 * @createDate 2025-04-09 18:26:58
 */
@Service
public class ChatDetailServiceImpl extends ServiceImpl<ChatDetailMapper, ChatDetail>
        implements ChatDetailService {
    @Resource
    MysqlChatMemory chatMemory;

    @Override
    public List<ChatDetail> detailList(Long userId, Long chatId) {
        // 可以直接使用数据库查询
//        List<ChatDetail> list = lambdaQuery().eq(ChatDetail::getChatId, chatId).orderByDesc(ChatDetail::getId).list();
//        return list.stream().peek(chatDetail -> {
//            String content = chatDetail.getContent();
//            JSONObject jsonObject = JSON.parseObject(content);
//            chatDetail.setContent(jsonObject.getString("text"));
//        }).collect(Collectors.toList());

        // 也可以使用 chatmemory 查询
        List<Message> messageList = chatMemory.get(String.valueOf(chatId), 10);
        return  messageList.stream().map(message -> {
            ChatDetail chatDetail = new ChatDetail();
            chatDetail.setChatId(chatId);
            chatDetail.setRole(message.getMessageType().getValue());
            chatDetail.setContent(message.getText());
            return chatDetail;
        }).collect(Collectors.toList());
    }
}




