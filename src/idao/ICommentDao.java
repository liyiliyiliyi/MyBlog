package idao;

import model.Comment;

import java.util.List;

public interface ICommentDao {

    /**
     *增加新的评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment);

    /**
     * 删除已有的评论
     * @param comment_id
     * @return
     */
    boolean deleteComment(int comment_id);

    /**
     * 获取已存在的评论
     * @param article_id
     * @return
     */
    List getComment(int article_id);

    /**
     *  点赞或者鄙视
     * @param id
     * @param star_or_diss
     * @return
     */
    int star_diss(int id, int star_or_diss);

    int starCounts(int id);

    int dissCounts(int id);
}
