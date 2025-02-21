package org.example.postproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.vo.LikeVO;
import org.example.postproject.vo.PostVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDAO {
    private SqlSessionFactory sqlSessionFactory;

    public PostDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<PostVO> getAllPosts() {
        List<PostVO> list = new ArrayList<PostVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.post.getAllPosts"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public PostVO getPostByPostId(int postId) {
        PostVO post = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            post = sqlSession.selectOne("servlet.post.getPostByPostId", postId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return post;
    }

    public void createPost(Map<String, String> postMap) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("servlet.post.createPost", postMap);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deletePost(int postId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("servlet.post.deletePost", postId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updatePost(Map<String, Object> postMap) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.update("servlet.post.updatePost", postMap);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public boolean getIsLike(Map<String, Object> likeMap) {
        boolean isLike = false;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            LikeVO like = sqlSession.selectOne("servlet.like.getIsLike", likeMap);
            if (like != null) {
                isLike = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return isLike;
    }

    public Map<String, Object> like(Map<String, Object> likeMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("likeResponse", true);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("servlet.like.like", likeMap);
            sqlSession.update("servlet.like.increaseLikeCnt", likeMap);
            sqlSession.commit();

            PostVO post = sqlSession.selectOne("servlet.post.getPostByPostId", likeMap);
            map.put("likeCount", post.getLikeCount());
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return map;
    }

    public Map<String, Object> unlike(Map<String, Object> likeMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("likeResponse", false);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("servlet.like.unlike", likeMap);
            sqlSession.update("servlet.like.decreaseLikeCnt", likeMap);
            sqlSession.commit();

            PostVO post = sqlSession.selectOne("servlet.post.getPostByPostId", likeMap);
            map.put("likeCount", post.getLikeCount());
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return map;
    }

    public List<PostVO> getPostsByTitle(String keyword) {
        List<PostVO> list = new ArrayList<PostVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.post.getPostsByTitle", keyword));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<PostVO> getPostsByContent(String keyword) {
        List<PostVO> list = new ArrayList<PostVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.post.getPostsByContent", keyword));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<PostVO> getPostsByTitleContent(String keyword) {
        List<PostVO> list = new ArrayList<PostVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.post.getPostsByTitleContent", keyword));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }
}
