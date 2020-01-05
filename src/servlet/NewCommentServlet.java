package servlet;

import model.Comment;
import service.CommentService;
import util.DateUtils;
import util.FailException;
import util.Form2Bean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/servlet/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String cookie_name = "comment_cookie" + request.getParameter("id");
        System.out.println(cookie_name);
        // 判断是否恶意提交(即重复提交)
        boolean isRepeat = false;

        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            for(Cookie c : cookies) {
                if(c.getName().equals(cookie_name)) {
                    isRepeat = true;
                    break;
                }
            }
        }

        //返回信息
        String info;
        if(!isRepeat) {
            Comment comment;
            try {
                comment = Form2Bean.commentForm2Bean(request);
                CommentService cs = CommentService.getInstance();
                boolean result = cs.addComment(comment);

                if (!result) {
                    info = "comment failed!";
                }else {
                    info = "comment success!";
                }
            } catch (FailException e) {
                e.printStackTrace();
                info = "comment failed!";
            }
        }else {
            info = "repeat submit comment!";
        }

        System.out.println(info);
        // 将评论信息存储cookie，用于上面判断
        Cookie c = new Cookie("cookie_name", cookie_name);
        //设置在磁盘生存的时间为一分钟
        c.setMaxAge(3600);
        //设置Cookie存储的路径---项目名字
        c.setPath("/MyBlog");
        //将Cookie添加到返回信息
        response.addCookie(c);

        request.setAttribute("info", info);
        //服务端跳转
        request.getRequestDispatcher("ArticleServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
