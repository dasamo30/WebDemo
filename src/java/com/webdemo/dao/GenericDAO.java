/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdemo.dao;


import com.webdemo.dao.util.HibernateUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author diegobenavides
 */

/**
 *
 * @author diegobenavides
 */
public abstract class GenericDAO  {

    private final static Logger log = Logger.getLogger("GenericDAO");

    //private final Class<T> claseDePersistencia;
    protected Session session;
   /* private Transaction tx;
    private PreparedStatement stm;
    public Statement stat;
    private ResultSet rest;
    private Connection con;
    private CallableStatement proc;
    public EntityManager em;*/
   
    @SuppressWarnings("unchecked")
    public GenericDAO() {

        // this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
         this.session = HibernateUtil.getSessionFactory().openSession();
    }
}
