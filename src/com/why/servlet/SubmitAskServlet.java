package com.why.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.why.model.User;

public class SubmitAskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        process(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("dadaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa===111+");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User u = (User)(req.getSession().getAttribute("user"));
        
        //检查身份
        if(null == u) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        if(null != title &&title.length() > 10) {
            ObjectId id = u.askQuestion(title, content);
            req.setAttribute("askid", id);
            req.getRequestDispatcher("ViewAskServlet").forward(req, resp);
        }
    }
}
