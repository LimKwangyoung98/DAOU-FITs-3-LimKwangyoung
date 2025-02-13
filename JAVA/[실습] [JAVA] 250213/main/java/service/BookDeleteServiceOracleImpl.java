package service;

import dao.BookDAO;
import org.apache.ibatis.session.SqlSessionFactory;

public class BookDeleteServiceOracleImpl implements BookDeleteService {
    private SqlSessionFactory sqlSessionFactory;

    public BookDeleteServiceOracleImpl() {
    }

    public BookDeleteServiceOracleImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void deleteBookByISBN(String bisbn) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        bookDAO.delete(bisbn);
    }
}
