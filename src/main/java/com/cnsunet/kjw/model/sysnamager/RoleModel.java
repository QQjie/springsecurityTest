package com.cnsunet.kjw.model.sysnamager;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 16:12 2018/7/12
 * @Modified By:
 */
public class RoleModel {
    public enum  RoleMapper implements RowMapper<RoleModel>{
        INSTANCE;
        @Override
        public RoleModel mapRow(ResultSet rs, int rowNum) throws SQLException{
            RoleModel role=new RoleModel();
            role.setId(rs.getInt("id"));
            role.setRoleName(rs.getString("roleName"));
            role.setPriority(rs.getInt("priority"));
            role.setStatus(rs.getInt("status"));
            return role;
        }
    }
    /**
    *角色ID
    */
    private Integer id;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    *角色的优先级
    */
    private Integer priority;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
