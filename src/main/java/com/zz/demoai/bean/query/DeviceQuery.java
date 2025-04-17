package com.zz.demoai.bean.query;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

@Data
public class DeviceQuery {
    @ToolParam(required = false,description = "设备id")
    private String deviceId;

    @ToolParam(required = false,description = "设备名称")
    private String deviceName;

    @ToolParam(required = false,description = "用户手机号")
    private String phone;

//    @ToolParam(required = false,description = "排序方式")
//    private List<Sort> sorts;
//
//    public static class Sort{
//        @ToolParam(required = false,description = "排序字段：price或duration")
//        private String field;
//        @ToolParam(required = false,description = "是否升序：true/false")
//        private Boolean asc;
//    }
}
