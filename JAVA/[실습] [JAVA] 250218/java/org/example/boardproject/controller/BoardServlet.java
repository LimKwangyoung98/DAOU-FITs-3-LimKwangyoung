package org.example.boardproject.controller;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.service.BoardServiceOracleImpl;
import org.example.boardproject.service.LoginService;
import org.example.boardproject.service.LoginServiceOracleImpl;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/board")
public class BoardServlet extends HttpServlet {
    public BoardServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String board_id = req.getParameter("id");

        BoardService boardService = new BoardServiceOracleImpl();
        BoardVO board = boardService.getById(board_id);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("boardDetail.jsp");
        req.setAttribute("boardDetail", board);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }
}