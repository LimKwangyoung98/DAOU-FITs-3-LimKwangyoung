package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.vo.MemberVO;

import java.util.List;
import java.util.Map;

public class LoginDAO {
    private SqlSessionFactory sqlSessionFactory;

    public LoginDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public MemberVO login(Map<String, String> map) {
        MemberVO member = new MemberVO();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            member = sqlSession.selectOne("servlet.member.login", map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return member;
    }
}
