package com.cnsunet.kjw.service.sysmanage.impl;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.repository.sysmanage.MenuRepository;
import com.cnsunet.kjw.service.sysmanage.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
        return menuRepository.getAllMenu();
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
      return menuRepository.getMenuByName(name);
    }
    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuById(Integer id){
       return menuRepository.getMenuById(id);
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
      return menuRepository.addMenu(menuModel);
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
       return menuRepository.updateMenu(menuModel);
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
       return menuRepository.deleteMenu(id);
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
        return menuRepository.getChildrenMenu(id);
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
        return menuRepository.getMenuByUserId(userid);
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
        return menuRepository.getMenuByUserName(userName);
    }
    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsForRole(Integer roleId,List<Integer> menus) throws DBErrorException{
        return menuRepository.updatePmsForRole(roleId,menus);
    }

}
