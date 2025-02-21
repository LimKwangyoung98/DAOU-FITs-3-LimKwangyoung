package org.example.postproject.service;

import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.postproject.dao.LoginDAO;
import org.example.postproject.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

public class LoginServiceOracleImpl implements LoginService {
    private SqlSessionFactory sqlSessionFactory;

    public LoginServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    @Override
    public UserVO login(String userId, String password) {
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("userId", userId);
        loginMap.put("password", password);

        LoginDAO loginDAO = new LoginDAO(sqlSessionFactory);
        return loginDAO.login(loginMap);
    }
}
