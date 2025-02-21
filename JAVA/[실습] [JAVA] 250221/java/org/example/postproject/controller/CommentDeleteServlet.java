package org.example.postproject.controller;

import org.example.postproject.service.CommentService;
import org.example.postproject.service.CommentServiceOracleImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/commentDelete")
public class CommentDeleteServlet extends HttpServlet {
    public CommentDeleteServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentId = req.getParameter("commentId");

        CommentService commentService = new CommentServiceOracleImpl();
        commentService.deleteComment(Integer.parseInt(commentId));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"status\": \"success\"}");
    }
}
