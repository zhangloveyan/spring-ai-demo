package com.zz.demoai.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName service_order
 */
@TableName(value ="service_order")
@Data
public class ServiceOrder implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 发生时间
     */
    private Date createDate;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String desc;

    /**
     * 是否解决 0 未解决 1 已解决
     */
    private Integer solve;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}