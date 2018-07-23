package com.cnsunet.kjw.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: huangjie
 * @Description :使用表达式hasPermission,自定义SecurityExpressionHandler对应的bean定义
 * @Date: Created in 9:24 2018/7/2
 * @Modified By:
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
       /* UserModel user=(UserModel) targetDomainObject;
        System.out.println(
                user.getUserName()+"====Permisson"
        );*/
        System.out.println(
                targetDomainObject+"permission"
        );
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }
}
