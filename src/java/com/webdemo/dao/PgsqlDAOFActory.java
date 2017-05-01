/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao;

import com.webdemo.dao.inventory.IInventoryDAO;
import com.webdemo.dao.inventory.InventoryDAOImplements;



/**
 *
 * @author dasamo
 */
public class PgsqlDAOFActory extends DAOFactory{

    @Override
    public IAccesosDAO getAccesosDAO() {
       return new AcessoDAOImplements();
    } 

    @Override
    public IInventoryDAO getInventoryDAO() {
        return new InventoryDAOImplements();
    }
}
