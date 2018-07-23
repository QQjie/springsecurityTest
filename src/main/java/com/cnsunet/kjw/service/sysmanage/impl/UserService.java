package com.cnsunet.kjw.service.sysmanage.impl;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import com.cnsunet.kjw.service.sysmanage.IUserService;
import com.cnsunet.kjw.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :用户服务类
 * @Date: Created in 15:48 2018/7/12
 * @Modified By:
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    /**
     *@Author  huangjie
     *@Description  根据
     *@Date  2018/7/12 15:48
     *@Param  
     *@Return 
     *@Modyfied by
     */
    public UserModel getUserById(Integer id){
        return  userRepository.getUserById(id);
    }
    /**
     *@Author  huangjie
     *@Description  根据
     *@Date  2018/7/12 15:48
     *@Param
     *@Return
     *@Modyfied by
     */
    @Override
    public UserModel getUserByName(String name) {
        return userRepository.getUserByName(name);
    }
    /**
     *@Author  huangjie
     *@Description 新增用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  新增用户Id
     *@Modyfied by
     */
    public int addUser(UserModel userModel) throws DBErrorException{
        String pwd=userModel.getPassword();
        userModel.setPassword( MD5Util.encode(pwd));
        int result=0;
        try {
            result= userRepository.addUser(userModel);
        }catch (Exception e){
            throw new DBErrorException("用户添加失败");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 修改用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  新增用户Id
     *@Modyfied by
     */
    public int updateUser(UserModel userModel) throws DBErrorException{
        userModel.setPassword(MD5Util.encode(userModel.getPassword()));
        int result=0;
        try{
            result= userRepository.updateUser(userModel);
        }catch (Exception e){
            throw new DBErrorException("用户修改");
        }
        return result;
    };
    /**
     *@Author  huangjie
     *@Description 删除用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  删除用户Id
     *@Modyfied by
     */
    public int deleteUserByName(String userName) throws DBErrorException{
        int result=0;
        try{
            result=userRepository.deleteUserByName(userName);
        }catch (Exception e){
            throw new DBErrorException("删除用户");
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 查询认证登陆当前的用户所拥有的权限操作
     *@Date  2018/7/17 14:27
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<String> getUserPermAndOper(String  userName){
        List<String> result=null;
        try{
            result=userRepository.getUserPermAndOper(userName);
        }catch(Exception e){
            throw new DBErrorException("查询认证登陆当前的用户所拥有的权限操作失败");
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
    public List<String> getUserPermAndOper(Integer  userId){
        List<String> result=null;
        try{
            result=userRepository.getUserPermAndOper(userId);
        }catch(Exception e){
            throw new DBErrorException("根据用户Id查询用户所拥有的权限操作失败");
        }
        return result;
    }
}
