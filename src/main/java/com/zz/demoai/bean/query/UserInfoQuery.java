package com.zz.demoai.bean.query;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

@Data
public class UserInfoQuery {
    @ToolParam(required = false,description = "用户名称")
    private String userName;

    @ToolParam(required = false,description = "预警开关 0 开 1 关")
    private String warnStatus;

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
