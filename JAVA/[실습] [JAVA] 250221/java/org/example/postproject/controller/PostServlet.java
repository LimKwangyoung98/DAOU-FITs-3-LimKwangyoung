package org.example.postproject.controller;

import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;
import org.example.postproject.vo.PostVO;
import org.example.postproject.vo.UserVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/posts")
public class PostServlet extends HttpServlet {
    public PostServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        PostService postService = new PostServiceOracleImpl();
        List<PostVO> posts = postService.getAllPosts();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("board.jsp");
        req.setAttribute("user", user);
        req.setAttribute("posts", posts);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        req.setCharacterEncoding("utf-8");
        String searchType = req.getParameter("searchType");
        String keyword = req.getParameter("keyword");

        PostService postService = new PostServiceOracleImpl();
        List<PostVO> posts = postService.getPostsByKeyword(searchType, keyword);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("board.jsp");
        req.setAttribute("user", user);
        req.setAttribute("posts", posts);
        requestDispatcher.forward(req, resp);
    }
}
