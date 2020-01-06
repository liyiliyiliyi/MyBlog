package ajax;

import net.sf.json.JSONObject;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CMDeleteServlet")
public class CMDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得要删除评论的ip
        String id = request.getParameter("id");

        //返回的数据
        JSONObject jo = new JSONObject();
        CommentService cs = CommentService.getInstance();
        boolean f = cs.deleteComment(Integer.parseInt(id));
        if(f) {
            jo.put("msg", "success");
        }else {
            jo.put("msg", "fail");
        }
        // 写回ajax
        response.getWriter().println(jo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
