package org.example.postproject.controller;

import org.example.postproject.service.LoginService;
import org.example.postproject.service.LoginServiceOracleImpl;
import org.example.postproject.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        LoginService loginService = new LoginServiceOracleImpl();
        UserVO user = loginService.login(userId, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("intro.html");
        } else {
            resp.sendRedirect("loginError.html");
        }
    }
}
