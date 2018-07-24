package com.cnsunet.kjw.controller.sysmanage;

import com.cnsunet.kjw.commom.Node;
import com.cnsunet.kjw.commom.Tree;
import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.service.sysmanage.IMenuService;
import com.cnsunet.kjw.utils.ConstDefine;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 10:51 2018/7/13
 * @Modified By:
 */
@RestController
public class MenuController {
    
    private Logger logger=LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

   /**
    * @Author  huangjie
    * 描述 返回menu的集合 以树形结构返回
    * HTTP方式  get
    * API路径   /api/menu
    * 方法名   getAllMenuByTree
    * 方法异常
    * @Modyfied by 
    */
    @RequestMapping(value = "/api/menuAll" ,method = RequestMethod.GET)
    @ApiOperation(value = "查询所有菜单信息",response = String.class,httpMethod = "GET",notes = "查询所有菜单信息-供菜单tree展示使用")
    public String getAllMenuByTree(){
        try{
            List<MenuModel> list=menuService.getAllMenu();
            Tree<MenuModel> tree=new Tree<MenuModel>(0,list);
            List<Node> menuTree=tree.build();
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "查询所有菜单信息", menuTree).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:getAllMenuByTree..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getAllMenuByTree..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据菜单名称查询菜单信息
     * HTTP方式  GET
     * API路径   /api/menu
     * 方法名   getMenuByName
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menubyname" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据菜单名称查询菜单信息",response = String.class,httpMethod = "GET",notes = "根据菜单名称查询菜单信息")
    public String getMenuByName(
            @ApiParam(value = "菜单名称",required = true) @RequestParam(value = "menuName",required = true) String menuName
    ){
        try{
            MenuModel result=menuService.getMenuByName(menuName);

            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "根据菜单名称查询菜单信息成功", result).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:getMenuByName..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getMenuByName..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据菜单Id查询菜单信息
     * HTTP方式  GET
     * API路径   /api/menu
     * 方法名   getMenuByName
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menubyid" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据菜单Id查询菜单信息",response = String.class,httpMethod = "GET",notes = "根据菜单Id查询菜单信息")
    public String getMenuById(
            @ApiParam(value = "菜单ID",required = true) @RequestParam(value = "menuId",required = true) String menuId
    ){
        try{
            MenuModel result=menuService.getMenuById(Integer.valueOf(menuId));

            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "根据菜单id查询菜单信息成功", result).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:getMenuById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getMenuById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述 新增菜单信息
     * HTTP方式  POST
     * API路径   /api/menu
     * 方法名   addMenu
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menu" ,method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单信息",response = String.class,httpMethod = "POST",notes = "新增菜单信息")
    public String addMenu(
            @ApiParam(value = "菜单名称",required = true) @RequestParam(value = "menuName",required = true) String menuName,
            @ApiParam(value = "菜单url",required = true) @RequestParam(value = "menuUrl",required = true) String menuUrl,
            @ApiParam(value = "父级菜单Id",required = true) @RequestParam(value = "menuParentId",required = true) String menuParentId
    ){
        MenuModel menuModel=new MenuModel();
        menuModel.setName(menuName);
        menuModel.setMenuUrl(menuUrl);
        menuModel.setParentId(Integer.valueOf(menuParentId));
        menuModel.setStatus(ConstDefine.STATE_ABLE);
        try{
            int result=menuService.addMenu(menuModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "新增菜单信息成功", result).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:addMenu..msg:POST  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:addMenu..msg:POST  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 修改菜单信息
     * HTTP方式  PUT
     * API路径   /api/menu
     * 方法名   updateMenu
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menu" ,method = RequestMethod.PUT)
    @ApiOperation(value = "修改菜单信息",response = String.class,httpMethod = "PUT",notes = "修改菜单信息")
    public String updateMenu(
            @ApiParam(value = "菜单Id",required = true) @RequestParam(value = "menuId",required = true) String menuId,
            @ApiParam(value = "菜单名称",required = true) @RequestParam(value = "menuName",required = true) String menuName,
            @ApiParam(value = "菜单url",required = true) @RequestParam(value = "menuUrl",required = true) String menuUrl,
            @ApiParam(value = "父级菜单Id",required = true) @RequestParam(value = "menuParentId",required = true) String menuParentId
    ){
        MenuModel menuModel=new MenuModel();
        menuModel.setId(Integer.valueOf(menuId));
        menuModel.setName(menuName);
        menuModel.setMenuUrl(menuUrl);
        menuModel.setParentId(Integer.valueOf(menuParentId));
        menuModel.setStatus(ConstDefine.STATE_ABLE);
        try{
            int result=menuService.updateMenu(menuModel);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "修改菜单信息成功", result).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:updateMenu..msg:PUT  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:updateMenu..msg:PUT  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 删除菜单信息
     * HTTP方式  DELETE
     * API路径   /api/menu
     * 方法名   deleteMenu
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menu" ,method = RequestMethod.DELETE)
    @ApiOperation(value = "删除菜单信息",response = String.class,httpMethod = "DELETE",notes = "删除菜单信息")
    public String deleteMenu(
            @ApiParam(value = "菜单Id",required = true) @RequestParam(value = "menuId",required = true) String menuId
    ){
        try{
            int result=menuService.deleteMenu(Integer.valueOf(menuId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "删除菜单信息成功", result).toString();
        }catch (DBErrorException e){
            logger.error("controller:MenuController. function:deleteMenu..msg:DELETE  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:deleteMenu..msg:DELETE  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
    /**
     * @Author  huangjie
     * 描述  获取该用户指定菜单的下级菜单列表
     * HTTP方式  GET
     * API路径
     * 方法名
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/BelowMenus" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取该用户指定菜单的下级菜单列表",response = String.class,httpMethod = "GET",notes = "获取该用户指定菜单的下级菜单列表")
    public String getBelowMenus(
            @ApiParam(value = "用户名称",required = true) @RequestParam(value = "userName",required = true) String userName,
            @ApiParam(value = "父级菜单Id",required = true) @RequestParam(value = "parentMenuId",required = true) String parentMenuId
    ){
        List<MenuModel> result=new ArrayList<>();
        try{
            List<MenuModel> list=menuService.getMenuByUserName(userName);
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getParentId()==Integer.valueOf(parentMenuId)){
                    result.add(list.get(i));
                }
            }
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户用户菜单列表成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getBelowMenus..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getBelowMenus..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }


    /**
     * @Author  huangjie
     * 描述 根据用户ID 获取该用户所拥有的菜单列表
     * HTTP方式  GET
     * API路径
     * 方法名
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menuByUId" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取该用户所拥有的菜单列表",response = String.class,httpMethod = "GET",notes = "获取该用户所拥有的菜单列表")
    public String getUserMenuById(
            @ApiParam(value = "用户Id",required = true) @RequestParam(value = "userId",required = true) String userId
    ){
        List<MenuModel> result=new ArrayList<>();
        try{
            result=menuService.getMenuByUserId(Integer.valueOf(userId));
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户用户菜单列表成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getUserMenuById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getUserMenuById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 根据用户ID 获取该用户所拥有的菜单列表
     * HTTP方式  GET
     * API路径
     * 方法名
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menuByUName" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取该用户所拥有的菜单列表",response = String.class,httpMethod = "GET",notes = "获取该用户所拥有的菜单列表")
    public String getUserMenuByName(
            @ApiParam(value = "用户名称",required = true) @RequestParam(value = "userName",required = true) String userName
    ){
        List<MenuModel> result=new ArrayList<>();
        try{
            result=menuService.getMenuByUserName(userName);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户用户菜单列表成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getUserMenuByName..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getUserMenuByName..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给角色分配菜单列表
     * HTTP方式  POST
     * API路径 /api/menuforrole
     * 方法名
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menuforrole" ,method = RequestMethod.POST)
    @ApiOperation(value = "给角色分配菜单列表",response = String.class,httpMethod = "POST",notes = "给角色分配菜单列表")
    public String addOrUpdMenuForRole(
            @ApiParam(value = "角色Id",required = true) @RequestParam(value = "roleId",required = true) String roleId,
            @ApiParam(value = "菜单id数组 用英文逗号,隔开",required = true) @RequestParam(value = "muneIds",required = true) String muneIds
    ){
        List<Integer> menuIds=new ArrayList<>();
        String[] arr=muneIds.split(",");
        for (int i = 0; i < arr.length; i++) {
            menuIds.add(Integer.valueOf(arr[i]));
        }
        try{
            int result=menuService.updateMenusForRole(Integer.valueOf(roleId),menuIds);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户用户菜单列表成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:addOrUpdMenuForRole..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:addOrUpdMenuForRole..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author  huangjie
     * 描述 给角色分配菜单列表
     * HTTP方式  POST
     * API路径 /api/menuforuser
     * 方法名
     * 方法异常
     * @Modyfied by
     */
    @RequestMapping(value = "/api/menuforuser" ,method = RequestMethod.POST)
    @ApiOperation(value = "给用户分配菜单列表",response = String.class,httpMethod = "POST",notes = "给角色分配菜单列表")
    public String addOrUpdMenuForUser(
            @ApiParam(value = "角色Id",required = true) @RequestParam(value = "userId",required = true) String userId,
            @ApiParam(value = "菜单id数组 用英文逗号,隔开",required = true) @RequestParam(value = "muneIds",required = true) String muneIds
    ){
        List<Integer> menuIds=new ArrayList<>();
        String[] arr=muneIds.split(",");
        for (int i = 0; i < arr.length; i++) {
            menuIds.add(Integer.valueOf(arr[i]));
        }
        try{
            int result=menuService.updateMenusForUser(Integer.valueOf(userId),menuIds);
            return new JsonResponseData(true, StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS, "用户用户菜单列表成功", result).toString();
        }catch (DBErrorException e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:addOrUpdMenuForRole..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:addOrUpdMenuForRole..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
}
