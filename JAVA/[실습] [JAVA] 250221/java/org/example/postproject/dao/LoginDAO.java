package org.example.postproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.vo.UserVO;

import java.util.Map;

public class LoginDAO {
    private SqlSessionFactory sqlSessionFactory;

    public LoginDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public UserVO login(Map<String, String> loginMap) {
        UserVO user = new UserVO();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            user = sqlSession.selectOne("servlet.user.login", loginMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }
}
