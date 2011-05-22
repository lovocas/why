package com.why.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.why.model.Ask;
import com.why.model.User;
import com.why.util.DB;

public class QuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        Datastore ds = DB.ds;
        
        Query query = ds.createQuery(Ask.class);
        List<Ask> asks = query.asList();
        req.setAttribute("asks", asks);
        req.getRequestDispatcher("asks.jsp").forward(req, resp);
    }

}
