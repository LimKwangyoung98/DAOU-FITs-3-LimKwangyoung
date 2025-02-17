package org.example.booksearchservletproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchservletproject.vo.BookVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BookDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<BookVO> selectByKeyword(Map map) {
        List<BookVO> list = new ArrayList<BookVO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("servlet.booksearch.selectByKeyword", map));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public BookVO selectByISBN(String bisbn) {
        BookVO book = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            book = sqlSession.selectOne("servlet.booksearch.selectByISBN", bisbn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return book;
    }
}
