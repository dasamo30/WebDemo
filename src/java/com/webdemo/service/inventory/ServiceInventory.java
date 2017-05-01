/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.service.inventory;

import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.dao.DAOFactory;
import com.webdemo.dao.inventory.IInventoryDAO;
import java.util.ArrayList;

/**
 *
 * @author dasamo
 */
public class ServiceInventory {
    private DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
    IInventoryDAO inventoryDAO=fabrica.getInventoryDAO();
    
    public int RegisterProduct(ProductBean product){
    
        return inventoryDAO.RegisterProduct(product);
    
    }
    
    public ArrayList<TableProductBean> get_list_Product(){
    
        return  inventoryDAO.get_list_Product();
    }
    
    public int deleteProduct(int product_id){
        return  inventoryDAO.DeleteProduct(product_id);
    }
    
    public ProductBean get_Product(int product_id){
        return inventoryDAO.get_Product(product_id);
    }
    
    public int ModifyProduct(ProductBean product){
        return inventoryDAO.ModifyProduct(product);
    }
    
    public int registerLocation(LocationBean location){
        return inventoryDAO.registerLocation(location);
    
    }
    
    public ArrayList<TableLocationBean> get_list_Location(){
        return inventoryDAO.get_list_Location();
    }
    
    public int deleteLocation(int location_id){
        return inventoryDAO.deleteLocation(location_id);
    }
    
    public LocationBean get_Location(int location_id){
        return inventoryDAO.get_Location(location_id);
    }
    
    public int modifyLocation(LocationBean location){
        return inventoryDAO.modifyLocation(location);
    }
}
