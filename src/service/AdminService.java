package service;

import dao.ArticleDao;
import dao.TagDao;
import idao.IArticleDao;
import idao.ITagDao;
import model.Article;
import util.FailException;
import util.Form2Bean;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class AdminService {

    private IArticleDao iadao;
    private ITagDao itdao;
    private static AdminService instance;

    private AdminService() {
        iadao = ArticleDao.getInstance();
        itdao = TagDao.getInstance();
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
            //从前端界面获取文章
            article = Form2Bean.articleForm2Bean(request);
        } catch (FailException e) {
            e.printStackTrace();
        }
        if(article == null) {
            return null;
        }
        //将文章添加到数据库中
        Article a = iadao.addArticle(article);
        if(a == null) {
            return null;
        }

        //增加标签
        String str = request.getParameter("tags").trim();
        String[] tags = str.split(" ");
        for (String tag : tags){
            itdao.addTag(a.getId(),tag);
        }

        return a;
    }

    //通过id得到一篇文章
    public Article getArticle(String article_id) {
        List<Article> list = iadao.getArticleByColumn("article_id", article_id);
        System.out.println(list.size());
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    //通过id删除一篇文章
    public void delteArticle(String a_id2) {
        iadao.deleteArticle(a_id2);
        if (iadao.deleteArticle(a_id2)) {
            System.out.println("可以删除");
        }
    }

    //更新文章种类
    public void updateSort(String old_sort, String new_sort) {
        iadao.updateSort(old_sort, new_sort);
    }

    //删除这类所有文章
    public void deleteSort(String sort) {
        itdao.deleteTag(0, sort);
    }

    //更新标签
    public void updateTag(String old_tag, String new_tag) {
        itdao.updateTag(old_tag, new_tag);
    }

    //删除标签
    public void deleteTag(String tag) {
        itdao.deleteTag(0,tag);
    }


    public Article updateArticle(HttpServletRequest request) {
        String old_id = request.getParameter("id ");
        
        this.delteArticle(old_id);

        return this.addArticle(request);
    }

}
