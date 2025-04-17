package com.zz.demoai.tools;

import com.zz.demoai.bean.DeviceInfo;
import com.zz.demoai.bean.ServiceOrder;
import com.zz.demoai.bean.UserInfo;
import com.zz.demoai.bean.query.DeviceQuery;
import com.zz.demoai.bean.query.ServiceOrderSave;
import com.zz.demoai.bean.query.UserInfoQuery;
import com.zz.demoai.service.DeviceInfoService;
import com.zz.demoai.service.ServiceOrderService;
import com.zz.demoai.service.UserInfoService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DeviceTools {
    @Resource
    DeviceInfoService deviceInfoService;
    @Resource
    UserInfoService userInfoService;
    @Resource
    ServiceOrderService serviceOrderService;

    @Tool(description = "根据条件，查询用户的所有设备")
    public List<DeviceInfo> queryDevice(@ToolParam(description = "查询的条件") DeviceQuery query) {
        if (query == null) {
            return List.of();
        }
        return deviceInfoService.lambdaQuery()
                .eq(StringUtils.isNotEmpty(query.getDeviceId()), DeviceInfo::getDeviceId, query.getDeviceId())
                .eq(StringUtils.isNotEmpty(query.getDeviceName()), DeviceInfo::getDeviceName, query.getDeviceName())
                .eq(StringUtils.isNotEmpty(query.getPhone()), DeviceInfo::getPhone, query.getPhone())
                .list();
    }

    @Tool(description = "根据条件，查询设备的推送开关 推送开关 0 开 1 关 -1 未查到设备")
    public Integer queryDevicePush(@ToolParam(description = "设备id") String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return -1;
        }
        DeviceInfo one = deviceInfoService.lambdaQuery().eq(DeviceInfo::getDeviceId, deviceId).one();
        if (one == null) {
            return -1;
        }
        return one.getPushStatus();
    }

    @Tool(description = "根据条件，查询用户的预警开关，预警开关 0 开 1 关 -1 未查到用户信息")
    public Integer queryUserInfo(@ToolParam(description = "查询的条件") UserInfoQuery query) {
        if (query == null) {
            return -1;
        }
        UserInfo one = userInfoService.lambdaQuery()
                .eq(StringUtils.isNotEmpty(query.getUserName()), UserInfo::getUserName, query.getUserName())
                .eq(StringUtils.isNotEmpty(query.getPhone()), UserInfo::getPhone, query.getPhone())
                .one();
        if (one == null) {
            return -1;
        }
        return one.getWarnStatus();
    }

    @Tool(description = "根据设备id，打开设备的推送开关")
    public boolean updateDevicePush(@ToolParam(description = "设备id") String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return false;
        }
        return deviceInfoService.lambdaUpdate().eq(DeviceInfo::getDeviceId, deviceId).set(DeviceInfo::getPushStatus, 0).update();
    }

    @Tool(description = "根据用户手机，打开用户的预警开关")
    public boolean updateUserWarn(@ToolParam(description = "用户手机") String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        return userInfoService.lambdaUpdate().eq(UserInfo::getPhone, phone).set(UserInfo::getWarnStatus, 0).update();
    }

    @Tool(description = "在最后，将问题保存为工单信息，根据流程跳转，判断问题是否解决")
    public Integer saveServiceOrder(@ToolParam(description = "查询的条件") ServiceOrderSave orderSave) {
        if (orderSave == null) {
            return -1;
        }
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setTitle(orderSave.getTitle());
        serviceOrder.setDescription(orderSave.getDescription());
        serviceOrder.setSolve(orderSave.getSolve());
        serviceOrder.setCreateDate(new Date());

        serviceOrderService.save(serviceOrder);
        return serviceOrder.getId();
    }
}
