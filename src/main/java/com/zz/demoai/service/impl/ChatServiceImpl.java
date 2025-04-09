package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.Chat;
import com.zz.demoai.service.ChatService;
import com.zz.demoai.mapper.ChatMapper;
import org.springframework.stereotype.Service;

/**
* @author zln
* @description 针对表【chat】的数据库操作Service实现
* @createDate 2025-04-09 18:20:05
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

}




