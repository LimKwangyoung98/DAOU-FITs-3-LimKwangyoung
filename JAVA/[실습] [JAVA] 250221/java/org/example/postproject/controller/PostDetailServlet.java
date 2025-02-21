package org.example.postproject.controller;

import org.example.postproject.service.CommentService;
import org.example.postproject.service.CommentServiceOracleImpl;
import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;
import org.example.postproject.vo.CommentVO;
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

@WebServlet(value = "/detail")
public class PostDetailServlet extends HttpServlet {
    public PostDetailServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");

        String postId = req.getParameter("postId");

        PostService postService = new PostServiceOracleImpl();
        PostVO post = postService.getPostByPostId(Integer.parseInt(postId));

        CommentService commentService = new CommentServiceOracleImpl();
        List<CommentVO> comments = commentService.getCommentsByPostId(Integer.parseInt(postId));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("boardDetail.jsp");
        req.setAttribute("user", user);
        req.setAttribute("post", post);
        req.setAttribute("comments", comments);
        requestDispatcher.forward(req, resp);
    }
}
