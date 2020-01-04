package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import idao.IArticleDao;
import dao.ArticleDao;
import model.Article;

import util.ArticleUtils;
import util.DBUtils;
import util.StringUtils;

public class ArticleService {

    private IArticleDao dao;

    private static ArticleService instance;

    private ArticleService() {
        dao = ArticleDao.getInstance();
    }

    /**
     * 获取实例 ArticleDao
     *
     * @return
     */
    public static final ArticleService getInstance() {
        if (instance == null) {
            try {
                instance = new ArticleService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    /**
     * 获取分类和它的文章
     *
     * @return
     */
    public Map getSortAndAirticle(String sort_name) {

        Map map = new HashMap();
        List<Article> articleBySort = null;

        // 获取所有分类
        if (sort_name.equals("all") || StringUtils.isEmpty(sort_name)) {
            List<String> sorts = dao.getAllSort();

            for (int i = 0; i < sorts.size(); i++) {
                String sort = sorts.get(i);
                articleBySort = dao.getArticleByColumn("sort", sort);
                ArticleUtils.cutTime(articleBySort);
                //设置key : String 和 value: map
                map.put(sort, articleBySort);
            }
        } else {
            // 获取单个分类
            articleBySort = dao.getArticleByColumn("sort", sort_name);
            ArticleUtils.cutTime(articleBySort);
            map.put(sort_name, articleBySort);
        }
        return map;
    }

    /**
     * 通过列属性获取对应的文章
     * @param column //id
     * @param value
     * @return
     */
    public List<Article> getArticle (String column, String value) {

        return dao.getArticleByColumn(column, value);
    }

    /**
     *  获取上一篇文章---参数LESS为1
     * @param time
     * @return
     */
    public Article getPreviousArticle(String time){
        return dao.getANearArticle(time, dao.LESS);
    }

    /**
     * 获取下一篇文章---参数MORE为1
     * @param time
     * @return
     */
    public Article getNextArticle(String time) {
        return dao.getANearArticle(time, dao.MORE);
    }

    public Map getColumAndCount () {

        return null;
    }




}
