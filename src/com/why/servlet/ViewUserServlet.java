package com.why.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.why.dao.UserDAO;
import com.why.model.User;
import com.why.util.DB;

public class ViewUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        try{
            ObjectId id = new ObjectId(req.getParameter("askid"));
            
            UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
            
            User  user = dao.get(id);
            
            if(null != user) {
                req.setAttribute("vieweduser", user);
            }
            
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        finally {
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        }

    }

}
