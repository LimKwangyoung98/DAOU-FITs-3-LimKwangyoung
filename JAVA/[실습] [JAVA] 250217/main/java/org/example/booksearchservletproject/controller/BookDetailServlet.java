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

@WebServlet(value = "/bookDetail")
public class BookDetailServlet extends HttpServlet {
    public BookDetailServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bisbn = req.getParameter("isbn");

        BookService searchService = new BookServiceOracleImpl();
        BookVO book = searchService.searchBookByISBN(bisbn);

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head></head>");
        out.println("<h1>도서 상세 정보</h1>");
        out.println("<h3>도서 ISBN: " + book.getBisbn() + "</h3>");
        out.println("<h3>도서 제목: " + book.getBtitle() + "</h3>");
        out.println("<h3>도서 날짜: " + book.getBdate() + "</h3>");
        out.println("<h3>도서 페이지: " + book.getBpage() + "</h3>");
        out.println("<h3>도서 가격: " + book.getBprice() + "</h3>");
        out.println("<h3>도서 저자: " + book.getBauthor() + "</h3>");
        out.println("<h3>도서 번역가: " + book.getBtranslator() + "</h3>");
        out.println("<h3>도서 출판사:  " + book.getBpublisher() + "</h3>");
        out.println("</body></html>");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }
}

