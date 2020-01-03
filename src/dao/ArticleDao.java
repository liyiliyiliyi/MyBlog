package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.Article;

import idao.IArticleDao;
import util.DBUtils;

/*
 * 文章服务类
 *
 */
public class ArticleDao implements IArticleDao {

    //一个连接，一个实例
    private Connection conn;
    private static IArticleDao instance;


    private ArticleDao() {
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final IArticleDao getInstance() {
        if (instance == null) {
            try {
                instance = new ArticleDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;

    }


    //得到所有的种类
    @Override
    public List getAllSort() {

        String sql = " select distinct(sort) from article order by sort";
        List list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            DBUtils.Close(ps, rs,null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //通过分类来得到该分类的所有文章
    @Override
    public List<Article> getArticleByColumn(String column, String value) {
        List<Article> list = null;
        Article at = null;
        String sql = "select * from article where " + column + " = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            // bean实例化
            list = new ArrayList();
            while (rs.next()) {
                at = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("sort"),
                        rs.getString("time"), rs.getInt("star"), rs.getInt("comment"), rs.getInt("visit"),
                        rs.getString("content"));
                list.add(at);
            }
            // 关闭连接
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //增加访客人数
    @Override
    public void addVisit(int article_id) {
    }

    //获得上or下一篇文章
    @Override
    public Article getANearArticle(String time, int less_or_more) {
        return null;
    }


    @Override
    public Map getColumAndCount(String search_column) {
        return null;
    }
    @Override
    public Article addArticle(Article a) {
        return null;
    }

    //将文章加到delte表
    private boolean addArticle_delet(Article a) {
        return true;
    }


     //获取最新的文章
    private Article getLastArticle() {
        return null;
    }


    @Override
    public boolean deleteArticle(String id) {
        return true;
    }


    @Override
    public List getAllArticle() {
       return null;
    }


    @Override
    public List getVisitRank() {
        return null;
    }

    @Override
    public int getCount(String search_key) {
        return 1;
    }


    @Override
    public int star_article(int id) {
        return 1;
    }


    @Override
    public boolean updateSort(String old_sort, String new_sort) {
        return true;
    }


    @Override
    public boolean delelteSort(String sort) {

        return true;
    }

}


