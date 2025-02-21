package org.example.postproject.controller;

import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;
import org.example.postproject.vo.UserVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/update")
public class PostUpdateServlet extends HttpServlet {
    public PostUpdateServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("postUpdate.jsp");
        req.setAttribute("postId", postId);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String postId = req.getParameter("postId");

        PostService postService = new PostServiceOracleImpl();
        postService.updatePost(title, content, Integer.parseInt(postId));

        resp.sendRedirect("posts");
    }
}
