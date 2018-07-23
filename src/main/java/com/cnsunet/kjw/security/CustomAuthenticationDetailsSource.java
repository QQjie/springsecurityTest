package com.cnsunet.kjw.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: huangjie
 * @Description :该接口用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充，
 * 默认实现是WebAuthenticationDetailsSource，生成上面的默认实现WebAuthenticationDetails。
 * 我们编写类实现AuthenticationDetailsSource，用于生成上面自定义的CustomWebAuthenticationDetails。
 * @Date: Created in 11:47 2018/6/28
 * @Modified By:
 */
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context);
    }
}
