package com.cnsunet.kjw.service.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 10:47 2018/7/13
 * @Modified By:
 */
public interface IMenuService {
    /**
     *@Author  huangjie
     *@Description 获取全部菜单信息
     *@Date  2018/7/13 10:48
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getAllMenu() throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuByName(String name);
    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuById(Integer id);
    /**
     *@Author  huangjie
     *@Description 添加菜单
     *@Date  2018/7/13 10:39
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addMenu(MenuModel menuModel);
    /**
     *@Author  huangjie
     *@Description 修改菜单信息
     *@Date  2018/7/13 12:43
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenu(MenuModel menuModel);
    /**
     *@Author  huangjie
     *@Description 删除菜单
     *@Date  2018/7/13 14:04
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteMenu(Integer id);
    /**
     *@Author  huangjie
     *@Description 查询当前菜单下的所有第一级子菜单
     *@Date  2018/7/13 14:08
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getChildrenMenu(int id);

    /**
     *@Author  huangjie
     *@Description 根据用户id查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getMenuByUserId(Integer userid) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 根据用户名称查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getMenuByUserName(String userName) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenusForRole(Integer roleId,List<Integer> menus) throws DBErrorException;


    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateMenusForUser(Integer userId,List<Integer> menus) throws DBErrorException;
}
