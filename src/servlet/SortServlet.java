
package servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.control.Alert;
import service.ArticleService;
import util.StringUtils;

@WebServlet("/servlet/SortServlet")
public class SortServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("test");
        // 获取的是所有（all）分类 也可以是一个分类的文章
        String get = StringUtils.pareCode(request.getParameter("get"));
        // 初始化分类和和文章
        ArticleService as = ArticleService.getInstance();
        //把从数据库中得到的文章map发出去
        request.setAttribute("sort_article_map", as.getSortAndAirticle(get));
        // 转发
        request.getRequestDispatcher("/pages/sort.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
