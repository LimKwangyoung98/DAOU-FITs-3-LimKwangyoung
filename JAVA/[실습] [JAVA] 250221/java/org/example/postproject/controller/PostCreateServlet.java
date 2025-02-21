package org.example.postproject.controller;

import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;
import org.example.postproject.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/create")
public class PostCreateServlet extends HttpServlet {
    public PostCreateServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        PostService postService = new PostServiceOracleImpl();
        postService.createPost(user.getUserId(), title, content);

        resp.sendRedirect("posts");
    }
}
