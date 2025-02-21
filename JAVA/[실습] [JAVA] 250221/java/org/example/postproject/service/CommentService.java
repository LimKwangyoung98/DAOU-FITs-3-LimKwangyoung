package org.example.postproject.service;

import org.example.postproject.vo.CommentVO;

import java.util.List;

public interface CommentService {
    public List<CommentVO> getCommentsByPostId(int postId);
    public void createComment(int postId, String userId, String content);
    public void deleteComment(int commentId);
}
