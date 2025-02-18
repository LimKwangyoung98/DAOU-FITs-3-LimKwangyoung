package org.example.boardproject.service;

import org.example.boardproject.vo.MemberVO;

import java.util.Map;

public interface LoginService {
    public MemberVO login(Map<String, String> map);
}
