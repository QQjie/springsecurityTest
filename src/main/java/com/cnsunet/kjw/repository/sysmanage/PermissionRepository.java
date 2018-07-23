package com.cnsunet.kjw.repository.sysmanage;

import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.utils.ConstDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 14:49 2018/7/13
 * @Modified By:
 */
@Repository
public class PermissionRepository {
    private Logger logger=LoggerFactory.getLogger(MenuRepository.class);

    @Autowired
    @Qualifier("myJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleRepository roleRepository;

    /**
     *@Author  huangjie
     *@Description 增加权限管理模块
     *@Date  2018/7/13 14:51
     *@Param
     *@Return
     *@Modyfied by
     */
    public int addPermission(PermissionModel permissionModel){
        String sql="insert into permission(permissionName,status) values(?,?)";
        logger.info(sql);
        KeyHolder key=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,permissionModel.getPerssionName());
                ps.setInt(2,permissionModel.getStatus());
                return ps;
            }
        },key);
        return key.getKey().intValue();
    }
    /**
     *@Author  huangjie
     *@Description 查询所有的权限
     *@Date  2018/7/13 15:10
     *@Param  
     *@Return 
     *@Modyfied by
     */
    public List<PermissionModel> getAllPermission(){
        String sql ="select id,permissionName,status from permission where status=?";
        logger.info(sql);
        List<PermissionModel> list=jdbcTemplate.query(sql,new Object[]{ConstDefine.STATE_ABLE},PermissionModel
                .PermissionMapper.INSTANCE);
        return list;
    }
    /**
     *@Author  huangjie
     *@Description 修改权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePermission(PermissionModel permissionModel){
        String sql ="update permission set permissionName=? where id=? and status=?";
        logger.info(sql);
        int result=jdbcTemplate.update(sql,new Object[]{permissionModel.getPerssionName(),permissionModel.getId(),ConstDefine.STATE_ABLE});
        return result;
    }

    /**
     *@Author  huangjie
     *@Description 删除权限信息
     *@Date  2018/7/13 15:10
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePermission(Integer permId){
        String sql ="update permission set status=? where id=?";
        logger.info(sql);
        int result=jdbcTemplate.update(sql,new Object[]{ConstDefine.STATE_DELETE,permId});
        return result;
    }

    /**
     *@Author  huangjie
     *@Description  给角色划分权限
     *@Date  2018/7/18 17:22
     *@Param
     *@Return
     *@Modyfied by
     */
     public int addPmsForRole(Integer roleId,List<Integer> pmsIds){
         String sql="insert into role_permission(roleId,permissionId) values(?,?)";
         logger.info(sql);
         jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
             @Override
             public void setValues(PreparedStatement ps, int i) throws SQLException {
                 ps.setInt(1,roleId);
                 ps.setInt(2,pmsIds.get(i));
             }

             @Override
             public int getBatchSize() {
                 return pmsIds.size();
             }
         });
         return pmsIds.size();
     }
    /**
     *@Author  huangjie
     *@Description  删除角色的权限
     *@Date  2018/7/18 17:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePmsForRole(Integer roleId,List<Integer> pmsIds){
        String sql="update role_permission set status=? where roleId=? and permissionId=?";
        logger.info(sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,ConstDefine.STATE_DELETE);
                ps.setInt(2,roleId);
                ps.setInt(3,pmsIds.get(i));
            }

            @Override
            public int getBatchSize() {
                return pmsIds.size();
            }
        });
        return pmsIds.size();
    }

    /**
     *@Author  huangjie
     *@Description  删除角色的权限
     *@Date  2018/7/18 17:22
     *@Param
     *@Return
     *@Modyfied by
     */
    public int deletePmsForRoleById(Integer roleId){
        String sql="update role_permission set status=? where roleId=? ";
        logger.info(sql);
        int result=jdbcTemplate.update(new PreparedStatementCreator() {
                 @Override
                 public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                     PreparedStatement ps=con.prepareStatement(sql);
                     ps.setInt(1,ConstDefine.STATE_DELETE);
                     ps.setInt(2,roleId);
                     return ps;
                 }
             }
        );
        return result;
    }

    /**
      *@Author  huangjie
      *@Description 给角色修改权限
      *@Date  2018/7/18 17:41
      *@Param  
      *@Return 
      *@Modyfied by
      */
     public int updatePmsForRole(Integer roleId,List<Integer> pmsIds){
        //删除旧的
        this.deletePmsForRoleById(roleId);
        String sql ="insert into role_permission(roleId,permissionId,status) values(?,?,?) on DUPLICATE KEY update status=values(status)";
        logger.info(sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,roleId);
                ps.setInt(2,pmsIds.get(i));
                ps.setInt(3,ConstDefine.STATE_ABLE);
            }
            @Override
            public int getBatchSize() {
                return pmsIds.size();
            }
        });
        return pmsIds.size();
     }
     /**
      *@Author  huangjie
      *@Description 删除权限拥有的操作
      *@Date  2018/7/19 10:51
      *@Param
      *@Return
      *@Modyfied by
      */
     public int deleteOperForPems(Integer permId){
         String sql="update permission_operate set status=? where permId=?";
         logger.info(sql);
         int result=jdbcTemplate.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                 PreparedStatement ps=con.prepareStatement(sql);
                 ps.setInt(1,ConstDefine.STATE_DELETE);
                 ps.setInt(2,permId);
                 return ps;
             }
         });
         return result;
     }

    /**
     *@Author  huangjie
     *@Description 给权限修改操作选项
     *@Date  2018/7/18 17:41
     *@Param
     *@Return
     *@Modyfied by
     */
    public int updatePmsOperate(Integer permId,List<Integer> operIds){
        //删除旧的
        this.deleteOperForPems(permId);
        String sql ="insert into permission_operate(permId,operId,status) values(?,?,?) on DUPLICATE KEY update status=values(status)";
        logger.info(sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,permId);
                ps.setInt(2,operIds.get(i));
                ps.setInt(3,ConstDefine.STATE_ABLE);
            }
            @Override
            public int getBatchSize() {
                return operIds.size();
            }
        });
        return operIds.size();
    }




}
