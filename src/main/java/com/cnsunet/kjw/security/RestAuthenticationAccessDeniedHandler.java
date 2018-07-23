package com.cnsunet.kjw.security;

import com.cnsunet.kjw.utils.StatusDefine;
import com.cnsunet.kjw.utils.StatusDefineMessage;
import com.cnsunet.kjw.utils.json.JsonResponseData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: huangjie
 * @Description : 自定义403返回信息
 * @Date: Created in 11:33 2018/7/18
 * @Modified By:
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        writer.println(new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_NO_TOKEN),
                StatusDefine.U_NO_TOKEN, "用户无权访问该接口", null).toString());
    }
}
