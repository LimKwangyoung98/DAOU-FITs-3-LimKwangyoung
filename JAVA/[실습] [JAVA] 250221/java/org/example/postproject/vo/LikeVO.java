package org.example.postproject.vo;

import java.sql.Timestamp;

public class LikeVO {
    private int likeId;
    private int postId;
    private String userId;

    public LikeVO() {
    }

    public LikeVO(int likeId, int postId, String userId) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
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
}
