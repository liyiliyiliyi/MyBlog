package servlet;

import dao.ArticleDao;
import dao.TagDao;
import service.ArticleService;
import service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/AskCountsServlet")
public class AskCountsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回标签数，日志数，日志种类数
        int tagCounts = TagService.getInstance().getTagCounts();
        int articleCounts = ArticleService.getInstance().getArticleCounts();
        int articleSortCounts = ArticleService.getInstance().getArticleSortCounts();

        //传标签的前三
//        List list = TagService.getInstance().getTagCounts();
        response.getWriter().write("tagCounts=" + tagCounts + "&articleCounts=" + articleCounts + "&articleSortCounts=" + articleSortCounts);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
