package com.why.servlet;

import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config){
        ServletContext context = config.getServletContext();
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            System.out.println(null == mongo);
            Morphia morphia = new Morphia();
            Datastore ds = morphia.createDatastore(mongo, "zhiye");
            ds.ensureCaps();
            ds.ensureIndexes();
            context.setAttribute("mongo", mongo);
            context.setAttribute("datastore", ds);
            
            
            
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }


}
