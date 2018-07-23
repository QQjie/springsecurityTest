package com.cnsunet.kjw.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @Author: huangjie
 * @Description :用配置表达式的服务
 * @Date: Created in 11:31 2018/7/2
 * @Modified By:
 */
@Service
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Autowired
    private CustomSecurityExpressionRoot root;//要在这个类中写表达式

    @Autowired
    private CustomPermissionEvaluator cpe;//还是hasPermission的表达式

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        root.setAuthentication(authentication);
        root.setPermissionEvaluator(cpe);
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
