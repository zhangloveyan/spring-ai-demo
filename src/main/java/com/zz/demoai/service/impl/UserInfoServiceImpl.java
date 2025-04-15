package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.UserInfo;
import com.zz.demoai.service.UserInfoService;
import com.zz.demoai.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zln
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2025-04-15 18:13:03
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




