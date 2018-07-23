package com.cnsunet.kjw.security;

import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.repository.sysmanage.RoleRepository;
import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import com.cnsunet.kjw.service.sysmanage.IUserService;
import com.cnsunet.kjw.utils.page.OperateConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: huangjie
 * @Description :此类可以添加自定义的权限测试表达式，可自定义添加相应的service提供服务
 * @Date: Created in 11:32 2018/7/2
 * @Modified By:
 */
@Component
public class CustomSecurityExpressionRoot implements MethodSecurityExpressionOperations {
    public final boolean permitAll = true;
    public final boolean denyAll = false;
    private String defaultRolePrefix = "ROLE_";
    protected Authentication authentication;
    private AuthenticationTrustResolver trustResolver;
    private RoleHierarchy roleHierarchy;
    private Set<String> roles;
    private PermissionEvaluator permissionEvaluator;
    private Object filterObject;
    private Object returnObject;

    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;


    public void setAuthentication(Authentication authentication) {
        if (authentication == null) { throw new IllegalArgumentException(
                "Authentication object cannot be null"); }
        this.authentication = authentication;
    }

    public boolean hasCustomPermission(String userName) {//自定义的方法，其他方法可以拷贝过来，在这里可以注入任何Component,具有很大的灵活性

        UserModel userModel = userService.getUserByName(userName);
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        System.out.println(customWebAuthenticationDetails.getUserName()+"----mymymymymymymymym");
        if(userModel.getUserName().equals("hj")){
            return true;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(userName)) return true;
        }
        return false;
    }

    /**
     *@Author  huangjie
     *@Description 自定义的方法，在这里可以注入任何Component,具有很大的灵活性
     *@Date  2018/7/2 12:28
     *@Param
     *@Return
     *@Modyfied by
     */

    public boolean hasPagePrivilege1(String privilege) {
        UserModel userModel = userService.getUserByName(privilege);
        if(userModel.getUserName().equals("hjj")){
            return true;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(privilege)) return true;
        }
        return false;
    }
    /**
     *@Author  huangjie
     *@Description 自定义用户添加权限 
     *@Date  2018/7/18 10:04
     *@Param  
     *@Return 
     *@Modyfied by
     */
    public boolean hasUserAddPms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("用户管理权限")&&((Integer.valueOf(arr[1])&OperateConst.POST)>=OperateConst.POST)) {
                return true;
            }
        }
        return false;
    }
    /**
     *@Author  huangjie
     *@Description 自定义用户修改权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasUserUpdatePms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("用户管理权限")&&((Integer.valueOf(arr[1])&OperateConst.PUT)>=OperateConst.PUT)) {
                return true;
            }
        }
        return false;
    }

    /**
     *@Author  huangjie
     *@Description 自定义用户删除权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasUserDeletePms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("用户管理权限")&&((Integer.valueOf(arr[1])&OperateConst.DELETE)>=OperateConst.DELETE)) {
                return true;
            }
        }
        return false;
    }
    /**
     *@Author  huangjie
     *@Description 自定义查询角色权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasRoleSelectPms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("角色管理权限")&&((Integer.valueOf(arr[1])&OperateConst.GET)>=OperateConst.GET)) {
                return true;
            }
        }
        return false;
    }

    /**
     *@Author  huangjie
     *@Description 自定义添加角色权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasRoleAddPms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("角色管理权限")&&((Integer.valueOf(arr[1])&OperateConst.POST)>=OperateConst.POST)) {
                return true;
            }
        }
        return false;
    }
    /**
     *@Author  huangjie
     *@Description 自定义添加角色权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasRoleUpdatePms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("角色管理权限")&&((Integer.valueOf(arr[1])&OperateConst.PUT)>=OperateConst.PUT)) {
                return true;
            }
        }
        return false;
    }
    /**
     *@Author  huangjie
     *@Description 自定义添加角色权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public boolean hasRoleDeletePms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("角色管理权限")&&((Integer.valueOf(arr[1])&OperateConst.DELETE)>=OperateConst.DELETE)) {
                return true;
            }
        }
        return false;
    }
    

    @Override
    public final boolean hasAuthority(String authority) {
        throw new RuntimeException("method hasAuthority() not allowed");
    }

    @Override
    public final boolean hasAnyAuthority(String... authorities) {
        return hasAnyAuthorityName(null, authorities);
    }

    @Override
    public final boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    @Override
    public final boolean hasAnyRole(String... roles) {
        return hasAnyAuthorityName(defaultRolePrefix, roles);
    }

    private boolean hasAnyAuthorityName(String prefix, String... roles) {
        final Set<String> roleSet = getAuthoritySet();

        for (final String role : roles) {
            final String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (roleSet.contains(defaultedRole)) { return true; }
        }

        return false;
    }

    @Override
    public final Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public final boolean permitAll() {
        return true;
    }

    @Override
    public final boolean denyAll() {
        return false;
    }

    @Override
    public final boolean isAnonymous() {
        return trustResolver.isAnonymous(authentication);
    }

    @Override
    public final boolean isAuthenticated() {
        return !isAnonymous();
    }

    @Override
    public final boolean isRememberMe() {
        return trustResolver.isRememberMe(authentication);
    }

    @Override
    public final boolean isFullyAuthenticated() {
        return !trustResolver.isAnonymous(authentication)
                && !trustResolver.isRememberMe(authentication);
    }

    public Object getPrincipal() {
        return authentication.getPrincipal();
    }

    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        this.trustResolver = trustResolver;
    }

    public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    private Set<String> getAuthoritySet() {
        if (roles == null) {
            roles = new HashSet<String>();
            Collection<? extends GrantedAuthority> userAuthorities = authentication
                    .getAuthorities();

            if (roleHierarchy != null) {
                userAuthorities = roleHierarchy
                        .getReachableGrantedAuthorities(userAuthorities);
            }

            roles = AuthorityUtils.authorityListToSet(userAuthorities);
        }

        return roles;
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        return permissionEvaluator.hasPermission(authentication, target,
                permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType,
                                 Object permission) {
        return permissionEvaluator.hasPermission(authentication,
                (Serializable) targetId, targetType, permission);
    }

    public void setPermissionEvaluator(
            PermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    private static String getRoleWithDefaultPrefix(String defaultRolePrefix,
                                                   String role) {
        if (role == null) { return role; }
        if ((defaultRolePrefix == null)
                || (defaultRolePrefix.length() == 0)) { return role; }
        if (role.startsWith(defaultRolePrefix)) { return role; }
        return defaultRolePrefix + role;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}
