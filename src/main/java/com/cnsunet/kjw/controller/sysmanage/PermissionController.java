package com.cnsunet.kjw.controller.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.service.sysmanage.IPermissionService;
import com.cnsunet.kjw.service.sysmanage.IUserService;
import com.cnsunet.kjw.utils.StatusDefine;
import com.cnsunet.kjw.utils.StatusDefineMessage;
import com.cnsunet.kjw.utils.json.JsonResponseData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 16:06 2018/7/20
 * @Modified By:
 */
@RestController
public class PermissionController {
    private Logger logger=LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IUserService userService;

    /**
     * @Author  huangjie
     * 描述 获取当前登录用户的所有权限列表
     * HTTP方式  GET
     * API路径   /api/authpms
     * 方法名  getAuthPms
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/authpms",method = RequestMethod.GET)
    @ApiOperation(value ="获取当前登录用户的所有权限列表",response = String.class,httpMethod = "GET",notes="获取当前登录用户的所有权限列表")
    public String getAuthPms(
            @ApiParam(value = "",required = true) @RequestParam(value = "userName" ,required = true) String userName
    ){
        try{
            List<PermissionModel> result=permissionService.getAuthPmsByName(userName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "获取当前登录用户的所有权限列表成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据用户Id的所有权限列表
     * HTTP方式  GET
     * API路径   /api/authpms
     * 方法名  getAuthPms
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/pmsById",method = RequestMethod.GET)
    @ApiOperation(value ="获取当前登录用户的所有权限列表",response = String.class,httpMethod = "GET",notes="获取当前登录用户的所有权限列表")
    public String getPmsByid(
            @ApiParam(value = "",required = true) @RequestParam(value = "userId" ,required = true) String userId
    ){
        try{
            List<PermissionModel> result=permissionService.getAuthPmsById(Integer.valueOf(userId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "根据用户Id的所有权限列表成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给角色划分或修改权限
     * HTTP方式  POST
     * API路径   /api/pmsforrole
     * 方法名  getAuthPms
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/pmsforrole",method = RequestMethod.POST)
    @ApiOperation(value ="给角色划分或修改权限",response = String.class,httpMethod = "POST",notes="给角色划分或修改权限")
    public String addOrUpdatePmsByRoleid(
            @ApiParam(value = "角色id",required = true) @RequestParam(value = "roleId" ,required = true) String roleId,
            @ApiParam(value = "权限id集合用,分开",required = true) @RequestParam(value = "pmsids" ,required = true) String pmsids
    ){
        List<Integer> list=new ArrayList<>();
        String[] arr=pmsids.split(",");
        for (int i = 0; i < arr.length; i++) {
            list.add(Integer.valueOf(arr[i]));
        }
        try{
            int result=permissionService.grantPmsForRole(Integer.valueOf(roleId),list);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "根据用户Id的所有权限列表成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 删除角色权限
     * HTTP方式  POST
     * API路径   /api/pmsforrole
     * 方法名  deletePmsByRoleid
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/pmsforrole",method = RequestMethod.PUT)
    @ApiOperation(value ="删除角色权限",response = String.class,httpMethod = "PUT",notes="删除角色权限")
    public String deletePmsByRoleid(
            @ApiParam(value = "角色id",required = true) @RequestParam(value = "roleId" ,required = true) String roleId,
            @ApiParam(value = "权限id集合用,分开",required = true) @RequestParam(value = "pmsids" ,required = true) String pmsids
    ){
        List<Integer> list=new ArrayList<>();
        String[] arr=pmsids.split(",");
        for (int i = 0; i < arr.length; i++) {
            list.add(Integer.valueOf(arr[i]));
        }
        try{
            int result=permissionService.deletePmsForRole(Integer.valueOf(roleId),list);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除角色权限成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAuthPms..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 给权限增加或修改操作权限
     * HTTP方式 POST
     * API路径 /api/oprforpms
     * 方法名 AddOrUpdateOprForPms
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/oprforpms",method = RequestMethod.POST)
    @ApiOperation(value = "给权限增加或修改操作权限",response = String.class,httpMethod = "POST",notes = "给权限增加或修改操作权限")
    public String AddOrUpdateOprForPms(
            @ApiParam(value = "权限Id",required = true)@RequestParam(value = "pmsId",required = true) String pmsId,
            @ApiParam(value = "操作Id列表，用英文逗号','隔开") @RequestParam(value = "oprIds",required = true) String oprIds
    ){
            List<Integer> list=new ArrayList<>();
            String[] arr=oprIds.split(",");
            for (int i = 0; i < arr.length; i++) {
                list.add(Integer.valueOf(arr[i]));
            }
            try{
                int result=permissionService.updatePmsOperate(Integer.valueOf(pmsId),list);
                return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "给权限增加或修改操作权限成功", result).toString();
            }catch(DBErrorException e){
                //抛出异常返回异常信息
                logger.error("controller:PermissionController. function:AddOrUpdateOprForPms..msg:POST  DBErrorException. error:"+e.getMessage());
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
            }catch (Exception e){
                //抛出异常返回异常信息
                logger.error("controller:PermissionController. function:AddOrUpdateOprForPms..msg:POST  Exception. error:"+e.getMessage());
                return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
            }
    }

    /**
     * @Author  huangjie
     * 描述 新增权限管理信息
     * HTTP方式  POST
     * API路径   /api/permission
     * 方法名  addPermission
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
     @RequestMapping(value = "/api/permission",method = RequestMethod.POST)
     @ApiOperation(value = "新增权限管理信息",response = String.class,httpMethod = "POST",notes = "新增权限管理信息")
     public String addPermission(
             @ApiParam(value = "权限名称",required = true)@RequestParam(value = "permissionName",required = true) String permissionName
     ){
         PermissionModel permissionModel=new PermissionModel();
         permissionModel.setPerssionName(permissionName);
         try{
             int result=permissionService.addPermission(permissionModel);
             return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "新增权限管理信息成功", result).toString();
         }catch(DBErrorException e){
             //抛出异常返回异常信息
             logger.error("controller:PermissionController. function:addPermission..msg:POST  DBErrorException. error:"+e.getMessage());
             return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
         }catch (Exception e){
             //抛出异常返回异常信息
             logger.error("controller:PermissionController. function:addPermission..msg:POST  Exception. error:"+e.getMessage());
             return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
         }
     }

    /**
     * @Author  huangjie
     * 描述 修改权限管理信息
     * HTTP方式  PUT
     * API路径   /api/permission
     * 方法名  updatePermission
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/permission",method = RequestMethod.PUT)
    @ApiOperation(value = "修改权限管理信息",response = String.class,httpMethod = "PUT",notes = "修改权限管理信息")
    public String updatePermission(
            @ApiParam(value = "权限名称",required = true)@RequestParam(value = "permissionName",required = true) String permissionName
    ){
        PermissionModel permissionModel=new PermissionModel();
        permissionModel.setPerssionName(permissionName);
        try{
            int result=permissionService.updatePermission(permissionModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "修改权限管理信息成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:updatePermission..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:updatePermission..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 删除权限管理信息
     * HTTP方式  POST
     * API路径   /api/permission
     * 方法名  deletePermission
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/permission",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除权限管理信息",response = String.class,httpMethod = "DELETE",notes = "删除权限管理信息")
    public String deletePermission(
            @ApiParam(value = "权限Id",required = true)@RequestParam(value = "permissionId",required = true) String permissionId
    ){
        try{
            int result=permissionService.deletePermission(Integer.valueOf(permissionId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除权限管理信息成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:deletePermission..msg:POST  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:deletePermission..msg:POST  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 查询所有权限管理信息
     * HTTP方式  GET
     * API路径   /api/permission
     * 方法名  getAllPermission
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/permission",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有权限管理信息",response = String.class,httpMethod = "GET",notes = "查询所有权限管理信息")
    public String getAllPermission(
            @ApiParam(value = "权限名称",required = true)@RequestParam(value = "permissionName",required = true) String permissionName
    ){
        List<PermissionModel> result=new ArrayList<>();
        try{
            result=permissionService.getAllPermission();
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "新增权限管理信息成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAllPermission..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getAllPermission..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据id查询所有权限信息
     * HTTP方式  GET
     * API路径   /api/permissionById
     * 方法名  getPermissionById
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/permissionById",method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询所有权限信息",response = String.class,httpMethod = "GET",notes = "根据id查询所有权限信息")
    public String getPermissionById(
            @ApiParam(value = "权限Id",required = true)@RequestParam(value = "permissionId",required = true) String permissionId
    ){
        PermissionModel result=null;
        try{
            result=permissionService.getPermissionByid(Integer.valueOf(permissionId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "新增权限管理信息成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getPermissionById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getPermissionById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据Name查询所有权限信息
     * HTTP方式  GET
     * API路径   /api/permissionByName
     * 方法名  getPermissionByName
     * 方法异常  DBErrorException Exception
     * @Modyfied by
     */
    @RequestMapping(value = "/api/permissionByName",method = RequestMethod.GET)
    @ApiOperation(value = "根据Name查询所有权限信息",response = String.class,httpMethod = "GET",notes = "根据Name查询所有权限信息")
    public String getPermissionByName(
            @ApiParam(value = "权限名称",required = true)@RequestParam(value = "permissionName",required = true) String permissionName
    ){
        PermissionModel result=null;
        try{
            result=permissionService.getPermissionByName(permissionName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "新增权限管理信息成功", result).toString();
        }catch(DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getPermissionByName..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:PermissionController. function:getPermissionByName..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }


}
