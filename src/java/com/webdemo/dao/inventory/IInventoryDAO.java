/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

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
import java.util.ArrayList;
import java.util.Map;

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
    public Map<String, Object> get_list_dataTable_Product(int offset,int limit, String searchColumn, String searchValue);
    public ArrayList<ProductBean> get_Product_Search(int typeSearch,String valor);
    
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
    
    public int savePurchaseOrder(PurchaseOrderBean purchaseOrderBean);
    public ArrayList<TablePurchaseOrder> get_list_purchaseOrderBean();
    public PurchaseOrderBean get_purchaseOrderBean(int id_purchase_order);
    public ArrayList<PurchaseOrderDetailBean> get_list_purchaseOrderDetailBean(int id_purchase_order);
     public int deletePurchaseOrderBean(int id_purchase_order);
     
    public int saveMerchandiseIncome(MerchandiseIncomeBean merchandiseIncomeBean);  
    public ArrayList<TableMerchandiseIncome> get_list_merchandiseIncomeBean();
    public MerchandiseIncomeBean get_MerchandiseIncomeBean(int id_merchandise_income);
    public ArrayList<MerchandiseIncomeDetailBean> get_list_merchandiseIncomeDetailBean(int id_merchandise_income);
    public int deleteMerchandiseIncomeBean(int id_merchandise_income);
    
    public int saveTransfer(TransferBean transferBean);  
    public ArrayList<TableTransferBean> get_list_transferBean();
    public TransferBean get_TransferBean(int id_transfer);
    public ArrayList<TransferDetailBean> get_list_transferDetailBean(int id_transfer);
    public int deleteTransferBean(int id_transfer);
    
    public int validateIncomePO(String nro_document); 
    
}
