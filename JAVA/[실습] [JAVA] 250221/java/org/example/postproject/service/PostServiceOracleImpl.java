package org.example.postproject.service;

import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.dao.PostDAO;
import org.example.postproject.vo.PostVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostServiceOracleImpl implements PostService {
    private SqlSessionFactory sqlSessionFactory;

    public PostServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    @Override
    public List<PostVO> getAllPosts() {
        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        return postDAO.getAllPosts();
    }

    @Override
    public PostVO getPostByPostId(int postId) {
        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        return postDAO.getPostByPostId(postId);
    }

    @Override
    public void createPost(String userId, String title, String content) {
        Map<String, String> postMap = new HashMap<String, String>();
        postMap.put("userId", userId);
        postMap.put("title", title);
        postMap.put("content", content);

        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        postDAO.createPost(postMap);
    }

    @Override
    public void deletePost(int postId) {
        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        postDAO.deletePost(postId);
    }

    @Override
    public void updatePost(String title, String content, int postId) {
        Map<String, Object> postMap = new HashMap<String, Object>();
        postMap.put("title", title);
        postMap.put("content", content);
        postMap.put("postId", postId);

        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        postDAO.updatePost(postMap);
    }

    @Override
    public boolean getIsLike(String userId, int postId) {
        Map<String, Object> likeMap = new HashMap<String, Object>();
        likeMap.put("userId", userId);
        likeMap.put("postId", postId);

        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        return postDAO.getIsLike(likeMap);
    }

    @Override
    public Map<String, Object> toggleLike(int postId, String userId, boolean likeRequest) {
        Map<String, Object> likeMap = new HashMap<String, Object>();
        likeMap.put("postId", postId);
        likeMap.put("userId", userId);

        PostDAO postDAO = new PostDAO(sqlSessionFactory);
        if (likeRequest) {
            return postDAO.like(likeMap);
        } else {
            return postDAO.unlike(likeMap);
        }
    }

    @Override
    public List<PostVO> getPostsByKeyword(String searchType, String keyword) {
        PostDAO postDAO = new PostDAO(sqlSessionFactory);

        if (searchType.equals("제목")) {
            return postDAO.getPostsByTitle(keyword);
        } else if (searchType.equals("내용")) {
            return postDAO.getPostsByContent(keyword);
        } else {
            return postDAO.getPostsByTitleContent(keyword);
        }
    }
}
