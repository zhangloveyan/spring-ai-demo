package com.zz.demoai.service;

import com.zz.demoai.bean.ChatDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zln
* @description 针对表【chat_detail】的数据库操作Service
* @createDate 2025-04-09 18:26:58
*/
public interface ChatDetailService extends IService<ChatDetail> {

    List<ChatDetail> detailList(Long userId, Long id);
}
