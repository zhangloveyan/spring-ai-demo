package com.zz.demoai.service;

import com.zz.demoai.bean.Chat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zln
* @description 针对表【chat】的数据库操作Service
* @createDate 2025-04-09 18:20:05
*/
public interface ChatService extends IService<Chat> {

    List<Chat> listByUserId(Long userId);

    Chat create(Long userId);

    void checkUserAndChat(Long userId, Long chatId);
}
