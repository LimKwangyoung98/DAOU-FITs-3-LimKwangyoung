package service;

import javafx.collections.ObservableList;
import vo.BookVO;

public interface BookSearchService {
    public ObservableList<BookVO> searchBookByKeyword(String keyword);
}
