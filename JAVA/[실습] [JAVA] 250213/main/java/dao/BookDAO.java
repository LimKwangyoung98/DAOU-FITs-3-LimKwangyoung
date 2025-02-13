package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import vo.BookVO;

import java.util.HashMap;
import java.util.Map;

public class BookDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BookDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public ObservableList<BookVO> select(String keyword) {
        ObservableList<BookVO> list = FXCollections.observableArrayList();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list.addAll(sqlSession.selectList("practice.booksearch.select", keyword));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public void delete(String bisbn) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("practice.booksearch.delete", bisbn);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void insert(BookVO bookVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("practice.booksearch.insert", bookVO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void update(String bisbn, String column, String newValue) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("bisbn", bisbn);
            map.put("column", column);
            if (column.equals("가격")) {
                map.put("newValue", String.valueOf(Integer.parseInt(newValue)));
            } else {
                map.put("newValue", newValue);
            }

            sqlSession.update("practice.booksearch.update", map);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
