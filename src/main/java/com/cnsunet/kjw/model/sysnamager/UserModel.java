package com.cnsunet.kjw.model.sysnamager;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 14:30 2018/7/12
 * @Modified By:
 */
public class UserModel {
    public enum UserModelMapper implements RowMapper<UserModel>{
        INSTANCE;
        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserModel userModel=new UserModel();
            userModel.setId(rs.getInt("id"));
           // userModel.setLoginName(rs.getString("loginName"));
            userModel.setPassword(rs.getString("password"));
            userModel.setUserName(rs.getString("userName"));
            userModel.setEmail(rs.getString("email"));
            userModel.setPhone(rs.getString("phone"));
            userModel.setCreateTime(rs.getString("createTime"));
            userModel.setUpdateTime(rs.getString("updateTime"));
            userModel.setCreateId(rs.getInt("createUser"));
            userModel.setLastLoginTime(rs.getString("lastLoginTime"));
            userModel.setStatus(rs.getInt("status"));
            return userModel;
        }
    }
    /**
    *userId
    */
    private Integer id;
    /**
    *登陆名称
    */
   // private String loginName;

    /**
    * 密码
    */
    private String password;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * email
    */
    private String email;

    /**
    * phone
    */
    private String phone;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 修改时间
    */
    private String updateTime;

    /**
    *创建人id
    */
    private Integer createId;

    /**
    * 上次登陆时间
    */
    private String lastLoginTime;

    /**
    *状态
    */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   /* public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
