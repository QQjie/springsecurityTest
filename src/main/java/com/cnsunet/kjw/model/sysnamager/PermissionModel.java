package com.cnsunet.kjw.model.sysnamager;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huangjie
 * @Description :权限模型
 * @Date: Created in 9:13 2018/7/13
 * @Modified By:
 */
public class PermissionModel {

    public enum PermissionMapper implements RowMapper<PermissionModel> {
        INSTANCE;

        @Override
        public PermissionModel mapRow(ResultSet rs, int rowNum) throws  SQLException{
            PermissionModel permissionModel=new PermissionModel();
            permissionModel.setId(rs.getInt("id"));
            permissionModel.setPerssionName(rs.getString("permissionName"));
            permissionModel.setStatus(rs.getInt("status"));
            return permissionModel;
        }
    }
    /**
    *权限id
    */
    private Integer id;
    
    /**
    * 权限名称
    */
    private String perssionName;

    /**
    *status
    */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerssionName() {
        return perssionName;
    }

    public void setPerssionName(String perssionName) {
        this.perssionName = perssionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
