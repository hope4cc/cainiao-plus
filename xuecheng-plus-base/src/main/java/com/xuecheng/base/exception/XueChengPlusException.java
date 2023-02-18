package com.xuecheng.base.exception;

/**
 * 文件名：XueChengPlusException
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/18-16:28
 * 描述：业务统一异常
 */
public class XueChengPlusException extends RuntimeException{

    private String errorMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public static void cast(String errorMessage){
        throw new XueChengPlusException(errorMessage);
    }

    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }

}
