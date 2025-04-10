package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.Chat;
import com.zz.demoai.mapper.ChatMapper;
import com.zz.demoai.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zln
 * @description 针对表【chat】的数据库操作Service实现
 * @createDate 2025-04-09 18:20:05
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
        implements ChatService {

    @Override
    public List<Chat> listByUserId(Long userId) {
        return lambdaQuery().eq(Chat::getUserId, userId).list();
    }


    @Override
    public Chat create(Long userId) {
        Chat chat = new Chat();
        chat.setUserId(userId);
        chat.setTitle("测试标题" + System.currentTimeMillis());
        save(chat);
        return chat;
    }

    @Override
    public void checkUserAndChat(Long userId, Long chatId) {
        // 判断该问题是否是用户所有
    }
}




