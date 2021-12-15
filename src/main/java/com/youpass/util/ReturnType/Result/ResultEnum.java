package com.youpass.util.ReturnType.Result;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(100, "成功"),
    EMAIL_IS_EXISTS(1, "邮箱已存在"),
    //yxb 500
    COURSE_MISS(500, "课程不存在"),
    WITHOUT_AUTHORITY(501,"用户没有考试权限"),
    ANSWER_TYPE_MISS(502,"题目类型错误"),
    QUESTION_ERROR(503,"题库数量不足"),
    //lyz 600
    SESSION_MISS(600, "session miss"),
    ALREADY_EXISTS(601,"学生已经加入了该课程"),
    PASSWORD_ERROR(602,"密码错误"),
    //xsc 700
    INFO_DEFICIENCY(700,"信息缺失"),
    USER_MISS(701,"用户不存在"),
    USER_NOT_LOGIN(702,"用户没有登录"),
    File_ERROR(703,"文件操作出现问题"),
    NOT_TEACHER(704,"不是老师"),
    NOT_STUDENT(705,"不是学生"),
    PERMISSION_DENIED(706,"没有此权限"),
    EXAM_MISS(707,"不存在此考试"),
    Question_MISS(708,"不存在此题目"),
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
