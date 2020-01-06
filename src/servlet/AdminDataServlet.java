package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.ArticleService;
import service.TagService;
import util.StringUtils;

@WebServlet("/servlet/AdminDataServlet")
public class AdminDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String op = request.getParameter("op");
        AdminService as = AdminService.getInstance();

        switch (op) {

            case "edit_article":
                System.out.println("ceshi1");
                //通过id得到一篇文章
                String a_id1 = request.getParameter("article_id");
                request.setAttribute("edit_article", as.getArticle(a_id1));
                request.getRequestDispatcher("/pages/edit.jsp").forward(request, response);
                break;

            case "delete_article":
                System.out.println("ceshi2");
                String a_id2 = request.getParameter("article_id");
                as.delteArticle(a_id2);

                break;

        }
    }
}
