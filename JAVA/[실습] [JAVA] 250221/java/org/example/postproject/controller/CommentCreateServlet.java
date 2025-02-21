package org.example.postproject.controller;

import org.example.postproject.service.CommentService;
import org.example.postproject.service.CommentServiceOracleImpl;
import org.example.postproject.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/commentCreate")
public class CommentCreateServlet extends HttpServlet {
    public CommentCreateServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        CommentService commentService = new CommentServiceOracleImpl();
        commentService.createComment(Integer.parseInt(postId), user.getUserId(), content);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"status\": \"success\"}");
    }
}
