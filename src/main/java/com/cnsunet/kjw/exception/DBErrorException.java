package com.cnsunet.kjw.exception;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 15:54 2018/7/12
 * @Modified By:
 */
public class DBErrorException extends RuntimeException {
    public DBErrorException(String message) {
        super(message);
    }
}
