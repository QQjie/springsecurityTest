package com.cnsunet.kjw.repository;

import com.cnsunet.kjw.model.FlowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowAllRepository {

    @Autowired
    @Qualifier("flowJdbcTemplate")
    private JdbcTemplate flowJdbcTemplate;

    public List<FlowModel> getFlowModelsByTime(String areaName) {
        String sql="SELECT * FROM tbl_topic_linklayerinfo WHERE recordtime=1531152000 AND areaname=? ";
        List<FlowModel> list= new ArrayList<FlowModel>();
        Object obj= flowJdbcTemplate.query(sql, new Object[]{areaName}, new RowMapper<FlowModel>() {
            @Override
            public FlowModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                FlowModel fm=new FlowModel();
                fm.setIn(rs.getFloat("bytesin"));
                fm.setAreaName(rs.getString("areaName"));
                fm.setAll(rs.getFloat("bytesout"));
                fm.setLinkLayerProtocal(rs.getInt("LinkLayerProtocol"));
                fm.setRecordTime(rs.getString("recordTime"));
                return fm;
            }
        });
        return (List<FlowModel>)obj;
    }
}
