package idao;

import model.User;

import java.util.List;

public interface IUserDao {

    /**
     * 注册用户
     * @param
     * @return
     */
    boolean register(String username, String password);

    /**
     * 登录验证
     * @param
     * @return
     */
    User login(String username, String password);
}
