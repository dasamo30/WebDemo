/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

import com.webdemo.beans.inventory.CategoryBean;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.SupplierBean;
import com.webdemo.beans.inventory.TableCategoryBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.beans.inventory.TableSupplierBean;
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
    
    public int registerCategory(CategoryBean category);
    public int modifyCategory(CategoryBean category); 
    public CategoryBean get_Category(int category_id);
    public int deleteCategory(int category_id);
    public ArrayList<TableCategoryBean> get_list_Categories();
    
    public int registerSupplier(SupplierBean supplier);
    public int modifySupplier(SupplierBean supplier); 
    public SupplierBean get_Supplier(int supplier_id);
    public int deleteSupplier(int supplier_id);
    public ArrayList<TableSupplierBean> get_list_Suppliers();
}