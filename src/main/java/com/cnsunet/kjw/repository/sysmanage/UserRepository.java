package com.cnsunet.kjw.repository.sysmanage;

import com.cnsunet.kjw.commom.Node;
import com.cnsunet.kjw.commom.Tree;
import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.model.sysnamager.RoleModel;
import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.utils.ConstDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangjie
 * @Description :用户管理repository
 * @Date: Created in 15:37 2018/7/12
 * @Modified By:
 */
@Repository
public class UserRepository {
    private final Logger logger=LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    @Qualifier("myJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    /**
     *@Author  huangjie
     *@Description 根据id查询用户信息
     *@Date  2018/7/12 15:38
     *@Param
     *@Return
     *@Modyfied by
     */
    public UserModel getUserById(Integer id){
        String sql ="select * from user_info where id=? and status=?";
        List<UserModel> list=jdbcTemplate.query(sql,new Object[]{id,ConstDefine.STATE_ABLE},UserModel.UserModelMapper.INSTANCE);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
    /**
     *@Author  huangjie
     *@Description 根据name查询用户信息
     *@Date  2018/7/12 15:38
     *@Param
     *@Return
     *@Modyfied by
     */
    public UserModel getUserByName(String name){
        String sql ="select * from user_info where userName=? and status=?";
        List<UserModel> list=jdbcTemplate.query(sql,new Object[]{name,ConstDefine.STATE_ABLE},UserModel.UserModelMapper.INSTANCE);
      /*  jdbcTemplate.execute(
                new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection con) throws SQLException{
                        String storedProc = "{call Test2(?)}";// 调用的sql

                        CallableStatement cs = con.prepareCall(storedProc);

                        cs.setInt(1, 1);// 设置输入参数的值
                       // cs.registerOutParameter(2, Types.VARCHAR);// 注册输出参数的类型
                        return cs;
                    }
                },
                new CallableStatementCallback<Object>() {
                    @Override
                    public Object doInCallableStatement(CallableStatement cs) throws DataAccessException,SQLException {
                        cs.execute();
                        ResultSet resultSet=cs.getResultSet();
                        while(resultSet.next()){
                            System.out.println(resultSet.getInt(1)+"=========================");
                            System.out.println(resultSet.getString(2)+"=========================");
                        }
                       // System.out.println(cs.getString(2)+"=========================");
                        return 1;// 获取输出

                    }
                }
        );*/
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
    /**
     *@Author  huangjie
     *@Description 查询所有用户信息
     *@Date  2018/7/17 15:03
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<UserModel> getAllUser(){
        String sql="select * from user_info where status=?";
        List<UserModel> list=jdbcTemplate.query(sql,new Object[]{ConstDefine.STATE_ABLE},UserModel.UserModelMapper.INSTANCE);
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 查询该用户所创建的所有用户信息
     *@Date  2018/7/17 15:03
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<UserModel> getAllUserByCreateUser(Integer createId){
        String sql="select * from user_info where createUser=? and status=?";
        List<UserModel> list=jdbcTemplate.query(sql,new Object[]{createId,ConstDefine.STATE_ABLE},UserModel.UserModelMapper.INSTANCE);
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 新增用户
     *@Date  2018/7/17 15:06
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addUser(UserModel userModel){
        String sql="insert into user_info(userName,password) values(?,?) ";
        logger.info(sql);
        KeyHolder key=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,userModel.getUserName());
                ps.setString(2,userModel.getPassword());
                return ps;
            }
        },key);
        return key.getKey().intValue();
    }

    /**
     *@Author  huangjie
     *@Description 修改用户
     *@Date  2018/7/18 11:57
     *@Param
     *@Return
     *@Modyfied by
     */
     public int updateUser(UserModel userModel){
         String sql ="update user_info set userName=? , password=? where id=?";
         logger.info(sql);
         int result=jdbcTemplate.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                 PreparedStatement ps=con.prepareStatement(sql);
                 ps.setString(1,userModel.getUserName());
                 ps.setString(2,userModel.getPassword());
                 ps.setInt(3,userModel.getId());
                 return ps;
             }
         });
         return result;
     }

    /**
     *@Author  huangjie
     *@Description 删除用户
     *@Date  2018/7/18 11:57
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteUserByName(String userName){
        String sql ="update user_info set status=?  where userName=?";
        logger.info(sql);
        int result=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,ConstDefine.STATE_DELETE);
                ps.setString(2,userName);
                return ps;
            }
        });
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 查询用户拥有的权限信息
     *@Date  2018/7/16 9:11
     *@Param  用户id
     *@Return  权限列表
     *@Modyfied by
     */
    public List<PermissionModel> getUserPermissionById(Integer id){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT permissionId as id,permissionName ,status FROM (SELECT a.username,d.permissionId,e.permissionName,e.status  ");
        sql.append(" FROM user_info a ");
        sql.append("LEFT JOIN user_role b ON a.id=b.userid ");
        sql.append("LEFT JOIN role c ON b.roleId=c.id ");
        sql.append("LEFT JOIN role_permission d ON b.roleId=d.roleId ");
        sql.append("LEFT JOIN permission e ON d.permissionId=e.id ");
        sql.append("WHERE a.id=? ");
        sql.append("UNION ");
        sql.append("SELECT a.`userName`,b.permId,c.permissionName,c.status FROM user_info a ");
        sql.append("LEFT JOIN user_permission b ON a.id=b.userId AND b.status=? ");
        sql.append("LEFT JOIN permission c ON b.permId=c.id ");
        sql.append("WHERE a.id=? ) AS t ");
        sql.append("WHERE t.permissionId NOT IN (SELECT permid FROM user_permission WHERE userid=? AND STATUS=?)");
        List<PermissionModel> list=null;
        try {
            list=jdbcTemplate.query(sql.toString(),new Object[]{id,ConstDefine.STATE_ABLE,id,id,ConstDefine.STATE_DELETE},PermissionModel.PermissionMapper.INSTANCE);
        }catch (Exception e){
            e.printStackTrace();
            throw new DBErrorException("用户权限查询DBException");
        }
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 查询用户的角色信息 
     *@Date  2018/7/16 10:06
     *@Param  
     *@Return 
     *@Modyfied by
     */
    public List<RoleModel> getUserRoleById(Integer id){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT b.roleid as id,c.roleName,c.status FROM user_info  a ");
        sql.append("LEFT JOIN user_role b ON a.id=b.userId ");
        sql.append("LEFT JOIN role c ON c.id=b.roleId and c.status=? ");
        sql.append("WHERE a.id=?");
        List<RoleModel> list=null;
        try{
            list=jdbcTemplate.query(sql.toString(),new Object[]{ConstDefine.STATE_ABLE,id},RoleModel.RoleMapper.INSTANCE);
        }catch (Exception e){
            throw new DBErrorException("用户角色查询DBException");
        }
        return list;
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
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT permissionId,permissionName,STATUS,NotOperate(GROUP_CONCAT(V)) AS optvalue FROM( ");
        sql.append("SELECT permissionId,permissionName,STATUS,AndOperate(GROUP_CONCAT(V)) AS V FROM ");
        sql.append("(SELECT `permissionId`,`permissionName`,f.`status`,SUM(g.`value` ) AS v  ");
        sql.append("FROM (SELECT a.username,d.`permissionId`,e.`permissionName`,e.`status` FROM user_info a  ");
        sql.append("INNER JOIN user_role b ON a.`id`=b.userid ");
        sql.append("INNER JOIN role c ON b.`roleId`=c.`id` ");
        sql.append("INNER JOIN role_permission d ON b.`roleId`=d.`roleId` ");
        sql.append("INNER JOIN permission e ON d.`permissionId`=e.`id` AND e.status=? ");
        sql.append("WHERE a.userName=? ");
        sql.append("UNION ");
        sql.append("SELECT a.`userName`,b.permId,c.permissionName,c.`status` FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` and c.status=? ");
        sql.append("WHERE a.`userName`=? GROUP BY b.permId ) AS t  ");
        sql.append("INNER JOIN permission_operate f ON permissionId=permId ");
        sql.append("INNER JOIN operate_function g ON operId=g.`id`  ");
        sql.append("GROUP BY t.permissionId ");
        sql.append("UNION ");
        sql.append("SELECT b.permId AS permissionId,c.permissionName,c.`status`,SUM(e.value) AS V FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` AND c.status=? ");
        sql.append("INNER JOIN operate_function e ON b.operId=e.`id`  ");
        sql.append("WHERE a.`userName`=? GROUP BY b.permId) AS tmptable1 ");
        sql.append("GROUP BY permissionId ");
        sql.append("UNION ");
        sql.append("SELECT b.permId AS permissionId,c.permissionName,c.`status`,SUM(e.value) AS V FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` AND c.status=? ");
        sql.append("INNER JOIN operate_function e ON b.operId=e.`id` ");
        sql.append("WHERE a.`userName`=? GROUP BY b.permId ");
        sql.append(") AS tmptable2 GROUP BY permissionId ");
        Map<String,Integer> map=new HashMap<String,Integer>();
        List<String> list=jdbcTemplate.query(sql.toString(), new Object[]{
                ConstDefine.STATE_ABLE,userName,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userName,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,
                userName,ConstDefine.STATE_DELETE,ConstDefine.STATE_ABLE,userName
        }, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException{
                String s =
                        rs.getString("permissionName")+"-"+rs.getInt("optvalue")+"-"+rs.getInt("permissionId");
                return s;
            }
        });
        return list;
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
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT permissionId,permissionName,STATUS,NotOperate(GROUP_CONCAT(V)) AS optvalue FROM( ");
        sql.append("SELECT permissionId,permissionName,STATUS,AndOperate(GROUP_CONCAT(V)) AS V FROM ");
        sql.append("(SELECT `permissionId`,`permissionName`,f.`status`,SUM(g.`value` ) AS v  ");
        sql.append("FROM (SELECT a.username,d.`permissionId`,e.`permissionName`,e.`status` FROM user_info a  ");
        sql.append("INNER JOIN user_role b ON a.`id`=b.userid ");
        sql.append("INNER JOIN role c ON b.`roleId`=c.`id` ");
        sql.append("INNER JOIN role_permission d ON b.`roleId`=d.`roleId` ");
        sql.append("INNER JOIN permission e ON d.`permissionId`=e.`id` AND e.status=? ");
        sql.append("WHERE a.id=? ");
        sql.append("UNION ");
        sql.append("SELECT a.`userName`,b.permId,c.permissionName,c.`status` FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` and c.status=? ");
        sql.append("WHERE a.`id`=? GROUP BY b.permId ) AS t  ");
        sql.append("INNER JOIN permission_operate f ON permissionId=permId ");
        sql.append("INNER JOIN operate_function g ON operId=g.`id`  ");
        sql.append("GROUP BY t.permissionId ");
        sql.append("UNION ");
        sql.append("SELECT b.permId AS permissionId,c.permissionName,c.`status`,SUM(e.value) AS V FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` AND c.status=? ");
        sql.append("INNER JOIN operate_function e ON b.operId=e.`id`  ");
        sql.append("WHERE a.`id`=? GROUP BY b.permId) AS tmptable1 ");
        sql.append("GROUP BY permissionId ");
        sql.append("UNION ");
        sql.append("SELECT b.permId AS permissionId,c.permissionName,c.`status`,SUM(e.value) AS V FROM user_info a  ");
        sql.append("INNER JOIN user_permission b ON a.`id`=b.`userId` AND b.status=? ");
        sql.append("INNER JOIN permission c ON b.permId=c.`id` AND c.status=? ");
        sql.append("INNER JOIN operate_function e ON b.operId=e.`id` ");
        sql.append("WHERE a.`id`=? GROUP BY b.permId ");
        sql.append(") AS tmptable2 GROUP BY permissionId ");
        Map<String,Integer> map=new HashMap<String,Integer>();
        List<String> list=jdbcTemplate.query(sql.toString(), new Object[]{
                ConstDefine.STATE_ABLE,userId,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,userId,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,
                userId,ConstDefine.STATE_DELETE,ConstDefine.STATE_ABLE,userId
        }, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException{
                String s =
                        rs.getString("permissionName")+"-"+rs.getInt("optvalue")+"-"+rs.getInt("permissionId");
                return s;
            }
        });
        return list;
    }



    /**
     *@Author  huangjie
     *@Description 根据当前用户拥有的角色查询所拥有的的菜单的list
     *@Date  2018/7/17 16:38
     *@Param  用户Id
     *@Return 菜单list
     *@Modyfied by
     */
    public List<MenuModel> getAllMenuByUserId(Integer id){
        StringBuilder sql=new StringBuilder("");
        sql.append(" SELECT id,menuName,menuUrl,parentMenuId,STATUS FROM  ");
        sql.append("(SELECT c.`menuId` AS id,d.`menuName`,d.`menuUrl`,d.`parentMenuId`,d.`status` FROM user_info a ");
        sql.append("LEFT JOIN user_role b ON a.`id`=b.`userId` ");
        sql.append("LEFT JOIN role_menu c ON b.`roleId`=c.`roleId` AND c.status=? ");
        sql.append("LEFT JOIN menu d ON c.`menuId`=d.`id` AND d.status=? ");
        sql.append("WHERE a.id=? union ");
        sql.append(" SELECT a.menuId,b.menuName,b.menuUrl,b.parentMenuId,b.status FROM user_menu a ");
        sql.append(" LEFT JOIN menu b ON a.menuId=b.id ");
        sql.append(" WHERE a.userId=? AND a.`status`=?) AS tmp ");
        sql.append(" WHERE id NOT IN ");
        sql.append("(SELECT menuId FROM user_menu WHERE userId=? and STATUS=?) ");
        List<MenuModel> list=jdbcTemplate.query(sql.toString(),new Object[]{
                ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,id,id,ConstDefine.STATE_ABLE,id,ConstDefine.STATE_DELETE
        },MenuModel.MenuMapper.INSTANCE);
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 获取该用户一级菜单的列表
     *@Date  2018/7/17 17:03
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getFirstLevelMenu(Integer id){
        List<MenuModel> result=new ArrayList<>();
        List<MenuModel> list=this.getAllMenuByUserId(id);
        Tree<MenuModel> tree=new Tree<>(-1,list);
        List<Node> nodes=tree.build();
        Node node=null;
        for (int i = 0; i < nodes.size(); i++) {
            Node n=nodes.get(i);
            if (n.getParentId()==-1) {
                for (int i1 = 0; i1 < n.getChildren().size(); i1++) {
                     node=n.getChildren().get(i1);
                    result.add(new MenuModel(
                            node.getId(),node.getParentId(),node.getText(),node.getState(),""
                    ));
                }
            }
        }
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 获取登陆用户的指定菜单下面的子菜单
     *@Date  2018/7/17 17:24
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<MenuModel> getChildrenMenu(Integer userId,Integer menuId){
        List<MenuModel> result=new ArrayList<>();
        List<MenuModel> list=this.getAllMenuByUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId()==menuId) {
                result.add(list.get(i));
            }
        }
        return result;
    }

}
