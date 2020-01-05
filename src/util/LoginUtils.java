package util;

import dao.UserDao;
import idao.IUserDao;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class LoginUtils {

	public static void login(HttpServletRequest request) {

        User user = null;
	    //从前端页面文本输入框获取值
		String username = request.getParameter("username");
		String password = request.getParameter("password");


		//如果为空，则...
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return;

		IUserDao dao = UserDao.getInstance();
		user = dao.login(username,password);
		if (user == null)
			return;

		// 写入session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

	}

}
