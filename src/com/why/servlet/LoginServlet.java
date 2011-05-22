package com.why.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.why.model.User;
import com.why.util.DB;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = new String(req.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        String password = req.getParameter("password");
        Datastore ds = DB.ds;
        if(password != null && null!=email && "" != email) {
            Query<User> query = ds.createQuery(User.class).field("email").equal(email);
            List<User> list = query.asList();
            if(list.size() > 0 ) {
                User u = list.get(0);
                if(password.equals(u.getPassword())) {
                    req.getSession().setAttribute("user", u);
                    System.out.println(u.getUsername() + "========");
                }
            }
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
