package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.DeviceInfo;
import com.zz.demoai.service.DeviceInfoService;
import com.zz.demoai.mapper.DeviceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zln
* @description 针对表【device_info】的数据库操作Service实现
* @createDate 2025-04-15 18:19:47
*/
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo>
    implements DeviceInfoService{

}




