package com.yuandian.core.common;

import lombok.Getter;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

@Getter
public enum ResultStatus {
    SUCCESS(0, "成功"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    MSG_CODE_EXIST(-1004, "验证码未失效"),
    MSG_CODE_NOT_EXIST(-1005, "验证码已失效或不存在"),
    MSG_CODE_ERROR(-1006, "验证码错误"),
    USER_NOT_REGSTION(-1008, "用户未注册"),
    USER_EXIST(-1007, "已经存在此手机号的用户"),
    OLD_PWD_ERROR(-1009, "老密码输入错误"),
    CANNOT_NULL(-1010, "内容不能为空"),
    GOODS_NOT_FOUND(-1011, "不存在该物品");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
