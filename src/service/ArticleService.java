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

import model.TimeLine;
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


    public Map getSortAndCount() {
        return dao.getColumAndCount(dao.SEARCH_SORT);
    }

    public List getAllArticle() {
        return dao.getAllArticle();
    }

    public List getAllSort() {
       return  dao.getAllSort();
    }

    public List getTimeLineList() {
        // 获取数据库中的所有文章
        List<Article> articles = dao.getAllArticle();

        // 用来存 时间轴文章 (一种比Article类更简单适用的对象)
        List<TimeLine> axis_list = new ArrayList();
        // Article->AxisArticle
        for (Article a : articles) {
            TimeLine at = ArticleUtils.getTimeLine(a);
            axis_list.add(at);
        }
        // 这里开始处理数据
            // 时间降序
            // year+文章的效果 把year封装成一个特殊的TimeLine对象 id=0 year = 大写的year
            // 文章日期
            // 然后全部存入 result中
            // 在jsp判断id==0
            // true: 特殊的TimeLine对象输出
            // false: 输出文章对象的
        TimeLine tmp = null;
        List result = new LinkedList();
        // 塞进去最新的一个年份
        if (!axis_list.isEmpty()) {
            tmp = new TimeLine();
            tmp.setId(0);
            tmp.setYear(axis_list.get(0).getYear());
            result.add(tmp);
            result.add(axis_list.get(0));
        }
        // 判断文章年份是不是不一样 不一样则塞一个year
        for (int i = 1; i < axis_list.size(); i++) {
            System.out.println(axis_list.get(i).getDay());
            int present_year = axis_list.get(i).getYear();
            int past_year = axis_list.get(i - 1).getYear();


            if (present_year < past_year) {
                tmp = new TimeLine();
                tmp.setId(0);
                tmp.setYear(present_year);
                result.add(tmp);
            }
            result.add(axis_list.get(i));
        }

        // 注意: 在list遍历里面动态修改了数组长度会出现内存溢出的情况
        return result;
    }
}
