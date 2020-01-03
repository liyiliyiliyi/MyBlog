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


    @Override
    public List getAllTag() {
        return null;
    }


    @Override
    public boolean updateTag(String old_tag, String new_tag) {

       return true;
    }

   //通过column关键字找一类标签
    @Override
    public List getTagByColumn(String column, String value) {

        String sql = "select * from tag where " + column + "=?";
        List list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, value);
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
