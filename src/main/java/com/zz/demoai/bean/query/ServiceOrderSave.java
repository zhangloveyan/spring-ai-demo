package com.zz.demoai.bean.query;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.Serializable;

@Data
public class ServiceOrderSave implements Serializable {

    /**
     * 问题标题
     */
    @ToolParam(description = "问题标题")
    private String title;

    /**
     * 问题描述
     */
    @ToolParam(description = "问题描述")
    private String description;

    /**
     * 是否解决 0 未解决 1 已解决
     */
    @ToolParam(description = "是否解决 0 未解决 1 已解决")
    private Integer solve;

}
