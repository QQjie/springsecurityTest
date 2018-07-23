package com.cnsunet.kjw.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: huangjie
 * @Description :该类提供了获取用户登录时携带的额外信息的功能，
 *  默认实现WebAuthenticationDetails提供了remoteAddress与sessionId信息。
 * 开发者可以通过Authentication的getDetails()获取WebAuthenticationDetails。
 * 我们编写自定义类CustomWebAuthenticationDetails继承自WebAuthenticationDetails，
 * 添加我们关心的数据。
 * @Date: Created in 11:46 2018/6/28
 * @Modified By:
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;

    private  String token;
    /**
    * username
    */
    private String userName;

    /**
    * mima
    */
    private String password;




    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("token");
        userName=request.getParameter("username");
        password=request.getParameter("password");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Token: ").append(this.getToken());
        return sb.toString();
    }
}
