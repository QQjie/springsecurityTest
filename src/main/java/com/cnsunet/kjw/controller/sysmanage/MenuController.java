package com.cnsunet.kjw.controller.sysmanage;

import com.cnsunet.kjw.commom.Node;
import com.cnsunet.kjw.commom.Tree;
import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.service.sysmanage.IMenuService;
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
    @RequestMapping(value = "/api/menu" ,method = RequestMethod.GET)
    @ApiOperation(value = "查询所有菜单信息",response = String.class,httpMethod = "GET",notes = "查询所有菜单信息-供菜单tree展示使用")
    public String getAllMenuByTree(){
        try{
            List<MenuModel> list=menuService.getAllMenu();
            Tree<MenuModel> tree=new Tree<MenuModel>(0,list);
            List<Node> menuTree=tree.build();
            return new JsonResponseData(true,"", StatusDefine.SUCCESS,"SUCCESS",menuTree).toString();
        }catch (DBErrorException e){
            return new JsonResponseData(true,"", StatusDefine.DB_ERROR,"",e.getMessage()).toString();
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
            logger.error("controller:MenuController. function:getUserMenuById..msg:GET  DBErrorException. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.DB_ERROR), StatusDefine.DB_ERROR, "", null).toString();
        }catch (Exception e){
            //抛出异常返回异常信息
            logger.error("controller:MenuController. function:getUserMenuById..msg:GET  Exception. error:"+e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
}
