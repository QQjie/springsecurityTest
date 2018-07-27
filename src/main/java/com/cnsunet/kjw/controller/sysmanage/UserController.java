package com.cnsunet.kjw.controller.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.security.CustomAuthenticationDetailsSource;
import com.cnsunet.kjw.security.CustomWebAuthenticationDetails;
import com.cnsunet.kjw.service.sysmanage.impl.UserService;
import com.cnsunet.kjw.utils.StatusDefine;
import com.cnsunet.kjw.utils.StatusDefineMessage;
import com.cnsunet.kjw.utils.json.JsonResponseData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 15:54 2018/7/12
 * @Modified By:
 */
@RestController
public class UserController /*extends AbstractAuthenticationProcessingFilter*/ {
    private Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomAuthenticationDetailsSource customAuthenticationDetailsSource;


    @RequestMapping(value = "/api/user",method = RequestMethod.GET)
    @ApiOperation(value = "",response = String.class,httpMethod = "GET",notes = "")
    public String getUserById(
            @ApiParam(value = "用户Id",required = true) @RequestParam(value = "id",required = true) String id
    ){

        try {
            UserModel result=userService.getUserById(Integer.valueOf(id));
            if (result==null) {
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_INEXISTENCE), StatusDefine.U_INEXISTENCE, "修改用户失败", null).toString();
            }
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "获取用户信息成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getUserById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getUserById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 登录
     * HTTP方式  GET
     * API路径   /api/login
     * 方法名  login
     * 方法异常   Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    @ApiOperation(value = "登陆",httpMethod = "GET",response = String.class,notes = "登陆")
    public String userLogin(
            @ApiParam(value = "用户名",required =true ) @RequestParam(value = "username",required = true) String username,
            @ApiParam(value = "密码",required =true ) @RequestParam(value = "password",required = true) String password,
            @ApiParam(value = "token1",required =false ) @RequestParam(value = "token",required = false) String token,
            HttpServletRequest request
    ){
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(customAuthenticationDetailsSource.buildDetails(request));
        try{
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        }catch(AuthenticationException e){
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_PWD_FAILED), StatusDefine.U_PWD_FAILED, "用户登录失败", null).toString();
        }
      //在这里用redis生成token并存储 然后返回用户信息 加token
        return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户登录成功", null).toString();
    }
    /**
     * @Author  huangjie
     * 描述 退出登录
     * HTTP方式  PUT
     * API路径   /api/logout
     * 方法名  logout
     * 方法异常   Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/logout",method = RequestMethod.PUT)
    @ApiOperation(value = "退出登录",httpMethod = "PUT",response = String.class,notes = "登出")
    public String userLogout(HttpServletRequest request){
        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")==null) {
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_UNLOAD), StatusDefine.U_UNLOAD, "用户未登录", null).toString();
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        HttpSession session = request.getSession();
        session.removeAttribute("SPRING_SECURITY_CONTEXT");
        return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户退出登录成功", null).toString();
    }
    /**
     * @Author  huangjie
     * 描述 添加用户需要用户添加权限
     * HTTP方式  POST
     * API路径   /api/user
     * 方法名  addUser
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
     @PreAuthorize("hasUserAddPms()")
     @RequestMapping(value = "/api/user",method = RequestMethod.POST)
     @ApiOperation(value ="添加用户",response = String.class,httpMethod = "POST",notes="添加用户-供管理员添加web登陆用户使用")
     public String addUser(
             @ApiParam(value = "用户名",required = true ) @RequestParam(value = "userName" ,required = true) String userName,
             @ApiParam(value = "用户密码",required = true) @RequestParam(value = "password",required = true) String password,
             @ApiParam(value = "创建者Id",required = true) @RequestParam(value = "createId",required = true) String createId
     ){
         UserModel userModel=new UserModel();
         userModel.setUserName(userName);
         userModel.setPassword(password);
         userModel.setCreateId(Integer.valueOf(createId));
         try {
             UserModel old=userService.getUserByName(userName);
             if(!(old==null)){
                 return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_EXIST_USER), StatusDefine.U_EXIST_USER, "添加用户失败", null).toString();
             }
             Integer result=userService.addUser(userModel);
             return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "添加用户成功", result).toString();
         }catch (DBErrorException e){
             //抛出异常返回异常信息
             logger.error("controller:UserController. function:addUser..msg:POST  DBErrorException. error:"+e.getMessage());
             return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
         }catch (Exception e){
             //抛出异常返回异常信息
             logger.error("controller:UserController. function:addUser..msg:POST  Exception. error:"+e.getMessage());
             return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
         }
     }

    /**
     * @Author  huangjie
     * 描述 修改用户需要用户修改权限
     * HTTP方式  PUT
     * API路径   /api/updateforadmin
     * 方法名  updateUserFroAdmin
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @PreAuthorize("hasUserAddPms()")
    @RequestMapping(value = "/api/updateforadmin",method = RequestMethod.PUT)
    @ApiOperation(value ="修改用户FORADMIN",response = String.class,httpMethod = "PUT",notes="修改用户-供管理员修改web登陆用户使用")
    public String updateUserForAdmin(
            @ApiParam(value = "用户id",required = true ) @RequestParam(value = "userId" ,required = true) String userId,
            @ApiParam(value = "用户名",required = true ) @RequestParam(value = "userName" ,required = true) String userName,
            @ApiParam(value = "用户密码",required = true) @RequestParam(value = "password",required = true) String password
    ){
        UserModel userModel=new UserModel();
        userModel.setId(Integer.valueOf(userId));
        userModel.setUserName(userName);
        userModel.setPassword(password);
        try {
            UserModel old=userService.getUserById(Integer.valueOf(userId));
            if (old==null) {
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_INEXISTENCE), StatusDefine.U_INEXISTENCE, "修改用户失败", null).toString();
            }
            Integer result=userService.updateUser(userModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "修改用户成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:updateUser..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:updateUser..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 修改用户不需要用户修改权限
     * HTTP方式  PUT
     * API路径   /api/updateforadmin
     * 方法名  updateUserFroAdmin
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/updateforuser",method = RequestMethod.PUT)
    @ApiOperation(value ="修改用户FORUSER",response = String.class,httpMethod = "PUT",notes="修改用户-供web登陆用户使用不需要权限")
    public String updateUserForUser(
            @ApiParam(value = "用户id",required = true ) @RequestParam(value = "userId" ,required = true) String userId,
            @ApiParam(value = "用户名",required = true ) @RequestParam(value = "userName" ,required = true) String userName,
            @ApiParam(value = "用户密码",required = true) @RequestParam(value = "password",required = true) String password
    ){
        UserModel userModel=new UserModel();
        userModel.setId(Integer.valueOf(userId));
        userModel.setUserName(userName);
        userModel.setPassword(password);
        try {
            UserModel old=userService.getUserById(Integer.valueOf(userId));
            if (old==null) {
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_INEXISTENCE), StatusDefine.U_INEXISTENCE, "修改用户失败", null).toString();
            }
            Integer result=userService.updateUser(userModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "修改用户成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:updateUser..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:updateUser..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 删除用户
     * HTTP方式  PUT
     * API路径   /api/updateforadmin
     * 方法名  updateUserFroAdmin
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @PreAuthorize("hasUserDeletePms()")
    @RequestMapping(value = "/api/deleteuser",method = RequestMethod.DELETE)
    @ApiOperation(value ="删除用户",response = String.class,httpMethod = "DELETE",notes="删除用户-供管理员删除用户使用需要权限")
    public String deleteUser(
            @ApiParam(value = "用户名",required = true ) @RequestParam(value = "userName" ,required = true) String userName
    ){
        try {
            UserModel user=userService.getUserByName(userName);
            if (user==null) {
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_INEXISTENCE), StatusDefine.U_INEXISTENCE, "删除用户失败", null).toString();
            }
            Integer result=userService.deleteUserByName(userName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除用户成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:deleteUser..msg:DELETE  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:deleteUser..msg:DELETE  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 获取所有用户信息
     * HTTP方式  GET
     * API路径   /api/userAll
     * 方法名  getAllUser
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @PreAuthorize("hasSadmin()")
    @RequestMapping(value = "/api/userAll",method = RequestMethod.GET)
    @ApiOperation(value ="获取所有用户信息",response = String.class,httpMethod = "GET",notes="获取所有用户信息-供管理员使用")
    public String getAllUser(){
        try {
            List<UserModel> result=userService.getAllUser();
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "获取所有用户信息成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getAllUser..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getAllUser..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 根据用户id获取该用户所创建的所有用户的信息
     * HTTP方式  GET
     * API路径   /api/userAllFroCreator
     * 方法名  getAllUser
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @PreAuthorize("hasRoleSelectPms()")
    @RequestMapping(value = "/api/userAllFroCreator",method = RequestMethod.GET)
    @ApiOperation(value ="根据用户id获取该用户所创建的所有用户的信息",response = String.class,httpMethod = "GET",notes="根据用户id获取该用户所创建的所有用户的信息-供web登陆者使用需权限")
    public String getAllUserForCreator(
            @ApiParam(value = "创建者ID",required = true ) @RequestParam(value = "createId" ,required = true) String createId
    ){
        try {
            UserModel user=userService.getUserById(Integer.valueOf(createId));
            if (user==null) {
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.U_INEXISTENCE), StatusDefine.U_INEXISTENCE, "删除用户失败", null).toString();
            }
            List<UserModel> result=userService.getAllUserByCreatUser(Integer.valueOf(createId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "根据用户id获取该用户所创建的所有用户成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getAllUserForCreator..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:UserController. function:getAllUserForCreator..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
}
