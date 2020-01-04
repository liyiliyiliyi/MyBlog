package servlet;

import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/TimeLineServlet")
public class TimeLineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取时间轴文章类型的数据
        ArticleService as = ArticleService.getInstance();
//        request.setAttribute("axis_list", as.getTimeLineList());

        // 转发
        request.getRequestDispatcher("/pages/timeline.jsp").forward(request, response);
    }
}
