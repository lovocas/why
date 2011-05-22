package com.why.servlet;

import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.why.util.DB;

public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config){
        try {
            DB.mongo = new Mongo("localhost", 27017);
            System.out.println(null == DB.mongo ? "mongo初始化出现错误":"mongo 初始化成功");
            DB.morphia = new Morphia();
            DB.ds = DB.morphia.createDatastore(DB.mongo, "zhiye");
            DB.ds.ensureCaps();
            DB.ds.ensureIndexes();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }


}
