package org.example.postproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.vo.CommentVO;
import org.example.postproject.vo.LikeVO;
import org.example.postproject.vo.PostVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAO {
    private SqlSessionFactory sqlSessionFactory;

    public CommentDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<CommentVO> getCommentsByPostId(int postsId) {
        List<CommentVO> list = new ArrayList<CommentVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.update("servlet.comment.increaseViewCountByPostId", postsId);
            sqlSession.commit();

            list.addAll(sqlSession.selectList("servlet.comment.getCommentsByPostId", postsId));
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public void createComment(Map<String, Object> commentMap) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("servlet.comment.createComment", commentMap);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteComment(int commentId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("servlet.comment.deleteComment", commentId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
