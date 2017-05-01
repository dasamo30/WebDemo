/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
import java.util.ArrayList;

/**
 *
 * @author dasamo
 */
public interface IInventoryDAO {
    
    public int RegisterProduct(ProductBean product);
    public int ModifyProduct(ProductBean product);  
    public ProductBean get_Product(int product_id);
    public int DeleteProduct(int product_id);
    public ArrayList<TableProductBean> get_list_Product();
    
    public int registerLocation(LocationBean location);
    public int modifyLocation(LocationBean location); 
    public LocationBean get_Location(int location_id);
    public int deleteLocation(int location_id);
    public ArrayList<TableLocationBean> get_list_Location();
}
