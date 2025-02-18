package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BoardDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<BoardVO> select() {
        List<BoardVO> list = new ArrayList<BoardVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.board.select"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public BoardVO selectById(String boardId) {
        BoardVO board = new BoardVO();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            board = sqlSession.selectOne("servlet.board.selectById", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return board;
    }

    public void create(Map<String, Object> map) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            System.out.println(map);
            sqlSession.selectOne("servlet.board.create", map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
