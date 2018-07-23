package com.cnsunet.kjw.model.sysnamager;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huangjie
 * @Description :操作模型
 * @Date: Created in 9:42 2018/7/13
 * @Modified By:
 */
public class OperateModel {
    public enum OperteMapper implements RowMapper<OperateModel>{
        INTANCE;
        @Override
        public OperateModel mapRow(ResultSet rs, int rowNum) throws SQLException{
            OperateModel operateModel=new OperateModel();
            operateModel.setId(rs.getInt("id"));
            operateModel.setOperationName(rs.getString("operationName"));
            return operateModel;
        }
    }    /**
    *id
    */
    private Integer id;

    /**
    * 操作名称
    */
    private String operationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
