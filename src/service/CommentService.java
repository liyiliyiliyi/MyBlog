package service;

import dao.CommentDao;
import idao.ICommentDao;

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
}
