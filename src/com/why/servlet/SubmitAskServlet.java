package com.why.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.why.model.User;

public class SubmitAskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User u = (User)(req.getSession().getAttribute("user"));
        
        //检查身份
        if(null == u) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        if(null != title &&title.length() > 10) {
            u.askQuestion(title, content, req.getSession().getServletContext());
            
            req.getRequestDispatcher("Questions").forward(req, resp);
        }
    }
}
