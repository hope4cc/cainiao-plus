package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 文件名：GlobalExceptionHandler
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/18-17:22
 * 描述：
 */
@Slf4j
@ControllerAdvice //控制器增强
public class GlobalExceptionHandler {

    //处理异常 XueChengPlusException 此类异常由程序员主动抛出，可预知的异常
    @ResponseBody//将信息返回成json格式
    @ExceptionHandler(XueChengPlusException.class)//此方法捕获XueChengPlusException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//状态吗返回500
    public RestErrorResponse doXueChengPlusException(XueChengPlusException e) {
        log.error("捕获异常：{}", e.getErrorMessage());
        e.printStackTrace();

        String errorMessage = e.getErrorMessage();
        return new RestErrorResponse(errorMessage);
    }

    //捕获不可预知的异常Exception
    @ResponseBody//将信息返回成json格式
    @ExceptionHandler(Exception.class)//此方法捕获Exception异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//状态吗返回500
    public RestErrorResponse doException(Exception e) {
        log.error("捕获异常{}", e.getMessage());
        e.printStackTrace();

        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());//安慰！UNKOWN_ERROR=执行过程异常，请重试
    }


    //捕获MethodArgumentNotValidException异常
    @ResponseBody//将信息返回成json格式
    @ExceptionHandler(MethodArgumentNotValidException.class)//此方法捕获MethodArgumentNotValidException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//状态吗返回500
    public RestErrorResponse doMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        //收集错误
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        //遍历错误
        StringBuffer errors = new StringBuffer();
        fieldErrors.forEach(error -> errors.append(error.getDefaultMessage()).append(","));

        return new RestErrorResponse(errors.toString());
    }
}
