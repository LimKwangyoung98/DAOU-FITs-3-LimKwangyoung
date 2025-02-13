package service;

import dao.BookDAO;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSessionFactory;
import vo.BookVO;

public class BookSearchServiceOracleImpl implements BookSearchService {
    private SqlSessionFactory sqlSessionFactory;

    public BookSearchServiceOracleImpl() {
    }

    public BookSearchServiceOracleImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public ObservableList<BookVO> searchBookByKeyword(String keyword) {
        BookDAO bookDAO = new BookDAO(sqlSessionFactory);

        ObservableList<BookVO> list = bookDAO.select(keyword);;
        return list;
    }
}
