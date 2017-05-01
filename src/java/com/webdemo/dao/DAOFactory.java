/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao;

import com.webdemo.dao.inventory.IInventoryDAO;



/**
 *
 * @author dasamo
 */


public abstract class DAOFactory {
    
    	public static final int MYSQL 	= 1;
	public static final int ORACLE 	= 2;
	public static final int POSTGRES = 3;
	public static final int SQLSERVER = 4;
	
        
        public abstract IAccesosDAO getAccesosDAO();
        public abstract IInventoryDAO getInventoryDAO();
        //public abstract IReportesDAO getReportesDAO();
        
        public static DAOFactory getDAOFactory(int db){
		
		switch(db){
			case POSTGRES: 
				return new PgsqlDAOFActory();
			default: 
				return null;
		}
		
	}
}
