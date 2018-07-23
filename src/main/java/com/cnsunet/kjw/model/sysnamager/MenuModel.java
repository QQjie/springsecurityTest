package com.cnsunet.kjw.model.sysnamager;

import com.cnsunet.kjw.model.TreeBaseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huangjie
 * @Description :菜单模型
 * @Date: Created in 9:57 2018/7/13
 * @Modified By:
 */
public class MenuModel extends TreeBaseModel {
    public enum MenuMapper implements RowMapper<MenuModel>{
        INSTANCE;

        @Override
        public MenuModel mapRow(ResultSet rs, int rowNum) throws SQLException{
            MenuModel menuModel=new MenuModel();
            menuModel.setId(rs.getInt("id"));
            menuModel.setName(rs.getString("menuName"));
            menuModel.setMenuUrl(rs.getString("menuUrl"));
            menuModel.setParentId(rs.getInt("parentMenuId"));
            menuModel.setStatus(rs.getInt("status"));
            return menuModel;
        }
    }
    /**
    * menuUrl
    */
    private String menuUrl;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public MenuModel(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public MenuModel(){}

    public MenuModel(Integer id, Integer parentId, String name, Integer status, String menuUrl) {
        super(id, parentId, name, status);
        this.menuUrl = menuUrl;
    }
}
