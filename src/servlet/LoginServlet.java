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
        //将请求转到该方法处理
        LoginUtils.login(request);

        ArticleService as = ArticleService.getInstance();
        TagService ts = TagService.getInstance();

        // 初始化侧边栏 日志、分类、标签的个数
        request.setAttribute("article_number",as.getCount(IArticleDao.SEARCH_ARTICLE));
        request.setAttribute("sort_number", as.getCount(IArticleDao.SEARCH_SORT));
        request.setAttribute("tags_number",ts.getTagCounts());

        // 阅读排行
        request.setAttribute("visit_rank",as.getVisitRank());

        // 初始化文章列表
        request.setAttribute("article_list",as.getArticle());

        //


        //服务端跳转到main.jsp
        request.getRequestDispatcher("pages/main.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
