package org.example.postproject.service;

import org.example.postproject.vo.PostVO;
import org.example.postproject.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface PostService {
    public List<PostVO> getAllPosts();
    public PostVO getPostByPostId(int postId);
    public void createPost(String userId, String title, String content);
    public void deletePost(int postId);
    public void updatePost(String title, String content, int postId);
    public boolean getIsLike(String userId, int postId);
    public Map<String, Object> toggleLike(int postId, String userId, boolean likeRequest);
    public List<PostVO> getPostsByKeyword(String searchType, String keyword);
}
