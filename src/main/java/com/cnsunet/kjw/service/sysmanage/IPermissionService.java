package com.cnsunet.kjw.service.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 16:07 2018/7/19
 * @Modified By:
 */
public interface IPermissionService {

    /**
     *@Author  huangjie
     *@Description 给角色划分权限
     *@Date  2018/7/19 16:10
     *@Param  权限list
     *@Return
     *@Modyfied by
     */
     public int grantPmsForRole(Integer roleId, List<Integer> pms) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description  删除角色的权限
     *@Date  2018/7/18 17:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePmsForRole(Integer roleId,List<Integer> pmsIds);

    /**
     *@Author  huangjie
     *@Description 给角色修改权限
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsForRole(Integer roleId,List<Integer> pmsIds);

    /**
     *@Author  huangjie
     *@Description 给权限增加或修改操作选项
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsOperate(Integer permId,List<Integer> operIds);

    /**
     *@Author  huangjie
     *@Description 增加权限管理模块
     *@Date  2018/7/13 14:51
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addPermission(PermissionModel permissionModel);

    /**
     *@Author  huangjie
     *@Description 查询所有的权限
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAllPermission();

    /**
     *@Author  huangjie
     *@Description 修改权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePermission(PermissionModel permissionModel);

    /**
     *@Author  huangjie
     *@Description 删除权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePermission(Integer permId);

    /**
     *@Author  huangjie
     *@Description 查询当前登录用户的所有权限列表
     *@Date  2018/7/20 16:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAuthPmsByName(String userName);

    /**
     *@Author  huangjie
     *@Description 根据用户id查询用户的所有权限列表
     *@Date  2018/7/20 16:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAuthPmsById(Integer id);

    /**
     *@Author  huangjie
     *@Description 根据权限id查询权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public PermissionModel getPermissionByid(Integer id);
    /**
     *@Author  huangjie
     *@Description 根据权限名称查询权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public PermissionModel getPermissionByName(String name);

}
