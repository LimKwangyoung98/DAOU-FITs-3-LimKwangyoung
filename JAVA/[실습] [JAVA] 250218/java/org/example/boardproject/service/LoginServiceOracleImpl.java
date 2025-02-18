package org.example.boardproject.service;

import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.LoginDAO;
import org.example.boardproject.vo.MemberVO;

import java.util.Map;

public class LoginServiceOracleImpl implements LoginService {
    private SqlSessionFactory sqlSessionFactory;

    public LoginServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    @Override
    public MemberVO login(Map<String, String> map) {
        LoginDAO loginDAO = new LoginDAO(sqlSessionFactory);
        return loginDAO.login(map);
    }
}
