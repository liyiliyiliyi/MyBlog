package util;

import dao.UserDao;
import idao.IUserDao;
import model.User;

import javax.servlet.http.HttpServletRequest;

public class RegisterUtils {
    public static void register(HttpServletRequest request) {
        User user = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        user.setUser_name(username);
//        user.setUser_password(password);

        IUserDao dao = UserDao.getInstance();
        boolean f = dao.register(username,password);
        if (f == false)
            return;
    }
}
