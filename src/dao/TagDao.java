package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ArticleDao;
import idao.IArticleDao;
import idao.ITagDao;
import model.Article;
import model.Tag;
import util.DBUtils;

/**
 * 文章的标签类
 */
public class TagDao implements ITagDao {

    private Connection conn;

    private static ITagDao instance;

    private TagDao() { }

    public static final ITagDao getInstance() {
        if (instance == null) {
            try {
                instance = new TagDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public boolean addTag(int id, String tag) {

        String sql = "insert into tag values(null,?,?)";
        int result = 0;
        PreparedStatement pstatement;

        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setInt(1,id);
            pstatement.setString(2,tag);
            result = pstatement.executeUpdate();
            DBUtils.Close(pstatement,null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result <= 0) {
            return false;
        }
        return true;

    }

    //根据id或者tag删除一个或多个标签
    @Override
    public boolean deleteTag(int id, String tag) {
        String sql = "delete from tag where t_id=? or tag=?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tag);
            result = ps.executeUpdate();
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }

    // 获取所有不重复的标签
    @Override
    public List getAllTag() {
        List list = null;
        String sql = "select distinct(tag) from tag";
        PreparedStatement ps;
        list = new ArrayList();
        try {
            ps = DBUtils.getStatement(sql);
            ResultSet set = ps.executeQuery();
            Tag tag;
            while (set.next()) {
                tag = new Tag();
                tag.setTag(set.getString(1));
                list.add(tag);
            }
            DBUtils.Close(ps, set, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;


    }


    @Override
    public boolean updateTag(String old_tag, String new_tag) {
        String sql = "update tag set tag=? where tag=?";
        int result = 0;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ps.setString(1, new_tag);
            ps.setString(2, old_tag);
            result = ps.executeUpdate();
            DBUtils.Close(ps, null, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }


    @Override
    public List getTagByColumn(String column, String value) {
        //column == tag,先这样，还没找到更好的sql语句，用的时候注意sql语句
        String sql = "select * from tag where tag = '" + value + "'";
        List list = null;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("t_id"));
                tag.setArticle_id(rs.getInt("article_id"));
                tag.setTag(rs.getString("tag"));
                System.out.println(rs.getInt("article_id"));
                list.add(tag);
            }
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



}
