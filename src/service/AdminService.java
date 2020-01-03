package service;

import dao.ArticleDao;
import idao.IArticleDao;
import model.Article;
import util.FailException;
import util.Form2Bean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class AdminService {

    private IArticleDao iadao;
   // private ITagDao itdao;
    private static AdminService instance;

    private AdminService() {
        iadao = ArticleDao.getInstance();
        //itdao = TagDao.getInstance();
    }

    /**
     * 获取AdminService的实例
     * @return
     */
    public static final AdminService getInstance() {
        if(instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public Article addArticle(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Article article = null;
        try {
            article = Form2Bean.articleForm2Bean(request);
        } catch (FailException e) {
            e.printStackTrace();
        }
        if(article == null) {
            return null;
        }
        boolean f = iadao.addArticle(article);
        if(f == false) {
            return null;
        }
        //增加标签
        String str = request.getParameter("tags").trim();
        String[] tags = str.split(" ");
        for (String tag : tags){
         //   itdao.addTag(a.getId(),tag);
        }
        return article;
    }
}
