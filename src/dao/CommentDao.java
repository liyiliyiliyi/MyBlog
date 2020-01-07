package dao;

import idao.ICommentDao;
import model.Comment;
import util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements ICommentDao {

    private static ICommentDao instance;

    public static ICommentDao getInstance() {
        if(instance == null) {
            try {
                instance = new CommentDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    /**
     * 向数据库中插入评论
     * @param comment
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        PreparedStatement pstatement;
        String sql = "INSERT  INTO comment VALUES(null,?,?,?,?,?,?)";
        int count = 0;
        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setInt(1, comment.getArticle_id());
            pstatement.setString(2, comment.getNickname());
            pstatement.setString(3, comment.getContent());
            pstatement.setString(4, comment.getTime());
            pstatement.setInt(5, comment.getStar());
            pstatement.setInt(6, comment.getDiss());
            count = pstatement.executeUpdate();

            //写入文章之后需要向article表中的comment中数据加一处理
            sql = "UPDATE article SET COMMENT = COMMENT+1  WHERE article_id=" + comment.getArticle_id();
            PreparedStatement ps2 = DBUtils.getStatement(sql);
            ps2.executeUpdate();

            DBUtils.Close(pstatement, null, null);
            DBUtils.Close(ps2, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(count > 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteComment(int comment_id) {
        return false;
    }


    public List getComment(int article_id) {
        String sql = "SELECT * FROM comment WHERE article_id=? ORDER BY TIME";
        List list = null;
        PreparedStatement pstatement;
        try {
            pstatement = DBUtils.getStatement(sql);
            pstatement.setInt(1, article_id);
            ResultSet rs = pstatement.executeQuery();
            Comment cm;
            list = new ArrayList();
            while(rs.next()) {
                cm = new Comment();
                cm.setId(rs.getInt("c_id"));
                cm.setArticle_id(rs.getInt("article_id"));
                cm.setNickname(rs.getString("nickname"));
                cm.setTime(rs.getString("time"));
                cm.setStar(rs.getInt("star"));
                cm.setContent(rs.getString("content"));
                cm.setDiss(rs.getInt("diss"));
                list.add(cm);
            }
            DBUtils.Close(pstatement, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //star+1,diss-1,返回star 或者 diss值
    @Override
    public int star_diss(int id, int star_or_diss) {
        String sql;

        int result = -1;

        if (star_or_diss == Comment.STAR) {
            sql = "update comment set star=star+1 where c_id=" + id;
        } else if (star_or_diss == Comment.DISS) {
            sql = "update comment set diss=diss+1 where c_id=" + id;
        } else {
            return -1;
        }

        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ps.executeUpdate();
            // DBUtils.Close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (star_or_diss == Comment.STAR) {
            sql = "SELECT star FROM comment WHERE c_id = " + id;
        } else if (star_or_diss == Comment.DISS) {
            sql = "SELECT diss FROM comment WHERE c_id = " + id;
        }

        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }

            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int starCounts(int id) {
        String sql = "SELECT star FROM comment WHERE c_id = " + id;
        int counts = 0;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                counts = rs.getInt(1);
            }

            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;

    }
    public int dissCounts(int id) {

        String sql = "SELECT diss FROM comment WHERE c_id = " + id;
        int counts = 0;
        try {
            PreparedStatement ps = DBUtils.getStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                counts = rs.getInt(1);
            }

            DBUtils.Close(ps, rs, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;

    }
}
