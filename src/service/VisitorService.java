package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import util.DBUtils;
import util.DateUtils;

public class VisitorService {

    /**
     * 全部浏览者
     *
     * @return
     */
    public static int totalVisit() {

        int result = 0;

        String sql = "select * from visitor";
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result += 1;
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
    public static int totalMember() {
        int result = 0;
        String sql = "SELECT COUNT(DISTINCT(ip)) FROM visitor";
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
