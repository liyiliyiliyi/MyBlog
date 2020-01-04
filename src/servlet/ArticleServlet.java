package servlet;

import model.Article;
import service.ArticleService;
import service.CommentService;
import service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 想要获取的文章 id
        String id = request.getParameter("id");
        System.out.println(id);
        ArticleService as = ArticleService.getInstance();

        //文章-----get(0)没弄清楚
        Article a = as.getArticle("article_id", id).get(0);
        request.setAttribute("article", a);

        // 文章的所有标签
   //     TagService ts = TagService.getInstance();
  //      request.setAttribute("article_tags", ts.getTagById(id));

        // 获取上一篇文章
        request.setAttribute("article_pre", as.getPreviousArticle(a.getTime()));

        // 获取下一篇文章
        request.setAttribute("article_next", as.getNextArticle(a.getTime()));

        // 加载文章评论
        CommentService cs = CommentService.getInstance();
        request.setAttribute("comment", cs.loadComment(a.getId()));

        //服务器跳转
        request.getRequestDispatcher("../pages/article.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
