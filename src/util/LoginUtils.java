package util;

import dao.UserDao;
import idao.IUserDao;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class LoginUtils {

	public static boolean login(HttpServletRequest request) {

        User user = null;
	    //从前端页面文本输入框获取值
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//如果为空，则...
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return false;


		IUserDao dao = UserDao.getInstance();
		user = dao.login(username,password);

		//如果用户名密码不一致,则user的属性为空
		if (user.getUser_name() == null || user.getUser_password() == null)
			return false;


		// 写入session
		HttpSession session = request.getSession();

		session.setAttribute("user", user);

		return true;
	}

}
