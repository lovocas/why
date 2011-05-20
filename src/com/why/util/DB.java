package com.why.util;

import javax.servlet.ServletContext;

import com.google.code.morphia.Datastore;

public class DB {
    public static Datastore getDatastore(ServletContext context) {
        return (Datastore)context.getAttribute("datastore");
    }
}
