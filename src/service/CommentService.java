package service;

import dao.CommentDao;
import idao.ICommentDao;
import model.Comment;

import java.util.List;

public class CommentService {
    private ICommentDao idao;
    private static CommentService instance;

    /**
     * 获得CommentDao实例
     */
    private CommentService() {
        idao = CommentDao.getInstance();
    }

    public static final CommentService getInstance() {
        if (instance == null) {
            try {
                instance = new CommentService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public List loadComment(int article_id){

        return idao.getComment(article_id);
    }

    public boolean addComment(Comment comment) {

        return idao.addComment(comment);
    }

    public boolean deleteComment(int id) {
        return idao.deleteComment(id);
    }

    //点赞
    public  int starComment(int id, int flag) {
        return idao.star_diss(id, Comment.STAR);
    }
    //diss
    public  int dissComment(int id, int flag) {
       return  idao.star_diss(id, Comment.DISS);
    }
    //返回点赞数
    public int starCounts(int id) {
        return idao.starCounts(id);
    }
    //返回diss数
    public int dissCounts(int id) {
        return idao.starCounts(id);
    }


}
