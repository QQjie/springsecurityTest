package com.cnsunet.kjw.service.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.model.sysnamager.RoleModel;
import com.cnsunet.kjw.model.sysnamager.UserModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 15:49 2018/7/13
 * @Modified By:
 */
public interface IUserService {
    /**
     *@Author  huangjie
     *@Description  根据
     *@Date  2018/7/12 15:48
     *@Param
     *@Return
     *@Modyfied by
     */
    public UserModel getUserById(Integer id);
    /**
     *@Author  huangjie
     *@Description  根据
     *@Date  2018/7/12 15:48
     *@Param
     *@Return
     *@Modyfied by
     */
    public UserModel getUserByName(String name);
    /**
     *@Author  huangjie
     *@Description 新增用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  新增用户Id
     *@Modyfied by
     */
    public int addUser(UserModel userModel) throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 修改用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  修改用户Id
     *@Modyfied by
     */
    public int updateUser(UserModel userModel) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 删除用户
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  删除用户Id
     *@Modyfied by
     */
    public int deleteUserByName(String userName) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 查询所有用户信息
     *@Date  2018/7/18 9:46
     *@Param  UserMode
     *@Return  删除用户Id
     *@Modyfied by
     */
    public List<UserModel> getAllUser() throws DBErrorException;
    /**
     *@Author  huangjie
     *@Description 查询该用户所创建的所有用户信息
     *@Date  2018/7/17 15:03
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<UserModel> getAllUserByCreatUser(Integer createId) throws DBErrorException;

    /**
     *@Author  huangjie
     *@Description 查询认证登陆当前的用户所拥有的权限操作
     *@Date  2018/7/17 14:27
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<String> getUserPermAndOper(String  userName);

    /**
     *@Author  huangjie
     *@Description 根据用户Id查询用户所拥有的权限操作
     *@Date  2018/7/17 14:27
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<String> getUserPermAndOper(Integer  userId);

    /**
     *@Author  huangjie
     *@Description 根据用户名称查询用户所拥有的角色
     *@Date  2018/7/17 14:27
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> getUserRoleByName(String  userName);

}
