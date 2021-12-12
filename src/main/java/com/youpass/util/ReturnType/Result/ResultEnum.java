package com.youpass.util.ReturnType.Result;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(100, "成功"),
    EMAIL_IS_EXISTS(1, "邮箱已存在"),
    //yxb 500
    //lyz 600
    //xsc 700
    INFO_DEFICIENCY(700,"信息缺失"),
    USER_MISS(701,"用户不存在"),
    USER_NOT_LOGIN(702,"用户没有登录"),
    File_ERROR(703,"文件操作出现问题"),
    ;
    private Integer code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
