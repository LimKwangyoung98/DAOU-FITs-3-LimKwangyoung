package service;

import dao.BookDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import vo.BookVO;

public class BookInsertServiceOracleImpl implements BookInsertService {
    private SqlSessionFactory sqlSessionFactory;

    public BookInsertServiceOracleImpl() {
    }

    public BookInsertServiceOracleImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insertBook(BookVO bookVO) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        bookDAO.insert(bookVO);
    }
}
