package service;

import dao.BookDAO;
import org.apache.ibatis.session.SqlSessionFactory;

public class BookUpdateServiceOracleImpl implements BookUpdateService {
    private SqlSessionFactory sqlSessionFactory;

    public BookUpdateServiceOracleImpl() {
    }

    public BookUpdateServiceOracleImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void updateBook(String bisbn, String column, String newValue) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        bookDAO.update(bisbn, column, newValue);
    }
}
