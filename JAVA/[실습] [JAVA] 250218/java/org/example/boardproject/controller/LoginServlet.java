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

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("loginForm.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String member_id = req.getParameter("id");
        String password = req.getParameter("pw");

        Map<String, String> map = new HashMap<>();
        map.put("member_id", member_id);
        map.put("password", password);

        LoginService loginService = new LoginServiceOracleImpl();
        MemberVO member = loginService.login(map);

        if (member != null) {
            HttpSession session = req.getSession();
            session.setAttribute("member", member);

            BoardService boardService = new BoardServiceOracleImpl();
            List<BoardVO> board = boardService.getAll();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("board.jsp");
            req.setAttribute("member", member);
            req.setAttribute("board", board);
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("loginError.html");
        }
    }

    @Override
    public void destroy() {
    }
}