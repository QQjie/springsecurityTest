package com.cnsunet.kjw.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangjie
 * @Description :全局异常处理
 * @Date: Created in 15:13 2018/6/26
 * @Modified By:
 */
@RestController
public class ExceptionController {
    @ExceptionHandler({ForbiddenException.class})
    public String handleForbidden(){
        return " 授权异常";
    }

    @ExceptionHandler({AuthUserException.class})
    public String handleAuth(){
        return " 权限不足";
    }


}
