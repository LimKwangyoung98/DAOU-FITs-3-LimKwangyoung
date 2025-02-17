package org.example.booksearchservletproject.service;

import org.example.booksearchservletproject.vo.BookVO;

import java.util.List;
import java.util.Map;

public interface BookService {
    public List<BookVO> searchBookByKeyword(Map<String, Object> map);
    public BookVO searchBookByISBN(String bisbn);
}