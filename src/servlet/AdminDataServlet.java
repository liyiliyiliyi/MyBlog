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

                //通过id得到一篇文章
                String a_id1 = request.getParameter("article_id");
                request.setAttribute("edit_article", as.getArticle(a_id1));
                //得到一类文章个数
                ArticleService ase = ArticleService.getInstance();
                Map sort_count = ase.getSortAndCount();
                request.setAttribute("sort_count", sort_count);
                System.out.println("count");

                TagService tg = TagService.getInstance();
                List all_tag = tg.getAllTag();
                request.setAttribute("all_tag", all_tag);
                System.out.println("tags");
                request.getRequestDispatcher("/pages/edit.jsp").forward(request, response);
                break;

            case "delete_article":
                String a_id2 = request.getParameter("article_id");
                as.delteArticle(a_id2);

                break;

            case "sort_update":
                String old_sort = StringUtils.pareCode(request.getParameter("old_sort"));
                String new_sort = StringUtils.pareCode(request.getParameter("new_sort"));
                if (!old_sort.equals(new_sort)) {
                    as.updateSort(old_sort, new_sort);
                }
                break;
            case "sort_delete":
                String sort = StringUtils.pareCode(request.getParameter("sort"));
                as.deleteSort(sort);
                break;
            case "tag_update":
                String old_tag = StringUtils.pareCode(request.getParameter("old_tag"));
                String new_tag = StringUtils.pareCode(request.getParameter("new_tag"));
                if (!old_tag.equals(new_tag)) {
                    as.updateTag(old_tag, new_tag);
                }
                break;
            case "tag_delete":
                String tag = StringUtils.pareCode(request.getParameter("tag"));
                as.deleteTag(tag);
                break;
            default:
                break;

        }
    }
}
