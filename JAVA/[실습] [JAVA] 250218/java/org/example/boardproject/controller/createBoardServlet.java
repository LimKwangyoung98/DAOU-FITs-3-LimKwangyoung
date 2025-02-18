package org.example.boardproject.controller;

import org.example.boardproject.service.BoardService;
import org.example.boardproject.service.BoardServiceOracleImpl;
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

@WebServlet(value = "/createBoard")
public class createBoardServlet extends HttpServlet {
    public createBoardServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("member");

        req.setAttribute("member", member);
        RequestDispatcher dispatcher = req.getRequestDispatcher("createBoard.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String member_id = req.getParameter("member_id");
        String writer = req.getParameter("writer");

        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("member_id", member_id);
        map.put("writer", writer);

        BoardService boardService = new BoardServiceOracleImpl();
        boardService.createBoard(map);

        BoardService boardAllService = new BoardServiceOracleImpl();
        List<BoardVO> board = boardAllService.getAll();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("board.jsp");
        req.setAttribute("member", member);
        req.setAttribute("board", board);
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}