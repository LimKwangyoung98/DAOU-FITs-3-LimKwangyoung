package org.example.boardproject.vo;

import java.util.Date;

public class BoardVO {
    private String board_id;   // 게시글 ID (PK)
    private String title;      // 게시글 제목
    private String content;    // 게시글 내용
    private String member_id;  // 작성자의 회원 ID (FK)
    private String writer;     // 작성자 이름
    private String created_at;   // 작성일 (자동 생성)

    public BoardVO() {
    }

    public BoardVO(String board_id, String title, String content, String member_id, String writer, String created_at) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.member_id = member_id;
        this.writer = writer;
        this.created_at = created_at;
    }

    public String getBoard_id() {
        return board_id;
    }

    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}