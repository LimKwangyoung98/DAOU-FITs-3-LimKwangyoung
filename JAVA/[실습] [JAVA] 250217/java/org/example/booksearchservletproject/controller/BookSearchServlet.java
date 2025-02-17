package org.example.booksearchservletproject.controller;

import org.example.booksearchservletproject.service.BookService;
import org.example.booksearchservletproject.service.BookServiceOracleImpl;
import org.example.booksearchservletproject.vo.BookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/bookSearch")
public class BookSearchServlet extends HttpServlet {
    public BookSearchServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 입력 처리
        req.setCharacterEncoding("utf-8");

        String keyword = req.getParameter("keyword");
        Integer price = Integer.parseInt(req.getParameter("price"));

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("price", price);

        // 로직 처리
        BookService searchService = new BookServiceOracleImpl();
        List<BookVO> list = searchService.searchBookByKeyword(map);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("<h1>검색결과입니다.</h1>");
        out.println("<h3>검색 키워드 : " + keyword + "</h3>");
        out.println("<h3>검색 가격 : " + price + "</h3>");

        for (BookVO book : list) {
            out.println("<li><a href=bookDetail?isbn=" + book.getBisbn() + ">"
                    + book.getBtitle() + " , " + book.getBprice()
                    +"</a></li>");
        }

        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
    }
}

