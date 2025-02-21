package org.example.postproject.vo;

import java.sql.Timestamp;

public class CommentVO {
    private int commentId;
    private int postId;
    private String userId;
    private String name;
    private String content;
    private Timestamp createdAt;

    public CommentVO() {
    }

    public CommentVO(int commentId, int postId, String userId, String name, String content, Timestamp createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
