package dao;

import idao.IUserDao;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import util.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDao implements IUserDao{

    private static IUserDao instance;

    public  static final IUserDao getInstance() {
        if(instance == null) {
            try {
                instance =  new UserDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public boolean register(String username, String password) {
        PreparedStatement pstatement;
        String sql = "insert into user values(null,?,?)";
        int count = 0;
        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setString(1,username);
            pstatement.setString(2,password);
            count = pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        PreparedStatement pstatement;
        String sql = "select * from user where user_name=? and user_password=?";

        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setString(1,username);
            pstatement.setString(2,password);
            ResultSet rs = pstatement.executeQuery();

            if(rs.next()) {
                Map<String, String> map = new HashMap<>();
                user = new User();
                map.put("username",rs.getString("user_name"));
                map.put("password",rs.getString("user_password"));
                map.put("user_id",rs.getString("user_id"));
                try {
                    BeanUtils.populate(user,map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.Close(pstatement, null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
