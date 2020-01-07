package filter;

import service.ArticleService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "RankFilter")
public class RankFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        ArticleService as = ArticleService.getInstance();
        //显示阅读排行
        req.setAttribute("visit_rank",as.getVisitRank());

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
