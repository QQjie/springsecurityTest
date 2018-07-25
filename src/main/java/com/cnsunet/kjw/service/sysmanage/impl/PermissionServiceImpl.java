package com.cnsunet.kjw.service.sysmanage.impl;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.repository.sysmanage.PermissionRepository;
import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import com.cnsunet.kjw.service.sysmanage.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 16:12 2018/7/19
 * @Modified By:
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     *@Author  huangjie
     *@Description 给角色划分权限
     *@Date  2018/7/19 16:10
     *@Param  权限list
     *@Return
     *@Modyfied by
     */
    @Override
    public int grantPmsForRole(Integer roleId, List<Integer> pms) throws DBErrorException{
        int result=0;
        try{
            result=permissionRepository.updatePmsForRole(roleId,pms);
        }catch (Exception e){
            throw new DBErrorException("角色授予权限失败");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description  删除角色的权限
     *@Date  2018/7/18 17:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePmsForRole(Integer roleId,List<Integer> pmsIds){
        int result=0;
        try{
            result=permissionRepository.deletePmsForRole(roleId,pmsIds);
        }catch(Exception e){
            throw new DBErrorException("删除角色权限失败");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 给角色修改权限
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsForRole(Integer roleId,List<Integer> pmsIds){
        int result=0;
        try{
            result=permissionRepository.updatePmsForRole(roleId,pmsIds);
        }catch(Exception e){
            throw new DBErrorException("修改角色权限失败");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 给权限修改操作选项
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsOperate(Integer permId,List<Integer> operIds){
        int result=0;
        try{
            result=permissionRepository.updatePmsOperate(permId,operIds);
        }catch(Exception e){
            throw new DBErrorException("给权限修改操作选项失败");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 给用户增加或修改独特的权限操作选项
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsOperateForU(Integer userId,Map<Integer,List<Integer>> map){
        int result=0;
        try{
            result=permissionRepository.updatePmsOperateForU(userId,map);
        }catch(Exception e){
            throw new DBErrorException("给用户增加或修改独特的权限操作选项失败");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 增加权限管理模块
     *@Date  2018/7/13 14:51
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addPermission(PermissionModel permissionModel){
        int result=0;
        try{
            result=permissionRepository.addPermission(permissionModel);
        }catch(Exception e){
            throw new DBErrorException("增加权限管理模块失败");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 查询所有的权限
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAllPermission(){
        List<PermissionModel> list=null;
        try{
            list=permissionRepository.getAllPermission();
        }catch(Exception e){
            e.printStackTrace();
            throw new DBErrorException("查询所有的权限失败");
        }
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 修改权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePermission(PermissionModel permissionModel){
        int result=0;
        try{
            result=permissionRepository.updatePermission(permissionModel);
        }catch(Exception e){
            throw new DBErrorException("修改权限信息失败");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 删除权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePermission(Integer permId){
        int result=0;
        try{
            result=permissionRepository.deletePermission(permId);
        }catch(Exception e){
            throw new DBErrorException("删除权限信息失败");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 查询当前登录用户的所有权限列表
     *@Date  2018/7/20 16:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAuthPmsByName(String name){
        List<PermissionModel> result=new ArrayList<>();
        try{
            List<String> list=userRepository.getUserPermAndOper(name);
            for (int i = 0; i < list.size(); i++) {
                PermissionModel permissionModel=new PermissionModel();
                String[] arr=list.get(i).split("-");
                permissionModel.setId(Integer.valueOf(arr[2]));
                permissionModel.setPerssionName(arr[0]);
                permissionModel.setStatus(1);
                result.add(permissionModel);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new DBErrorException("查询当前登录用户的所有权限列表");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 根据用户Id查询用户所拥有的权限操作
     *@Date  2018/7/17 14:27
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<PermissionModel> getAuthPmsById(Integer  userId){
        List<PermissionModel> result=new ArrayList<>();
        try{
            List<String> list=userRepository.getUserPermAndOper(userId);
            for (int i = 0; i < list.size(); i++) {
                PermissionModel permissionModel=new PermissionModel();
                String[] arr=list.get(i).split("-");
                permissionModel.setId(Integer.valueOf(arr[2]));
                permissionModel.setPerssionName(arr[0]);
                permissionModel.setStatus(1);
                result.add(permissionModel);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new DBErrorException("查询当前登录用户的所有权限列表");
        }
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 根据权限id查询权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public PermissionModel getPermissionByid(Integer id){
        PermissionModel permissionModel=null;
        try{
            permissionModel=permissionRepository.getPermissionById(id);
        }catch(Exception e){
            throw new DBErrorException("根据权限id查询权限信息失败");
        }
        return permissionModel;
    }
    /**
     *@Author  huangjie
     *@Description 根据权限名称查询权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public PermissionModel getPermissionByName(String name){
        PermissionModel permissionModel=null;
        try{
            permissionModel=permissionRepository.getPermissionByName(name);
        }catch(Exception e){
            throw new DBErrorException("根据权限名称查询权限信息失败");
        }
        return permissionModel;
    }

}
