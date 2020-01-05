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

    //通过来得到拥有column属性的所有文章
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
            System.out.println(sql);
            // bean实例化
            list = new ArrayList();
            while (rs.next()) {
                at = new Article(rs.getInt("article_id"), rs.getString("title"), rs.getString("author"), rs.getString("sort"),
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
        Article article = null;
        String sql = null;
        PreparedStatement ps;
        if(less_or_more == this.LESS) {
            sql = " SELECT  * FROM article WHERE TIME< '" + time + "'  ORDER BY TIME DESC ";
        }else if(less_or_more == this.MORE) {
            sql = " SELECT  * FROM article WHERE TIME > '" + time + "'  ORDER BY TIME ";
        }
        try {
            ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article(rs.getInt("article_id"),rs.getString("title"),
                        rs.getString("author"), rs.getString("sort"),
                        rs.getString("time"), rs.getInt("star"),
                        rs.getInt("comment"), rs.getInt("visit"),
                        rs.getString("content"));
            }
            DBUtils.Close(ps, rs, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }


    //map
        //key:种类
        //value:这个种类文章的个数
    @Override
    public Map getColumAndCount(String search_column) {

        String sql = " select " + search_column + " ,count(" + search_column + ") as counts  from article  group by "
                + search_column;
        Map map = null;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            map = new HashMap();
            while (rs.next()) {
                map.put(rs.getString(search_column), rs.getInt("counts"));
            }
            DBUtils.Close(ps, rs,null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
    @Override
    public Article addArticle(Article a) {
        PreparedStatement pstatement;
        String sql = "insert into article values(null,?,?,?,?,?,?,?,?)";
        int count = 0;
        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setString(1, a.getTitle());
            pstatement.setString(2, a.getAuthor());
            pstatement.setString(3, a.getSort());
            pstatement.setString(4, a.getTime());
            pstatement.setInt(5,a.getStar());
            pstatement.setInt(6, a.getComment());
            pstatement.setInt(7, a.getVisit());
            pstatement.setString(8, a.getContent());
            count = pstatement.executeUpdate();
            DBUtils.Close(pstatement, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getLastArticle();
        /*
        statement = DBUtils.getStatement();
        String sql = "insert into article(title,author,sort,time,start,comment,visit,content) "
                + "values ('"+a.getTitle()+"','"+a.getAuthor()
                +"','"+a.getSort()+"','"+a.getTime()+"','"+a.getStar()
                +"','"+a.getComment()+"','"+a.getVisit()+"','"+a.getContent()+"')";
        try {
            int count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }

    //将文章加到delte表
    private boolean addArticle_delet(Article a) {
        return true;
    }


     //获取最新的文章
    private Article getLastArticle() {
        String sql = "SELECT * FROM article ORDER BY TIME DESC LIMIT 1";
        PreparedStatement  pstatement;
        try {
            pstatement = DBUtils.getStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            if(rs.next()) {
                Article article = new Article(rs.getInt("article_id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("sort"), rs.getString("time"), rs.getInt("star"), rs.getInt("comment"),
                        rs.getInt("visit"), rs.getString("content"));
                DBUtils.Close(pstatement, rs, null);
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //通过id删除一篇文章
    @Override
    public boolean deleteArticle(String id) {
        String sql = "delete from article where " + id + "=article_id";
        PreparedStatement ps;
        int result = 0;
        try {
            ps = DBUtils.getStatement(sql);
            result = ps.executeUpdate();
            // 关闭连接
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;

    }


    @Override
    public List getAllArticle() {
        List<Article> list = new ArrayList();

        String sql = "select * from article";
        PreparedStatement ps;
        try {
            ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            // bean实例化
            while (rs.next()) {
                Article article = new Article(rs.getInt("article_id"), rs.getString("title"), rs.getString("author"),
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

        String sql = "UPDATE article SET sort=? WHERE sort=?";
        int result = 0;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ps.setString(1, new_sort);
            ps.setString(2, old_sort);
            ps.executeUpdate();
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }


    //删除这类文章，并且把这类文章放入删除表保存
    @Override
    public boolean delelteSort(String sort) {

        // 找到这个分类下的文章 移动到article_delet
        String sql = "SELECT * FROM article where sort = ?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sort);
            ResultSet rs = ps.executeQuery();

            List<Article> list = new ArrayList();
            while (rs.next()) {
                Article article = new Article(rs.getInt("article_id"), rs.getString("title"), rs.getString("author"),
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
            PreparedStatement ps2 = DBUtils.getStatement(sql);
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
