/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.service.inventory;

import com.webdemo.beans.inventory.CategoryBean;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.PurchaseOrderBean;
import com.webdemo.beans.inventory.SupplierBean;
import com.webdemo.beans.inventory.TableCategoryBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.beans.inventory.TableSupplierBean;
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
    //search
    
    
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
    
    public int registerCategory(CategoryBean category){
        return inventoryDAO.registerCategory(category);
     }
    
     public int modifyCategory(CategoryBean category){
         return inventoryDAO.modifyCategory(category);
     }
     
     public CategoryBean get_Category(int category_id){
        return inventoryDAO.get_Category(category_id);
     }
     
     public int deleteCategory(int category_id){
         return inventoryDAO.deleteCategory(category_id);
     }
     
     public ArrayList<TableCategoryBean> get_list_Categories(){
         return inventoryDAO.get_list_Categories();
     }
     
     
    public int registerSupplier(SupplierBean supplier) {
        return inventoryDAO.registerSupplier(supplier);
    }

    public int modifySupplier(SupplierBean supplier) {
        return inventoryDAO.modifySupplier(supplier);
    }

    
    public SupplierBean get_Supplier(int supplier_id) {
        return inventoryDAO.get_Supplier(supplier_id);
    }

    public int deleteSupplier(int supplier_id) {
        return inventoryDAO.deleteSupplier(supplier_id);
    }

    public ArrayList<TableSupplierBean> get_list_Suppliers() {
        return inventoryDAO.get_list_Suppliers();
    }
    
    public ArrayList<ProductBean> get_Product_Search(int typeSearch, String valor){
        return inventoryDAO.get_Product_Search(typeSearch, valor);
    }
    
    public int savePurchaseOrder(PurchaseOrderBean purchaseOrderBean){
        return inventoryDAO.savePurchaseOrder(purchaseOrderBean);
    }
}
