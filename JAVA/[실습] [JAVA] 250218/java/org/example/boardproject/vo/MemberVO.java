package org.example.boardproject.vo;

public class MemberVO {
    private String member_id;
    private String password;
    private String name;

    public MemberVO() {
    }

    public MemberVO(String member_id, String password, String name) {
        this.member_id = member_id;
        this.password = password;
        this.name = name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}