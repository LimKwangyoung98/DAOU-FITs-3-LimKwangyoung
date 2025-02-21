package org.example.postproject.service;

import org.example.postproject.vo.UserVO;

public interface LoginService {
    public UserVO login(String userId, String password);
}
