package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import service.TagService;
import util.StringUtils;
@WebServlet(name = "/servlet/TagServlet")
public class TagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // getparameter? no dont exist here.
        String get = StringUtils.pareCode(request.getParameter("get"));
        // 初始化标签
        TagService ts = TagService.getInstance();
        request.setAttribute("id_tag_map", ts.getTagAndArticle(get));

        request.getRequestDispatcher("/pages/tags.jsp").forward(request, response);
    }
}
