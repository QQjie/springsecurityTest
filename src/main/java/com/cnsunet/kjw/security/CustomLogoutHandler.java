package com.cnsunet.kjw.security;

import com.cnsunet.kjw.utils.StatusDefine;
import com.cnsunet.kjw.utils.StatusDefineMessage;
import com.cnsunet.kjw.utils.json.JsonResponseData;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 14:45 2018/7/23
 * @Modified By:
 */
/*public class CustomLogoutHandler extends SimpleUrlAuthenticationFailureHandler  {

}*/
