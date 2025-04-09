package com.zz.demoai.config;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zln
 * @description 请求响应封装类
 * @date 2022/11/24
 */
@Data
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final String SUCCESS = "200";

    /**
     * 失败
     */
    public static final String FAIL = "500";

    private String code;
    private String message;
    private T data;


    private static <T> R<T> restResult(String code, String msg, T data) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     * 成功响应
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> ok() {
        return restResult(SUCCESS, "操作成功", null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(SUCCESS, "操作成功", data);
    }


    public static <T> R<T> fail() {
        return restResult(FAIL, "操作失败", null);
    }


    public static <T> R<T> fail(String msg) {
        return restResult(FAIL, msg, null);
    }

    public static <T> R<T> fail(String code, String msg, T data) {
        return restResult(code, msg, data);
    }

    /**
     * 根据 flag 返回 成功 失败
     *
     * @param flag
     * @param <T>
     * @return
     */
    public static <T> R<T> check(boolean flag) {
        return flag ? R.ok() : R.fail();
    }
}
