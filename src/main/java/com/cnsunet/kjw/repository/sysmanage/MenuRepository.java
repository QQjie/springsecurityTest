package com.cnsunet.kjw.repository.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.utils.ConstDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :菜单管理repository
 * @Date: Created in 10:38 2018/7/13
 * @Modified By:
 */
@Repository
public class MenuRepository {

    private Logger logger=LoggerFactory.getLogger(MenuRepository.class);

    @Autowired
    @Qualifier("myJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    /**
     *@Author  huangjie
     *@Description 查询所有的菜单信息
     *@Date  2018/7/13 10:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getAllMenu() throws DBErrorException {
        String sql="select id,menuName,menuUrl,parentMenuId,status from menu where status=?";
        List<MenuModel> list=jdbcTemplate.query(sql,new Object[]{ConstDefine.STATE_ABLE},MenuModel.MenuMapper.INSTANCE);
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuByName(String name){
        String sql="select id,menuName,menuUrl,parentMenuId,status from menu where menuName=? and status=?";
        logger.info(sql);
        List<MenuModel> list=jdbcTemplate.query(sql,new Object[]{name,ConstDefine.STATE_ABLE},MenuModel.MenuMapper.INSTANCE);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
    /**
     *@Author  huangjie
     *@Description 根据菜单名字查询菜单信息
     *@Date  2018/7/13 14:16
     *@Param
     *@Return
     *@Modyfied by
     */
    public MenuModel getMenuById(Integer id){
        String sql="select id,menuName,menuUrl,parentMenuId,status from menu where id=? and status=?";
        logger.info(sql);
        List<MenuModel> list=jdbcTemplate.query(sql,new Object[]{id,ConstDefine.STATE_ABLE},MenuModel.MenuMapper.INSTANCE);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
    /**
     *@Author  huangjie
     *@Description 添加菜单
     *@Date  2018/7/13 10:39
     *@Param
     *@Return
     *@Modyfied by
     */
     public int addMenu(MenuModel menuModel){
         String sql="insert into menu(menuName,menuUrl,parentMenuId,status) values(?,?,?,?)";
         logger.info(sql);
         KeyHolder key=new GeneratedKeyHolder();
         jdbcTemplate.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                 PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                 ps.setString(1,menuModel.getName());
                 ps.setString(2,menuModel.getMenuUrl());
                 ps.setInt(3,menuModel.getParentId());
                 ps.setInt(4,ConstDefine.STATE_ABLE);
                 return ps;
             }
         },key);
         return key.getKey().intValue();
     }
     /**
      *@Author  huangjie
      *@Description 修改菜单信息
      *@Date  2018/7/13 12:43
      *@Param
      *@Return
      *@Modyfied by
      */
      public int updateMenu(MenuModel menuModel){
          String sql="updae menu set menuName=?,menuUrl=? where id=?";
          logger.info(sql);
          int result=jdbcTemplate.update(new PreparedStatementCreator() {
              @Override
              public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                  PreparedStatement ps=con.prepareStatement(sql);
                  ps.setString(1,menuModel.getName());
                  ps.setString(2,menuModel.getMenuUrl());
                  ps.setInt(3,menuModel.getId());
                  return ps;
              }
          });
          return result;
      }
      /**
       *@Author  huangjie
       *@Description 删除菜单
       *@Date  2018/7/13 14:04
       *@Param
       *@Return
       *@Modyfied by
       */
      public int deleteMenu(Integer id){
          String sql="update mune set status=? where id=?";
          logger.info(sql);
          return jdbcTemplate.update(sql,new Object[]{ConstDefine.STATE_DELETE,id});
      }
      /**
       *@Author  huangjie
       *@Description 查询当前菜单下的所有第一级子菜单
       *@Date  2018/7/13 14:08
       *@Param
       *@Return
       *@Modyfied by
       */
      public List<MenuModel> getChildrenMenu(int id){
          String sql=" select id,menuName,menuUrl,parentMenuId,status from menu where parentMenuId=? and status=? ";
          List<MenuModel> list=jdbcTemplate.query(sql,new Object[]{id,ConstDefine.STATE_ABLE},MenuModel.MenuMapper.INSTANCE);
          return list;
      }

      /**
       *@Author  huangjie
       *@Description 根据用户id查询所拥有权限的菜单列表
       *@Date  2018/7/19 15:30
       *@Param
       *@Return
       *@Modyfied by
       */
    /*  public List<MenuModel> getMenuByUserId(Integer userid){
          StringBuilder sql=new StringBuilder("");
          sql.append("SELECT d.id,d.menuName,d.menuUrl,d.parentMenuId,d.status ");
          sql.append("FROM user_info a  ");
          sql.append("INNER JOIN user_role b ON a.id=b.userId AND b.status=? ");
          sql.append("INNER JOIN role_menu c ON b.roleId=c.roleId  AND c.status=? ");
          sql.append("INNER JOIN menu d ON c.menuId=d.id AND d.status=? ");
          sql.append("WHERE a.id=? ");
          List<MenuModel> list=jdbcTemplate.query(sql.toString(),new Object[]{
                  ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userid
          },MenuModel.MenuMapper.INSTANCE);
          return list;
      }*/

    /**
     *@Author  huangjie
     *@Description 根据用户id查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getMenuByUserId(Integer userid){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT id,menuName,menuUrl,parentMenuId,STATUS FROM( ");
        sql.append("SELECT d.id,d.menuName,d.menuUrl,d.parentMenuId,d.status  ");
        sql.append("FROM user_info a   ");
        sql.append("INNER JOIN user_role b ON a.id=b.userId AND b.status=? ");
        sql.append("INNER JOIN role_menu c ON b.roleId=c.roleId  AND c.status=?  ");
        sql.append("INNER JOIN menu d ON c.menuId=d.id AND d.status=?  ");
        sql.append("WHERE a.id=? ");
        sql.append("UNION ");
        sql.append("SELECT d.id,d.menuName,d.menuUrl,d.parentMenuId,d.status FROM user_menu a ");
        sql.append("INNER JOIN menu d ON d.id=a.menuId AND d.status=? ");
        sql.append("WHERE a.userId=? AND a.status=?) AS tmptable ");
        sql.append("WHERE id NOT IN ");
        sql.append("(SELECT d.id FROM user_menu a ");
        sql.append("INNER JOIN menu d ON d.id=a.menuId AND d.status=? ");
        sql.append("WHERE a.userId=? AND a.status=?) ");
        List<MenuModel> list=jdbcTemplate.query(sql.toString(),new Object[]{
                ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userid,
                ConstDefine.STATE_ABLE,userid,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,
                userid,ConstDefine.STATE_DELETE
        },MenuModel.MenuMapper.INSTANCE);
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 根据用户名称查询所拥有权限的菜单列表
     *@Date  2018/7/19 15:30
     *@Param
     *@Return
     *@Modyfied by
     */
    /*public List<MenuModel> getMenuByUserName(String userName){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT d.id,d.menuName,d.menuUrl,d.parentMenuId,d.status ");
        sql.append("FROM user_info a  ");
        sql.append("INNER JOIN user_role b ON a.id=b.userId AND b.status=? ");
        sql.append("INNER JOIN role_menu c ON b.roleId=c.roleId  AND c.status=? ");
        sql.append("INNER JOIN menu d ON c.menuId=d.id AND d.status=? ");
        sql.append("WHERE a.userName=? ");
        List<MenuModel> list=jdbcTemplate.query(sql.toString(),new Object[]{
                ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userName
        },MenuModel.MenuMapper.INSTANCE);
        return list;
    }*/
    public List<MenuModel> getMenuByUserName(String userName){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT d.id,d.menuName,d.menuUrl,d.parentMenuId,d.status ");
        sql.append("FROM user_info a  ");
        sql.append("INNER JOIN user_role b ON a.id=b.userId AND b.status=? ");
        sql.append("INNER JOIN role_menu c ON b.roleId=c.roleId  AND c.status=? ");
        sql.append("INNER JOIN menu d ON c.menuId=d.id AND d.status=? ");
        sql.append("WHERE a.userName=? ");
        List<MenuModel> list=jdbcTemplate.query(sql.toString(),new Object[]{
                ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userName
        },MenuModel.MenuMapper.INSTANCE);
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 删除角色所有的菜单列表
     *@Date  2018/7/19 15:38
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteMenuByRoleId(Integer roleId){
        String sql="update role_menu set status=? where roleId=?";
        logger.info(sql);
        int result=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,ConstDefine.STATE_DELETE);
                ps.setInt(2,roleId);
                return ps;
            }
        });
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 给角色添加或修改菜单列表
     *@Date  2018/7/19 15:37
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsForRole(Integer roleId,List<Integer> menus){
        //删除旧的
        this.deleteMenuByRoleId(roleId);
        String sql ="insert into role_menu(roleId,menuId,status) values(?,?,?) on DUPLICATE KEY update status=values(status)";
        logger.info(sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,roleId);
                ps.setInt(2,menus.get(i));
                ps.setInt(3,ConstDefine.STATE_ABLE);
            }
            @Override
            public int getBatchSize() {
                return menus.size();
            }
        });
        return menus.size();
    }



}
