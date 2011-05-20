package com.why.servlet;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.why.model.User;
import com.why.util.DB;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        User user  = null;
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String bio = req.getParameter("bio");
        String website = req.getParameter("website");
        String isFeMale = req.getParameter("gender");
        user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setMale("1".equals(isFeMale)? false:true);
        user.setWebsite(website);
        user.setPassword(password);
        
      
        Datastore ds = DB.getDatastore(req.getSession().getServletContext());
        System.out.println(ds == null);
        
        ds.ensureCaps();
        ds.ensureIndexes();
        ds.save(user);
        
        try {
            req.getRequestDispatcher("regok.jsp").forward(req, resp);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //验证用客户端的


    }

}
