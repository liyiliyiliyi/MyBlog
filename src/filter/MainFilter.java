package filter;

import idao.IArticleDao;
import service.ArticleService;
import service.TagService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MainFilter")
public class MainFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest rq = (HttpServletRequest)req;
        HttpServletResponse rp = (HttpServletResponse)resp;

        // 直接点main.jsp需要初始化界面元素---转到VisitMainServlet中解决

        ArticleService as = ArticleService.getInstance();
        TagService ts = TagService.getInstance();
        // 初始化侧边栏 日志、分类、标签的个数
        rq.setAttribute("article_number",as.getCount(IArticleDao.SEARCH_ARTICLE));
        rq.setAttribute("sort_number", as.getCount(IArticleDao.SEARCH_SORT));
        rq.setAttribute("tags_number",ts.getTagCounts());



        // 初始化文章列表
        rq.setAttribute("article_list",as.getArticle());



        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
