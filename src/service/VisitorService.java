package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import util.DBUtils;


public class VisitorService {
    /**
     * 全部浏览者
     *
     * @return
     */
    public static int totalMember() {

        int result = 0;

        String sql = "select * from visitor";
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 result = rs.getInt(1);
            }
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 第几个浏览者
     *
     * @return
     */
    public static int totalVisit() {
        int result = 0;
        String sql = "select count(v_id) from visitor";
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
