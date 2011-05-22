package com.why.util;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class DB {
    
    
    public static Mongo mongo;
    public static Morphia morphia;
    public static Datastore ds;
    
    private DB() throws Exception {
        throw new Exception("你用的是反射吗");
    }
}
