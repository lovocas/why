package com.why.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.why.dao.UserDAO;
import com.why.model.User;
import com.why.util.DB;

public class FollowUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        
        ObjectId viewedUserId = new ObjectId(req.getParameter("viewedid"));
       
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        User viewedUser = dao.get(viewedUserId);
        
        
        User user = (User)(req.getSession().getAttribute("user"));
        
        if(null == user) {
            out.print("fail");
            return;
        }
        user.followPerson(viewedUser);
        out.print("ok");
        
    }
}
