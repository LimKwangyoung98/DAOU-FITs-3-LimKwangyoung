package org.example.booksearchservletproject.service;

import org.example.booksearchservletproject.dao.BookDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchservletproject.mybatis.MyBatisSessionFactory;
import org.example.booksearchservletproject.vo.BookVO;

import java.util.List;
import java.util.Map;

public class BookServiceOracleImpl implements BookService {
    private SqlSessionFactory sqlSessionFactory;

    public BookServiceOracleImpl() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    @Override
    public List<BookVO> searchBookByKeyword(Map<String, Object> map) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        return bookDAO.selectByKeyword(map);
    }

    @Override
    public BookVO searchBookByISBN(String bisbn) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        return bookDAO.selectByISBN(bisbn);
    }
}
