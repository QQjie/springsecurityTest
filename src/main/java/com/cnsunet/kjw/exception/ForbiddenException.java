package com.cnsunet.kjw.exception;

/**
 * @Author: huangjie
 * @Description :授权异常
 * @Date: Created in 15:18 2018/6/26
 * @Modified By:
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message){
        super(message);
    }
}
