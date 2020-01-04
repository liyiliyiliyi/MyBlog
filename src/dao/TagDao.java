package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ArticleDao;
import idao.ITagDao;
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
        return true;
    }

    @Override
    public boolean deleteTag(int id, String tag) {
        return true;
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
                //为了getTagByColumn函数执行，只保留String一个字母
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

       return true;
    }

   //通过column关键字找一类所有标签
    @Override
    public List getTagByColumn(String column, String value) {
        //column == tag,先这样，还没找到更好的sql语句
        String sql = "select * from tag where " + value + "=tag";
        List list = null;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
