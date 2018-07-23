package com.cnsunet.kjw.service.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.RoleModel;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 14:50 2018/7/18
 * @Modified By:
 */
public interface IRoleService {
    /**
     *@Author  huangjie
     *@Description 添加角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
     public int addRole(RoleModel roleModel) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 修改角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public int updateRole(RoleModel roleModel) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 删除角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public int deleteRole(Integer roleId) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 按id查询角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public RoleModel selectRoleById(Integer roleId) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 按名称查询角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public RoleModel selectRoleByName(String roleName) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 查询所有角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> selectAllRole() throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 查询登陆用户优先级一下的所有角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> selectAllBelowRole(String userName) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 给当前用户添加角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addUserRole(Integer userId,Integer roleId) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 修改当前用户所拥有的角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateUserRole(Integer userId,Integer roleId) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 删除当前用户所拥有的角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteUserRole(Integer userId,Integer roleId) throws DBErrorException;

}
