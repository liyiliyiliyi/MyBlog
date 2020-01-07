package util;

import dao.UserDao;
import idao.IUserDao;
import model.User;

import javax.servlet.http.HttpServletRequest;

public class LoginUtils {

	public static User login(HttpServletRequest request) {

        User user = null;
	    //从前端页面文本输入框获取值
		String username = request.getParameter("username");
		String password = request.getParameter("password");


		IUserDao dao = UserDao.getInstance();
		user = dao.login(username,password);


		return user;
	}

}
