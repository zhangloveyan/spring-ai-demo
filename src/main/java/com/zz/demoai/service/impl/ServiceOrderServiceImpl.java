package com.zz.demoai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.demoai.bean.ServiceOrder;
import com.zz.demoai.service.ServiceOrderService;
import com.zz.demoai.mapper.ServiceOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author zln
* @description 针对表【service_order】的数据库操作Service实现
* @createDate 2025-04-15 18:13:03
*/
@Service
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderMapper, ServiceOrder>
    implements ServiceOrderService{

}




