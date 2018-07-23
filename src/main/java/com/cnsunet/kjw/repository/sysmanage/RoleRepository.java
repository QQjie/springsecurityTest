package com.cnsunet.kjw.repository.sysmanage;

import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.RoleModel;
import com.cnsunet.kjw.utils.ConstDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :角色管理repository
 * @Date: Created in 10:34 2018/7/13
 * @Modified By:
 */
@Repository
public class RoleRepository {
    private Logger logger=LoggerFactory.getLogger(MenuRepository.class);

    @Autowired
    @Qualifier("myJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    /**
     *@Author  huangjie
     *@Description 添加一个角色
     *@Date  2018/7/13 10:34
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addRole(RoleModel roleModel){
        String sql="insert into role(roleName,priority) values(?,?);";
        KeyHolder key=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,roleModel.getRoleName());
                ps.setInt(2,roleModel.getPriority());
                return ps;
            }
        },key);
        return key.getKey().intValue();
    }
    /**
     *@Author  huangjie
     *@Description 删除
     *@Date  2018/7/13 14:38
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deleteRoleById(Integer id){
        String sql="update role set status=? where id=?";
        logger.info(sql);
        int result=jdbcTemplate.update(sql,new Object[]{ConstDefine.STATE_DELETE,id});
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 修改
     *@Date  2018/7/13 14:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updateRole(RoleModel roleModel){
        String sql="update role set roleName=?,priority=? where id=?";
        logger.info(sql);
        int result=jdbcTemplate.update(sql,new Object[]{roleModel.getRoleName(),roleModel.getPriority(),roleModel.getId()});
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 查询所有角色信息
     *@Date  2018/7/13 14:43
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> getAllRole(){
        String sql="select id,roleName,priority,status from role where status=?";
        logger.info(sql);
        List<RoleModel> list=jdbcTemplate.query(sql,new Object[]{ConstDefine.STATE_ABLE},RoleModel.RoleMapper.INSTANCE);
        return  list;
    }
    /**
     *@Author  huangjie
     *@Description 根据id查询角色信息
     *@Date  2018/7/13 14:43
     *@Param
     *@Return
     *@Modyfied by
     */
    public RoleModel getRoleById(Integer id){
        String sql="select id,roleName,priority,status from role where id=? and status=?";
        logger.info(sql);
        List<RoleModel> list=jdbcTemplate.query(sql,new Object[]{id,ConstDefine.STATE_ABLE},RoleModel.RoleMapper.INSTANCE);
        if(list==null||list.size()==0){
            return null;
        }
        return  list.get(0);
    }

    /**
     *@Author  huangjie
     *@Description 根据id查询角色信息
     *@Date  2018/7/13 14:43
     *@Param
     *@Return
     *@Modyfied by
     */
    public RoleModel getRoleByRoleName(String name){
        String sql="select id,roleName,priority,status from role where roleName=? and status=?";
        logger.info(sql);
        List<RoleModel> list=jdbcTemplate.query(sql,new Object[]{name,ConstDefine.STATE_ABLE},RoleModel.RoleMapper.INSTANCE);
        if(list==null||list.size()==0){
            return null;
        }
        return  list.get(0);
    }

    /**
     *@Author  huangjie
     *@Description 查询当前用户优先级以下的所有角色
     *@Date  2018/7/18 14:53
     *@Param  userName
     *@Return
     *@Modyfied by
     */
    public List<RoleModel> getAllBelowRoles(String userName){
        StringBuilder sql=new StringBuilder("");
        sql.append("SELECT d.id,d.roleName,d.priority,d.status FROM user_info a  ");
        sql.append("LEFT JOIN user_role b ON a.id=b.userId AND b.status=? ");
        sql.append("LEFT JOIN role c ON b.roleid=c.id AND c.status=? ");
        sql.append("LEFT JOIN role d ON c.priority<=d.priority AND d.status=? ");
        sql.append("WHERE a.username=? AND a.status=? ");
        List<RoleModel> list=null;
        try{
            list=jdbcTemplate.query(sql.toString(),new Object[]{
                    ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,ConstDefine.STATE_ABLE,
                    userName,ConstDefine.STATE_ABLE
            },RoleModel.RoleMapper.INSTANCE);
        }catch (Exception e){
            throw new DBErrorException("查询当前用户优先级一下的所有角色");
        }
        return list;
    }

    /**
     *@Author  huangjie
     *@Description 给当前用户新增一个角色
     *@Date  2018/7/18 15:11
     *@Param  userId,RoleModel
     *@Return
     *@Modyfied by
     */
    public int addUserRole(Integer userId,Integer roleId){
        String sql="insert into user_role(userId,roleId) values(?,?)";
        KeyHolder key=new GeneratedKeyHolder();
        logger.info(sql);
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1,userId);
                ps.setInt(2,roleId);
                return ps;
            }
        },key);
        return key.getKey().intValue();
    }
    /**
     *@Author  huangjie
     *@Description 修改当前用户拥有的角色
     *@Date  2018/7/18 15:11
     *@Param  userId,RoleModel
     *@Return
     *@Modyfied by
     */
    public int updateUserRole(Integer userId,Integer roleId){
        String sql="update user_role set roleId=? where userId=? and status=?";
        logger.info(sql);
        Integer result=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,roleId);
                ps.setInt(2,userId);
                ps.setInt(3,ConstDefine.STATE_ABLE);
                return ps;
            }
        });
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 删除当前用户拥有的角色
     *@Date  2018/7/18 15:11
     *@Param  userId,RoleModel
     *@Return
     *@Modyfied by
     */
    public int deleteUserRole(Integer userId,Integer roleId){
        String sql="update user_role set status=? where  roleId=? and userId=? ";
        logger.info(sql);
        Integer result=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,ConstDefine.STATE_DELETE);
                ps.setInt(2,roleId);
                ps.setInt(3,userId);
                return ps;
            }
        });
        return result;
    }
    /**
     *@Author  huangjie
     *@Description 查询当前角色拥有的所有权限id
     *@Date  2018/7/18 17:48
     *@Param
     *@Return
     *@Modyfied by
     */
    public List<Integer> getPmsIdsRole(Integer roleId){
        String sql="select permissionId from role_permission where roleId=? and status=?";
        logger.info(sql);
        List<Integer> list=jdbcTemplate.query(sql, new Object[]{roleId, ConstDefine.STATE_ABLE}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("permissionId");
            }
        });
        return list;
    }

}
