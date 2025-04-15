package com.zz.demoai.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 预警开关 0 开 1 关
     */
    private Integer warnStatus;

    /**
     * 手机号
     */
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}