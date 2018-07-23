package com.cnsunet.kjw.exception;

/**
 * @Author: huangjie
 * @Description :权限不足异常
 * @Date: Created in 15:19 2018/6/26
 * @Modified By:
 */
public class AuthUserException extends RuntimeException {
    public AuthUserException(String message){
        super(message);
    }
}
