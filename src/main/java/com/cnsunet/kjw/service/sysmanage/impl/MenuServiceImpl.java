package com.cnsunet.kjw.service.sysmanage.impl;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.repository.sysmanage.MenuRepository;
import com.cnsunet.kjw.service.sysmanage.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :菜单管理服务
 * @Date: Created in 10:48 2018/7/13
 * @Modified By:
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;
    /**
     *@Author  huangjie
     *@Description  查询全部菜单信息
     *@Date  2018/7/13 10:49
     *@Param
     *@Return List<MenuModel>
     *@Modyfied by
     */
    @Override
    public List<MenuModel> getAllMenu() throws DBErrorException {
        List<MenuModel> list=new ArrayList<>();
        try{
            list=menuRepository.getAllMenu();
        }catch(DBErrorException e){
            throw new DBErrorException("查询全部菜单信息异常");
        }
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuByName(String name){
        MenuModel menuModel=null;
        try{
            menuModel=menuRepository.getMenuByName(name);
        }catch(DBErrorException e){
            throw new DBErrorException("根据菜单名字查询菜单信息异常");
        }
      return menuModel;
    }
    /**
     *@Author  huangjie
     *@Description 根据菜单Id查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuById(Integer id){
        MenuModel menuModel=null;
        try{
            menuModel=menuRepository.getMenuById(id);
        }catch(DBErrorException e){
            throw new DBErrorException("根据菜单id查询菜单信息异常");
        }
        return menuModel;
    }
    /**
     *@Author  huangjie
     *@Description 添加菜单
     *@Date  2018/7/13 10:39
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addMenu(MenuModel menuModel){
        int result=0;
        try{
            result= menuRepository.addMenu(menuModel);
        }catch(DBErrorException e){
            throw new DBErrorException("添加菜单异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 修改菜单信息
     *@Date  2018/7/13 12:43
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenu(MenuModel menuModel){
        int result=0;
        try{
            result =menuRepository.updateMenu(menuModel);
        }catch(DBErrorException e){
            throw new DBErrorException("修改菜单信息异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 删除菜单
     *@Date  2018/7/13 14:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteMenu(Integer id){
        int result=0;
        try{
            result =menuRepository.deleteMenu(id);
        }catch(DBErrorException e){
            throw new DBErrorException("删除菜单异常");
        }
       return result;
    }
    /**
     *@Author  huangjie
     *@Description 查询当前菜单下的所有第一级子菜单
     *@Date  2018/7/13 14:08
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getChildrenMenu(int id){
        List<MenuModel> list=new ArrayList<>();
        try{
            list=menuRepository.getChildrenMenu(id);
        }catch(DBErrorException e){
            throw new DBErrorException("查询当前菜单下的所有第一级子菜单异常");
        }
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 根据用户id查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getMenuByUserId(Integer userid) throws DBErrorException{
        List<MenuModel> list=new ArrayList<>();
        try{
            list=menuRepository.getMenuByUserId(userid);
        }catch(DBErrorException e){
            throw new DBErrorException("根据用户id查询所拥有权限的菜单列表异常");
        }
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 根据用户名称查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getMenuByUserName(String userName) throws DBErrorException{
        List<MenuModel> list=new ArrayList<>();
        try{
            list=menuRepository.getMenuByUserName(userName);
        }catch(DBErrorException e){
            throw new DBErrorException("根据用户名称查询所拥有权限的菜单列表异常");
        }
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenusForRole(Integer roleId,List<Integer> menus) throws DBErrorException{
        int result=0;
        try{
            result =menuRepository.updateMenuForRole(roleId,menus);
        }catch(DBErrorException e){
            throw new DBErrorException("删除菜单异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenusForUser(Integer userId,List<Integer> menus) throws DBErrorException{
        int result=0;
        try{
            result =menuRepository.updateMenuForUser(userId,menus);
        }catch(DBErrorException e){
            throw new DBErrorException("给角色添加或修改菜单列表异常");
        }
        return result;
    }

}
