package servlet;

import idao.IArticleDao;
import model.User;
import service.ArticleService;
import service.TagService;
import util.LoginUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User user = LoginUtils.login(request);

        if(user.getUser_name() == null || user.getUser_password() == null){
            //登录失败
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

        //登录成功  写入session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        //为什么服务器跳转不行,使用客户端跳转可以实现过滤器功能
       //  request.getRequestDispatcher("pages/main.jsp").forward(request,response);
        response.sendRedirect("pages/main.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
