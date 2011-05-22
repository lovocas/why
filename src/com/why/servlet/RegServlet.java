package com.why.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.why.model.User;
import com.why.util.DB;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        User user  = null;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = null;
        try {
            username = new String(req.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String bio = req.getParameter("bio");
        String website = req.getParameter("website");
        String isFeMale = req.getParameter("gender");
        user = new User();
        user.setUsername(username);
        user.setBio(bio);
        user.setEmail(email);
        user.setMale("1".equals(isFeMale)? false:true);
        user.setWebsite(website);
        user.setPassword(password);
        
      
        Datastore ds = DB.ds;
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
