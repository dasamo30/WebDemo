/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.util;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
/**
 *
 * @author dasamo
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private final static Logger log = Logger.getLogger(HibernateUtil.class);
    private static Statistics stats;
    
    static {
         PropertyConfigurator.configure("/opt/apps/etc/log4j.properties");
        try {
            sessionFactory = new Configuration().configure(new File("/opt/apps/etc/hibernate.cfg.xml")).buildSessionFactory();
            stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);
            
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            log.error("Initial SessionFactory creation failed."+e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    
}
