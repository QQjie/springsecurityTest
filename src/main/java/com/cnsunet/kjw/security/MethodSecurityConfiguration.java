package com.cnsunet.kjw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 17:38 2018/6/26
 * @Modified By:
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private CustomPermissionEvaluator cpe;//用来重写hasPermission表达式

    @Autowired
    private CustomMethodSecurityExpressionHandler expressionHandler;//用来配置表达式

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        expressionHandler.setPermissionEvaluator(cpe);
        return expressionHandler;
    }
}
