package org.example.postproject.controller;

import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class PostDeleteServlet extends HttpServlet {
    public PostDeleteServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");

        PostService postService = new PostServiceOracleImpl();
        postService.deletePost(Integer.parseInt(postId));

        resp.sendRedirect("posts");
    }
}
