package filter;

import service.ArticleService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest rq = (HttpServletRequest)req;
        HttpServletResponse rp = (HttpServletResponse)resp;

        // 点开文章自动增加浏览次数
        String id = rq.getParameter("id");
        ArticleService as = ArticleService.getInstance();
        as.addVisit(Integer.valueOf(id));
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
