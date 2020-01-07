package servlet;

import model.Comment;
import net.sf.json.JSONObject;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StarCommentServlet")
public class StarCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取评论id
        String id = request.getParameter("id");
        String diss_or_star = request.getParameter("diss_or_star");
        // 返回的数据
        JSONObject jo = new JSONObject();


        //判断是否为以前是diss还是Star评论，还是什么都没操作
        int flag = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("star_cm" + id) && "starOver".equals(cookie.getValue())) {
                //顶过评论
                flag = 1;
            } else if (cookie.getName().equals("star_cm" + id) && "dissOver".equals(cookie.getValue())) {
                //踩过评论
                flag = 2;
            } else {
                flag = 0;
            }
        }


        CommentService cs = CommentService.getInstance();
        int diss = 0;
        int star = 0;

        //判断是diss还是star
        boolean judge = false;
        if (diss_or_star.equals("star")) {
            judge = true;
        } else {
            judge = false;
        }

        //要点赞
        Cookie cookie;
        if (judge) {
            //发送新的cookie
            cookie = new Cookie("star_cm" + id,  "starOver");
            response.addCookie(cookie);
            switch (flag) {
                case 1 :
                    //点过赞
                    diss = cs.dissCounts(Integer.parseInt(id));
                    star = cs.starCounts(Integer.parseInt(id));
                    jo.put("star", "starFailed");
                    break;
                case 2 :
                    //把diss变成点赞,star+1,diss-1
                    diss = cs.dissComment(Integer.parseInt(id), Comment.DISS);
                    star = cs.starComment(Integer.parseInt(id), Comment.STAR);
                    jo.put("star", "starSuccess");
                    break;
                case 0 :
                    //点赞
                    star = cs.starComment(Integer.parseInt(id), Comment.STAR);
                    jo.put("star", "starSuccess");
                    break;
                default:
                    break;

            }
        }else {

            //发送新的cookie
            cookie = new Cookie("star_cm" + id,  "dissOver");
            response.addCookie(cookie);
            switch (flag) {
                case 1:
                    //diss过
                    jo.put("diss", "dissFailed");
                    diss = cs.dissCounts(Integer.parseInt(id));
                    star = cs.starCounts(Integer.parseInt(id));
                    break;
                case 2:
                    //把点赞变成diss,star-1,diss+1
                    star = cs.starComment(Integer.parseInt(id), Comment.STAR);
                    diss = cs.dissComment(Integer.parseInt(id), Comment.DISS);
                    jo.put("diss", "dissSuccess");
                    break;
                case 0:
                    //diss
                    jo.put("diss", "dissSuccess");
                    diss = cs.starComment(Integer.parseInt(id), Comment.DISS);
                    break;
                    default:
                        break;
            }
        }
        jo.put("starcount", star);
        jo.put("disscount", diss);
        // 设置有效期 15分钟
        cookie.setMaxAge(15 * 60);
        // 设置有效目录
        cookie.setPath("/MyBlog/pages/article.jsp");
        // 写回ajax
        response.getWriter().println(jo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

}
