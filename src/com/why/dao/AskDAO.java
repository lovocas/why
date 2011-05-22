package com.why.dao;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.why.model.Ask;


public class AskDAO extends BasicDAO<Ask, ObjectId>{
    public AskDAO( Morphia morphia, Mongo mongo ) {
        super(mongo, morphia, "zhiye");
    }
}
