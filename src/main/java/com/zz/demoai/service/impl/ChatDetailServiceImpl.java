package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.Chat;
import com.zz.demoai.bean.ChatDetail;
import com.zz.demoai.mapper.ChatDetailMapper;
import com.zz.demoai.service.ChatDetailService;
import com.zz.demoai.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    ChatService chatService;

    @Override
    public List<ChatDetail> detailList(Long userId, Long id) {
        List<Chat> list = chatService.lambdaQuery().eq(Chat::getUserId, userId).list();
        if (list.isEmpty()){
            return null;
        }
        List<Long> collect = list.stream().map(Chat::getId).collect(Collectors.toList());
        return lambdaQuery().eq(ChatDetail::getChatId, id).in(ChatDetail::getChatId, collect).list();
    }
}




