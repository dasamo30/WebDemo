/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.service.inventory;

import com.webdemo.beans.inventory.CategoryBean;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.MerchandiseIncomeBean;
import com.webdemo.beans.inventory.MerchandiseIncomeDetailBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.PurchaseOrderBean;
import com.webdemo.beans.inventory.PurchaseOrderDetailBean;
import com.webdemo.beans.inventory.SupplierBean;
import com.webdemo.beans.inventory.TableCategoryBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableMerchandiseIncome;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.beans.inventory.TablePurchaseOrder;
import com.webdemo.beans.inventory.TableSupplierBean;
import com.webdemo.beans.inventory.TableTransferBean;
import com.webdemo.beans.inventory.TransferBean;
import com.webdemo.beans.inventory.TransferDetailBean;
import com.webdemo.dao.DAOFactory;
import com.webdemo.dao.inventory.IInventoryDAO;
import java.util.ArrayList;
import java.util.Map;

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
    
    public Map<String, Object> get_list_dataTable_Product(int offset, int limit, String searchColumn, String searchValue) {
        return  inventoryDAO.get_list_dataTable_Product(offset, limit, searchColumn, searchValue);
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
    
    public ArrayList<TablePurchaseOrder> get_list_purchaseOrderBean() {
        return inventoryDAO.get_list_purchaseOrderBean();
    }
    
    public PurchaseOrderBean get_purchaseOrderBean(int id_purchase_order) {
        return inventoryDAO.get_purchaseOrderBean(id_purchase_order);
    }
    
    public ArrayList<PurchaseOrderDetailBean> get_list_purchaseOrderDetailBean(int id_purchase_order) {
        return inventoryDAO.get_list_purchaseOrderDetailBean(id_purchase_order);
    }
    
    public int deletePurchaseOrderBean(int id_purchase_order) {
        return inventoryDAO.deletePurchaseOrderBean(id_purchase_order);
    }
    
    public int saveMerchandiseIncome(MerchandiseIncomeBean merchandiseIncomeBean) {
        return inventoryDAO.saveMerchandiseIncome(merchandiseIncomeBean);
    
    }
    
    public ArrayList<TableMerchandiseIncome> get_list_merchandiseIncomeBean(){
        return inventoryDAO.get_list_merchandiseIncomeBean();
    }
    
    public MerchandiseIncomeBean get_MerchandiseIncomeBean(int id_merchandise_income){
        return inventoryDAO.get_MerchandiseIncomeBean(id_merchandise_income);
    }
    
    public ArrayList<MerchandiseIncomeDetailBean> get_list_merchandiseIncomeDetailBean(int id_merchandise_income){
        return inventoryDAO.get_list_merchandiseIncomeDetailBean(id_merchandise_income);
    }
    
    public int deleteMerchandiseIncomeBean(int id_merchandise_income){
        return inventoryDAO.deleteMerchandiseIncomeBean(id_merchandise_income);
    }
    
    public int saveTransfer(TransferBean transferBean){
        return inventoryDAO.saveTransfer(transferBean);
    } 
    
    public ArrayList<TableTransferBean> get_list_transferBean(){
        return inventoryDAO.get_list_transferBean();
    }
    
    public TransferBean get_TransferBean(int id_transfer){
        return inventoryDAO.get_TransferBean(id_transfer);
    }
    
    public ArrayList<TransferDetailBean> get_list_transferDetailBean(int id_transfer){
        return inventoryDAO.get_list_transferDetailBean(id_transfer);
    }
    
    public int deleteTransferBean(int id_transfer){
        return inventoryDAO.deleteTransferBean(id_transfer);
    }
    
    public int validateIncomePO(String nro_document){
        return inventoryDAO.validateIncomePO(nro_document);
    }
}
