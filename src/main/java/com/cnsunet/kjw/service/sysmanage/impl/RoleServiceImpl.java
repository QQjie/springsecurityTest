package com.cnsunet.kjw.service.sysmanage.impl;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.RoleModel;
import com.cnsunet.kjw.repository.sysmanage.RoleRepository;
import com.cnsunet.kjw.service.sysmanage.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 14:50 2018/7/18
 * @Modified By:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    /**
     *@Author  huangjie
     *@Description 添加角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public int addRole(RoleModel roleModel) throws DBErrorException{
        Integer result=0;
        try {
            result=  roleRepository.addRole(roleModel);
        }catch (Exception e){
            throw new DBErrorException("新增角色异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 修改角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public int updateRole(RoleModel roleModel) throws DBErrorException{
        Integer result=0;
        try {
            result=roleRepository.updateRole(roleModel);
        }catch (Exception e){
            throw new DBErrorException("修改角色异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 删除角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public int deleteRole(Integer roleId) throws DBErrorException{
        Integer result=0;
        try {
            result=roleRepository.deleteRoleById(roleId);
        }catch (Exception e){
            throw new DBErrorException("删除角色异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 按id查询角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public RoleModel selectRoleById(Integer roleId) throws DBErrorException{
        RoleModel result=null;
        try {
            result=roleRepository.getRoleById(roleId);
        }catch (Exception e){
            e.printStackTrace();
            throw new DBErrorException("id查询角色异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 按名称查询角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public RoleModel selectRoleByName(String roleName) throws DBErrorException{
        RoleModel result=null;
        try {
            result=roleRepository.getRoleByRoleName(roleName);
        }catch (Exception e){
            throw new DBErrorException("name查询角色异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 查询所有角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> selectAllRole() throws DBErrorException{
        List<RoleModel> result=null;
        try {
            result=roleRepository.getAllRole();
        }catch (Exception e){
            throw new DBErrorException("查询所有角色异常");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 查询登陆用户优先级一下的所有角色
     *@Date  2018/7/18 15:24
     *@Param  RoleModel
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> selectAllBelowRole(String userName) throws DBErrorException{
        List<RoleModel> result=null;
        try {
            result=roleRepository.getAllBelowRoles(userName);
        }catch (Exception e){
            throw new DBErrorException("查询等级以内所有角色异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 给当前用户添加角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addUserRole(Integer userId,Integer roleId) throws DBErrorException{
        Integer result=0;
        try {
            result=roleRepository.addUserRole(userId,roleId);
        }catch (Exception e){
            throw new DBErrorException("给用户添加角色异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 修改当前用户所拥有的角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateUserRole(Integer userId,Integer roleId) throws DBErrorException{
        Integer result=0;
        try {
            result=roleRepository.updateUserRole(userId,roleId);
        }catch (Exception e){
            throw new DBErrorException("修改用户角色异常");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 删除当前用户所拥有的角色
     *@Date  2018/7/18 15:31
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteUserRole(Integer userId,Integer roleId) throws DBErrorException{
        Integer result=0;
        try {
            result=roleRepository.deleteUserRole(userId,roleId);
        }catch (Exception e){
            throw new DBErrorException("删除用户角色异常");
        }
        return result;
    }

}
