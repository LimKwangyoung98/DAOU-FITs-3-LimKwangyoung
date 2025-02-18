package org.example.boardproject.service;

import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.BoardDAO;
import org.example.boardproject.dao.LoginDAO;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import java.util.List;
import java.util.Map;

public class BoardServiceOracleImpl implements BoardService {
    private SqlSessionFactory sqlSessionFactory;

    public BoardServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    @Override
    public List<BoardVO> getAll() {
        BoardDAO boardDAO = new BoardDAO(sqlSessionFactory);
        return boardDAO.select();
    }

    @Override
    public BoardVO getById(String boardId) {
        BoardDAO boardDAO = new BoardDAO(sqlSessionFactory);
        return boardDAO.selectById(boardId);
    }

    @Override
    public void createBoard(Map<String, Object> map) {
        BoardDAO boardDAO = new BoardDAO(sqlSessionFactory);
        boardDAO.create(map);
    }
}
