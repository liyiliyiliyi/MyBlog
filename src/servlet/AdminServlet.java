package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import service.ArticleService;
import service.TagService;
import service.VisitorService;

@WebServlet("/servlet/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 传所有的文章
        ArticleService as = ArticleService.getInstance();
        request.setAttribute("articles", as.getAllArticle());

        // 传所有的分类
        request.setAttribute("sort", as.getAllSort());

        // 传所有的标签
        TagService ts = TagService.getInstance();
        request.setAttribute("tags", ts.getAllTag());

        // 传网站的统计数据
        request.setAttribute("visited", VisitorService.totalVisit());
        request.setAttribute("member", VisitorService.totalMember());


        // 转发
        request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
    }
}
