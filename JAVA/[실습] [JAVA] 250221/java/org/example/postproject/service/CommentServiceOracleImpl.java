package org.example.postproject.service;

import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.dao.CommentDAO;
import org.example.postproject.dao.LoginDAO;
import org.example.postproject.dao.PostDAO;
import org.example.postproject.vo.CommentVO;
import org.example.postproject.vo.UserVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServiceOracleImpl implements CommentService {
    private SqlSessionFactory sqlSessionFactory;

    public CommentServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }


    @Override
    public List<CommentVO> getCommentsByPostId(int postId) {
        CommentDAO commentDAO = new CommentDAO(sqlSessionFactory);
        return commentDAO.getCommentsByPostId(postId);
    }

    @Override
    public void createComment(int postId, String userId, String content) {
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("postId", postId);
        commentMap.put("userId", userId);
        commentMap.put("content", content);

        CommentDAO commentDAO = new CommentDAO(sqlSessionFactory);
        commentDAO.createComment(commentMap);
    }

    @Override
    public void deleteComment(int commentId) {
        CommentDAO commentDAO = new CommentDAO(sqlSessionFactory);
        commentDAO.deleteComment(commentId);
    }
}
