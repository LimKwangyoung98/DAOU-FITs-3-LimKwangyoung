package org.example.postproject.controller;

import org.example.postproject.service.PostService;
import org.example.postproject.service.PostServiceOracleImpl;
import org.example.postproject.vo.PostVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/like")
public class PostLikeServlet extends HttpServlet {
    public PostLikeServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        int postId = Integer.parseInt(req.getParameter("postId"));

        PostService postService = new PostServiceOracleImpl();
        boolean isLike = postService.getIsLike(userId, postId);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"isLike\": " + isLike + "}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("postId"));
        String userId = req.getParameter("userId");
        boolean likeRequest = Boolean.parseBoolean(req.getParameter("likeRequest"));

        PostService postService = new PostServiceOracleImpl();
        Map<String, Object> likeMap = postService.toggleLike(postId, userId, likeRequest);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{" +
                "\"likeResponse\": " + likeMap.get("likeResponse") + ","
                + "\"likeCount\": " + likeMap.get("likeCount")
                + "}");
    }
}
