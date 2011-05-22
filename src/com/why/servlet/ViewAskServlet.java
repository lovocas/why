package com.why.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.why.dao.AskDAO;
import com.why.model.Ask;
import com.why.util.DB;

public class ViewAskServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        process(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ObjectId id = (ObjectId)req.getAttribute("askid");
        AskDAO  dao = new AskDAO(DB.morphia, DB.mongo);
        Ask ask = dao.get(id);
        req.setAttribute("ask", ask);
        req.getRequestDispatcher("ask.jsp").forward(req, resp);
    }
}
