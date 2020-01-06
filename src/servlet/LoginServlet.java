package servlet;

import idao.IArticleDao;
import service.ArticleService;
import service.TagService;
import util.LoginUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if(LoginUtils.login(request) == false){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

        //为什么服务器跳转不行,使用客户端跳转可以实现过滤器功能
       //  request.getRequestDispatcher("pages/main.jsp").forward(request,response);
        response.sendRedirect("pages/main.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
