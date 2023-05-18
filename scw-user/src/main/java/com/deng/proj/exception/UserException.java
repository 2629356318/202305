package com.deng.proj.exception;

import com.deng.proj.enums.UserExceptionEnum;

/**
 * 异常类
 */
public class UserException extends RuntimeException {

    public UserException(UserExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
    }
}