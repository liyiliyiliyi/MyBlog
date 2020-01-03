package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    /*
     * (non-Javadoc)
     *增加访客人数
     * @see blog.daoImpl.ArticleDao#addVisit(int)
     */
    @Override
    public void addVisit(int article_id) {

        String sql = "update article set visit = visit+1 where id = " + article_id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getANearArticle(java.lang.String, int)
     */
    @Override
    public Article getANearArticle(String time, int less_or_more) {

        Article article = null;
        String sql = null;
        if (less_or_more == this.LESS) {
            sql = " SELECT  * FROM article WHERE TIME< '" + time + "'  ORDER BY TIME DESC ";
        } else if (less_or_more == this.MORE) {
            sql = " SELECT  * FROM article WHERE TIME > '" + time + "'  ORDER BY TIME ";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
            }
            DBUtils.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getColumAndCount(java.lang.String)
     */
    @Override
    public Map getColumAndCount(String search_column) {

        String sql = " select " + search_column + " ,count(" + search_column + ") as counts  from article  group by "
                + search_column;
        Map map = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            map = new HashMap();
            while (rs.next()) {
                map.put(rs.getString(search_column), rs.getInt("counts"));
            }
            DBUtils.dispose();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getAllSort()
     */
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

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#addArticle(blog.model.Article)
     */
    @Override
    public Article addArticle(Article a) {

        String sql = "insert into article values(null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());
            ps.setString(3, a.getSort());
            ps.setString(4, a.getTime());
            ps.setInt(5, a.getStar());
            ps.setInt(6, a.getComment());
            ps.setInt(7, a.getVisit());
            ps.setString(8, a.getContent());
            result = ps.executeUpdate();
            DBUtils.Close(ps,null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getLastArticle();

    }

    /**
     * 将文章加到delte表
     *
     * @param a
     * @return
     */
    private boolean addArticle_delet(Article a) {

        String sql = "insert into article_delet values(null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());
            ps.setString(3, a.getSort());
            ps.setString(4, a.getTime());
            ps.setInt(5, a.getStar());
            ps.setInt(6, a.getComment());
            ps.setInt(7, a.getVisit());
            ps.setString(8, a.getContent());
            result = ps.executeUpdate();
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }

    /**
     * 获取最新的文章
     *
     * @return
     */
    private Article getLastArticle() {

        String sql = "SELECT * FROM article ORDER BY TIME DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Article article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
                DBUtils.Close(ps, rs, null);
                return article;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#deleteArticle(java.lang.String)
     */
    @Override
    public boolean deleteArticle(String id) {

        String sql = "delete from article where id=?";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            result = ps.executeUpdate();
            // 关闭连接
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getAllArticle()
     */
    @Override
    public List getAllArticle() {
        List<Article> list = new ArrayList();

        String sql = "select * from article";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // bean实例化
            while (rs.next()) {
                Article article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
                list.add(article);
            }
            // 关闭连接
            DBUtils.Close(ps, rs, null);
            // 排序 article compareTo();
            Collections.sort(list);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getVisitRank()
     */
    @Override
    public List getVisitRank() {
        List<Article> list = new ArrayList();

        String sql = "SELECT * FROM article ORDER BY visit DESC";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // bean实例化
            while (rs.next()) {
                Article article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
                list.add(article);
            }
            // 关闭连接
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getArticleByColumn(java.lang.String,
     * java.lang.String)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#getCount(java.lang.String)
     */
    @Override
    public int getCount(String search_key) {

        String sql;
        if (search_key.equals(SEARCH_ARTICLE)) {
            sql = "SELECT COUNT(id) FROM article";
        } else {// SEARCH_SORT
            sql = "SELECT COUNT(DISTINCT(sort)) FROM article";
        }
        int result = 0;
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#star_article(int)
     */
    @Override
    public int star_article(int id) {

        String sql = "update article set star=star+1 where id=" + id;
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            // DBUtils.Close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "select star from article where id=" + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#updateSort(java.lang.String, java.lang.String)
     */
    @Override
    public boolean updateSort(String old_sort, String new_sort) {

        String sql = "UPDATE article SET sort=? WHERE sort=?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, new_sort);
            ps.setString(2, old_sort);
            ps.executeUpdate();
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see blog.daoImpl.ArticleDao#delelteSort(java.lang.String)
     */
    @Override
    public boolean delelteSort(String sort) {
        // 找到这个分类下的文章 移动到t_article_delet
        String sql = "SELECT * FROM article where sort = ?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sort);
            ResultSet rs = ps.executeQuery();

            List<Article> list = new ArrayList();
            while (rs.next()) {
                Article article = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
                list.add(article);
            }
            System.out.println(list.size());
            if (list.size() > 0) {
                for (Article a : list) {
                    this.addArticle_delet(a);
                }
            }

            sql = "delete from article where sort =?";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setString(1, sort);
            result = ps2.executeUpdate();
            System.out.println(result);
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result > 0;
    }

}

// 2017年9月19日21:57:38 大吉大利 今晚吃鸡
// bug无敌多 fuck
// 2017年9月26日15:10:47 这个类有毒
