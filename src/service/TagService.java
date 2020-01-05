package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import dao.TagDao;
import idao.ITagDao;
import model.Article;
import model.Tag;
import util.ArticleUtils;
import util.DBUtils;
import util.StringUtils;

public class TagService {

    private ITagDao dao;

    private static TagService instance;

    private TagService() {
        dao = TagDao.getInstance();
    }

    //getAllTag()得到所有的便签
    public List<Tag> getAllTag() {
        return dao.getAllTag();
    }

    public static final TagService getInstance() {
        if (instance == null) {
            try {
                instance = new TagService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }



    //获取标签和它所标记的文章
    public Map getTagAndArticle(String tag_name) {
        ArticleService as = ArticleService.getInstance();
        Map map = new HashMap();

        List<Tag> tag_list;
        if (tag_name.equals("all") || StringUtils.isEmpty(tag_name)) {
            // 获取所有不重复的标签
            tag_list = dao.getAllTag();
        } else {
            // 获取这个标签
            tag_list = dao.getTagByColumn("tag", tag_name);
        }

        // 有这个标签的文章
        List<Article> article_list;
        //有这个标签的所有同类标签
        List<Tag> articleID_list = null;

        for (Tag mark : tag_list) {
            //该标签的所有文章
                //先通过tag_list得到这类标签所有的article_id;
                //在通过article得到所有这类标签的article
            article_list = new ArrayList<Article>();
            articleID_list = dao.getTagByColumn("tag",mark.getTag());
            for (Tag tag : articleID_list) {
                article_list.add( as.getArticle(tag.getArticle_id()));
            }

            // 返回标签的内容+标签标记的所有文章集合
            map.put(mark.getTag(), article_list);

        }
        return map;
    }

    /**
     * 根据文章id获得对应的标签
     * @param id
     * @return
     */
    public List getTagById(String id){
        return dao.getTagByColumn("article_id", id);
    }
}
