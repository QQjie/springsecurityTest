package com.cnsunet.kjw.controller.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.RoleModel;
import com.cnsunet.kjw.service.sysmanage.IRoleService;
import com.cnsunet.kjw.utils.StatusDefine;
import com.cnsunet.kjw.utils.StatusDefineMessage;
import com.cnsunet.kjw.utils.json.JsonResponseData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :角色控制器
 * @Date: Created in 14:49 2018/7/18
 * @Modified By:
 */
@RestController
public class RoleController {
    private Logger logger=LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private IRoleService roleService;
    /**
     * @Author  huangjie
     * 描述 添加角色
     * HTTP方式  POST
     * API路径   /api/role
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleAddPms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/roletttt",method = RequestMethod.POST)
    @ApiOperation(value ="添加角色",response = String.class,httpMethod = "POST",notes="添加角色-供管理员添加角色使用需要权限")
    public String addRole(
            @ApiParam(value = "角色名称",required = true ) @RequestParam(value = "roleName" ,required = true) String roleName,
            @ApiParam(value = "优先级",required = true ) @RequestParam(value = "priority" ,required = true) String priority
    ){
        RoleModel roleModel=new RoleModel();
        roleModel.setRoleName(roleName);
        roleModel.setPriority(Integer.valueOf(priority));
        try {
            Integer result=roleService.addRole(roleModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "添加角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:addRole..msg:POST  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:addRole..msg:POST  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 修改角色
     * HTTP方式  PUT
     * API路径   /api/role
     * 方法名  updateRole
     * 方法异常 DBErrorException  Exception
     * @Modyfied by
     */
    @PreAuthorize("hasRoleUpdatePms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/role",method = RequestMethod.PUT)
    @ApiOperation(value ="修改角色",response = String.class,httpMethod = "PUT",notes="修改角色-供管理员修改角色使用-需要权限")
    public String updateRole(
            @ApiParam(value = "角色ID",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId,
            @ApiParam(value = "角色名称",required = true ) @RequestParam(value = "roleName" ,required = true) String roleName,
            @ApiParam(value = "优先级",required = true ) @RequestParam(value = "priority" ,required = true) String priority
    ){
        RoleModel roleModel=new RoleModel();
        roleModel.setId(Integer.valueOf(roleId));
        roleModel.setRoleName(roleName);
        roleModel.setPriority(Integer.valueOf(priority));
        try {
            Integer result=roleService.updateRole(roleModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "修改角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:updateRole..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "修改角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:updateRole..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "修改角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 删除角色
     * HTTP方式  POST
     * API路径   /api/roledelete
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleDeletePms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/roledelete",method = RequestMethod.PUT)
    @ApiOperation(value ="删除角色",response = String.class,httpMethod = "PUT",notes="删除角色-供管理员删除角色使用需要权限")
    public String deleteRole(
            @ApiParam(value = "角色Id",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId
    ){
        try {
            Integer result=roleService.deleteRole(Integer.valueOf(roleId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:deleteRole..msg:DELETE  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "删除角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:deleteRole..msg:DELETE  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "删除角色失败", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 查询所有角色
     * HTTP方式  get
     * API路径   /api/role
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleSelectPms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/role",method = RequestMethod.GET)
    @ApiOperation(value ="查询所有角色",response = String.class,httpMethod = "GET",notes="查询所有角色-供管理员查询所有角色使用需要权限")
    public String getAllRoles(
    ){
        try {
            List<RoleModel> result=roleService.selectAllRole();
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "查询所有角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getAllRoles..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "查询所有角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getAllRoles..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "查询所有角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 查询比当前用户角色优先级低的所有角色
     * HTTP方式  get
     * API路径   /api/role
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleSelectPms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/belowrole",method = RequestMethod.GET)
    @ApiOperation(value ="查询当前用户优先级低所有角色",response = String.class,httpMethod = "GET",notes="查询当前用户优先级低所有角色-供管理员查询所有角色使用需要权限")
    public String getAllBelowRoles(
            @ApiParam(value = "用户名称",required = true ) @RequestParam(value = "userName" ,required = true) String userName
    ){
        try {
            List<RoleModel> result=roleService.selectAllBelowRole(userName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "查询所有角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getAllBelowRoles..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "查询所有角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getAllBelowRoles..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "查询所有角色失败", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 查询角色
     * HTTP方式  get
     * API路径   /api/rolebyid
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleSelectPms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/rolebyid",method = RequestMethod.GET)
    @ApiOperation(value ="查询角色",response = String.class,httpMethod = "GET",notes="查询角色-供管理员查询角色使用需要权限")
    public String getRoleById(
            @ApiParam(value = "角色id",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId
    ){
        try {
            RoleModel result=roleService.selectRoleById(Integer.valueOf(roleId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "查询角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getRoleById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "查询角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getRoleById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "查询角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 查询角色
     * HTTP方式  get
     * API路径   /api/rolebyname
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleSelectPms() || hasAnyRole('SADMIN')")
    @RequestMapping(value = "/api/rolebyname",method = RequestMethod.GET)
    @ApiOperation(value ="查询角色",response = String.class,httpMethod = "GET",notes="查询角色-供管理员查询所有角色使用需要权限")
    public String getRoleByName(
            @ApiParam(value = "角色名称",required = true ) @RequestParam(value = "roleName" ,required = true) String roleName
    ){
        try {
            RoleModel result=roleService.selectRoleByName(roleName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "查询角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getRoleByName..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "查询角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:getRoleByName..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "查询角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给用户增加一个角色
     * HTTP方式  get
     * API路径   /api/userrole
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleAddPms() || hasUserAddPms()")
    @RequestMapping(value = "/api/userrole",method = RequestMethod.POST)
    @ApiOperation(value ="给用户增加一个角色",response = String.class,httpMethod = "POST",notes="给用户增加一个角色-供管理员增加用户角色使用需要权限")
    public String addUserRole(
            @ApiParam(value = "用户ID",required = true ) @RequestParam(value = "userId" ,required = true) String userId,
            @ApiParam(value = "角色ID",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId
    ){
        try {
            Integer result=roleService.addUserRole(Integer.valueOf(userId),Integer.valueOf(roleId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "添加用户角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:addUserRole..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "添加用户角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:addUserRole..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "添加用户角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给用户修改角色
     * HTTP方式  get
     * API路径   /api/userrole
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleUpdatePms() && hasUserUpdatePms()")
    @RequestMapping(value = "/api/userrole",method = RequestMethod.PUT)
    @ApiOperation(value ="给用户修改角色",response = String.class,httpMethod = "PUT",notes="给用户修改角色-供管理员修改用户角色使用需要权限")
    public String updateUserRole(
            @ApiParam(value = "用户ID",required = true ) @RequestParam(value = "userId" ,required = true) String userId,
            @ApiParam(value = "角色ID",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId
    ){
        try {
            Integer result=roleService.updateUserRole(Integer.valueOf(userId),Integer.valueOf(roleId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "添加用户角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:updateUserRole..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "添加用户角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:updateUserRole..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "添加用户角色失败", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给用户删除角色
     * HTTP方式  get
     * API路径   /api/userrole
     * 方法名  addRole
     * 方法异常
     * @Modyfied by
     */
    @PreAuthorize("hasRoleDeletePms() && hasUserDeletePms()")
    @RequestMapping(value = "/api/urdelete",method = RequestMethod.PUT)
    @ApiOperation(value ="给用户删除角色",response = String.class,httpMethod = "PUT",notes="给用户删除角色-供管理员删除用户角色使用需要权限")
    public String deleteUserRole(
            @ApiParam(value = "用户ID",required = true ) @RequestParam(value = "userId" ,required = true) String userId,
            @ApiParam(value = "角色ID",required = true ) @RequestParam(value = "roleId" ,required = true) String roleId
    ){
        try {
            Integer result=roleService.deleteUserRole(Integer.valueOf(userId),Integer.valueOf(roleId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除用户角色成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:deleteUserRole..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "删除用户角色失败", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:RoleController. function:deleteUserRole..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "删除用户角色失败", null).toString();
        }
    }

}
