/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

import com.google.gson.Gson;
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
import com.webdemo.dao.GenericDAO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

/**
 *
 * @author dasamo
 */
public class InventoryDAOImplements extends GenericDAO implements IInventoryDAO{
    private  Gson gson = new Gson(); 
    @Override
    public int RegisterProduct(ProductBean product) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select register_product from inventory.register_product( :code , :name , :description , :unit_cost, :image_name, :alert_stock, :id_category);";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("code", product.getCode());
         query.setParameter("name", product.getName());
         query.setParameter("description", product.getDescription());
         query.setParameter("unit_cost", product.getUnit_cost());
         query.setParameter("image_name", product.getImage_name(), Hibernate.STRING);
         query.setParameter("alert_stock", product.getAlert_stock());
         query.setParameter("id_category", product.getId_category());
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("register_product");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public ArrayList<TableProductBean> get_list_Product() {
        
        Transaction tx = null;
        ArrayList<TableProductBean> litsProductBean =new ArrayList<TableProductBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT product_id, code, name, description, unit_cost,registration_date,image_name,stock,alert_stock, id_category FROM inventory.view_products;";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableProductBean productBean= new TableProductBean();
            productBean.setId((Integer)row.get("product_id"));
            productBean.setCode((String) row.get("code"));
            productBean.setName((String)row.get("name"));
            productBean.setDescription((String)row.get("description"));
            productBean.setUnit_cost(((BigDecimal)row.get("unit_cost")).doubleValue());
            productBean.setRegistration_date((Date) row.get("registration_date"));
            productBean.setImage_name((String)row.get("image_name"));
            productBean.setStock((Integer)row.get("stock"));
            productBean.setAlert_stock((Integer)row.get("alert_stock"));
            productBean.setId_category((Integer)row.get("id_category"));
            
            litsProductBean.add(productBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsProductBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsProductBean;
        
    }

    @Override
    public int DeleteProduct(int product_id) {
       int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_product from inventory.delete_product( :product_id );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("product_id", product_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_product");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta; 
    }

    @Override
    public ProductBean get_Product(int product_id) {
      //int rpta =-1;  
      Transaction tx = null;
      ProductBean productBean=new TableProductBean();
      
      try{
         tx = session.beginTransaction();
         String sql = "SELECT product_id, code, name, description, unit_cost,registration_date,image_name,stock,alert_stock, id_category FROM inventory.view_products where product_id= :product_id ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("product_id",product_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          productBean.setId((Integer) data.get("product_id"));
          productBean.setCode((String) data.get("code"));
          productBean.setName((String)data.get("name"));
          productBean.setDescription((String)data.get("description"));
          productBean.setUnit_cost(((BigDecimal)data.get("unit_cost")).doubleValue());
          productBean.setRegistration_date((Date) data.get("registration_date"));
          productBean.setImage_name((String)data.get("image_name"));
          productBean.setStock((Integer)data.get("stock"));
          productBean.setAlert_stock((Integer)data.get("alert_stock"));
          productBean.setId_category((Integer)data.get("id_category"));
//rpta=(Integer) ().get("register_product");
          
          ////System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return productBean;
    }

    @Override
    public int ModifyProduct(ProductBean product) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modify_product from inventory.modify_product( :product_id , :code , :name , :description , :unit_cost, :image_name, :alert_stock, :id_category );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("product_id", product.getId());
         query.setParameter("code", product.getCode());
         query.setParameter("name", product.getName());
         query.setParameter("description", product.getDescription());
         query.setParameter("unit_cost", product.getUnit_cost());
         query.setParameter("image_name", product.getImage_name(), Hibernate.STRING);
         query.setParameter("alert_stock", product.getAlert_stock());
         query.setParameter("id_category", product.getId_category());        
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modify_product");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta; 
    }

    @Override
    public int registerLocation(LocationBean location) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select register_location from inventory.register_location( :name_location, :date_creation);";
          //System.out.println("sql::"+sql);
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("name_location", location.getName_location());
         query.setParameter("date_creation", location.getDate_creation());
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("register_location");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public int modifyLocation(LocationBean location) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modify_location from inventory.modify_location( :id_location , :name_location , :date_modification );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_location", location.getId_location());
         query.setParameter("name_location", location.getName_location());
         query.setParameter("date_modification", location.getDate_modification());
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modify_location");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public LocationBean get_Location(int location_id) {
        Transaction tx = null;
      LocationBean locationBean=new LocationBean();
      
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_location, name_location, date_creation, date_modification FROM inventory.view_locations where id_location= :location_id ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("location_id",location_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          locationBean.setId_location((Integer) data.get("id_location"));
          locationBean.setName_location((String) data.get("name_location"));
          locationBean.setDate_creation((Date)data.get("date_creation"));
          locationBean.setDate_modification((Date)data.get("date_modification"));
//rpta=(Integer) ().get("register_product");
          
          ////System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return locationBean;
    }

    @Override
    public int deleteLocation(int location_id) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_location from inventory.delete_location( :location_id );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("location_id", location_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_location");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public ArrayList<TableLocationBean> get_list_Location() {
        Transaction tx = null;
        ArrayList<TableLocationBean> litsLocationBean =new ArrayList<TableLocationBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_location, name_location, date_creation, date_modification FROM inventory.view_locations;";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableLocationBean locationBean= new TableLocationBean();
            locationBean.setId_location((Integer)row.get("id_location"));
            locationBean.setName_location((String) row.get("name_location"));
            locationBean.setDate_creation((Date)row.get("date_creation"));
            locationBean.setDate_modification((Date)row.get("date_modification"));
            
            
            litsLocationBean.add(locationBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsLocationBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsLocationBean;
    }

    @Override
    public int registerCategory(CategoryBean category) {
        int rpta = -1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select register_category from inventory.register_category( :name_category, :date_creation);";
            //System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("name_category", category.getName_category());
            query.setParameter("date_creation", category.getDate_creation());
            //query.setParameter("clave", pass);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            rpta = (Integer) ((HashMap) data.get(0)).get("register_category");
            //System.out.println("list::::>" + rpta);

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            //rpta=1;
            e.printStackTrace();
        } finally {
            //session.close(); 
            tx.commit();
        }
        return rpta;
    }

    @Override
    public int modifyCategory(CategoryBean category) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modify_category from inventory.modify_category( :id_category , :name_category , :date_modification );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_category", category.getId_category());
         query.setParameter("name_category", category.getName_category());
         query.setParameter("date_modification", category.getDate_modification());
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modify_category");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public CategoryBean get_Category(int category_id) {
        Transaction tx = null;
      CategoryBean categoryBean=new CategoryBean();
      
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_category, name_category, date_creation, date_modification FROM inventory.view_categories where id_category= :category_id ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("category_id",category_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          categoryBean.setId_category((Integer) data.get("id_category"));
          categoryBean.setName_category((String) data.get("name_category"));
          categoryBean.setDate_creation((Date)data.get("date_creation"));
          categoryBean.setDate_modification((Date)data.get("date_modification"));
//rpta=(Integer) ().get("register_product");
          
          ////System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return categoryBean;
    }

    @Override
    public int deleteCategory(int category_id) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_category from inventory.delete_category( :category_id );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("category_id", category_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_category");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public ArrayList<TableCategoryBean> get_list_Categories() {
        Transaction tx = null;
        ArrayList<TableCategoryBean> litsCategoryBean =new ArrayList<TableCategoryBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_category, name_category, date_creation, date_modification FROM inventory.view_categories;";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableCategoryBean categoryBean= new TableCategoryBean();
            categoryBean.setId_category((Integer)row.get("id_category"));
            categoryBean.setName_category((String) row.get("name_category"));
            categoryBean.setDate_creation((Date)row.get("date_creation"));
            categoryBean.setDate_modification((Date)row.get("date_modification"));
            
            
            litsCategoryBean.add(categoryBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsCategoryBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsCategoryBean;
    }

    @Override
    public int registerSupplier(SupplierBean supplier) {
        int rpta = -1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select register_suppliers from inventory.register_suppliers( :code_suppliers, :name_suppliers, :phone_number, :web, :date_creation);";
            //System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("code_suppliers", supplier.getCode_suppliers());
            query.setParameter("name_suppliers", supplier.getName_suppliers());
            query.setParameter("phone_number", supplier.getPhone_number());
            query.setParameter("web", supplier.getWeb());
            query.setParameter("date_creation", supplier.getDate_creation());
            //query.setParameter("clave", pass);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            rpta = (Integer) ((HashMap) data.get(0)).get("register_suppliers");
            //System.out.println("list::::>" + rpta);

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            //rpta=1;
            e.printStackTrace();
        } finally {
            //session.close(); 
            tx.commit();
        }
        return rpta;
    }

    @Override
    public int modifySupplier(SupplierBean supplier) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modify_suppliers from inventory.modify_suppliers( :id_supplier , :code_suppliers, :name_suppliers, :phone_number, :web, :date_modification );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_supplier", supplier.getId_supplier());
         query.setParameter("code_suppliers", supplier.getCode_suppliers());
         query.setParameter("name_suppliers", supplier.getName_suppliers());
         query.setParameter("phone_number", supplier.getPhone_number());
         query.setParameter("web", supplier.getWeb());
         query.setParameter("date_modification", supplier.getDate_modification());

         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modify_suppliers");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public SupplierBean get_Supplier(int supplier_id) {
        Transaction tx = null;
      SupplierBean supplierBean=new SupplierBean();
      
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_supplier, code_suppliers, name_suppliers, phone_number, web, date_creation, date_modification FROM inventory.view_suppliers where id_supplier= :supplier_id ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("supplier_id",supplier_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          supplierBean.setId_supplier((Integer)data.get("id_supplier"));
            supplierBean.setCode_suppliers((String) data.get("code_suppliers"));
            supplierBean.setName_suppliers((String) data.get("name_suppliers"));
            supplierBean.setPhone_number((String) data.get("phone_number"));
            supplierBean.setWeb((String) data.get("web"));
            supplierBean.setDate_creation((Date)data.get("date_creation"));
            supplierBean.setDate_modification((Date)data.get("date_modification"));
//rpta=(Integer) ().get("register_product");
          
          ////System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return supplierBean;
    }

    @Override
    public int deleteSupplier(int supplier_id) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_suppliers from inventory.delete_suppliers( :supplier_id );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("supplier_id", supplier_id);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_suppliers");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public ArrayList<TableSupplierBean> get_list_Suppliers() {
        Transaction tx = null;
        ArrayList<TableSupplierBean> litsSupplierBean =new ArrayList<TableSupplierBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_supplier, code_suppliers, name_suppliers, phone_number, web, date_creation, date_modification FROM inventory.view_suppliers;";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableSupplierBean supplierBean= new TableSupplierBean();
            supplierBean.setId_supplier((Integer)row.get("id_supplier"));
            supplierBean.setCode_suppliers((String) row.get("code_suppliers"));
            supplierBean.setName_suppliers((String) row.get("name_suppliers"));
            supplierBean.setPhone_number((String) row.get("phone_number"));
            supplierBean.setWeb((String) row.get("web"));
            supplierBean.setDate_creation((Date)row.get("date_creation"));
            supplierBean.setDate_modification((Date)row.get("date_modification"));
            
            
            litsSupplierBean.add(supplierBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsSupplierBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsSupplierBean;
    }

    @Override
    public ArrayList<ProductBean> get_Product_Search(int typeSearch, String valor) {
        Transaction tx = null;
        ArrayList<ProductBean> litsProductBean =new ArrayList<ProductBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT product_id, code, name, description, unit_cost,registration_date,image_name FROM inventory.product_search( :typeSearch, :valor)";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("typeSearch",typeSearch);
            query.setParameter("valor",valor);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            ProductBean productBean= new ProductBean();
            productBean.setId((Integer)row.get("product_id"));
            productBean.setCode((String) row.get("code"));
            productBean.setName((String)row.get("name"));
            productBean.setDescription((String)row.get("description"));
            /* System.out.println("---"+row.get("unit_cost").getClass().getTypeName());
             System.out.println("---"+row.get("unit_cost"));
             System.out.println("---"+row.get("unit_cost").getClass());*/
            productBean.setUnit_cost(((BigDecimal)row.get("unit_cost")).doubleValue());
            productBean.setRegistration_date((Date) row.get("registration_date"));
            productBean.setImage_name((String)row.get("image_name"));
            
            
            litsProductBean.add(productBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsProductBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsProductBean;
    }

    @Override
    public int savePurchaseOrder(PurchaseOrderBean purchaseOrderBean) {
        int rpta = -1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sql = "INSERT INTO inventory.purchase_order( id_supplier, amount, username, date_creation) VALUES ( :id_supplier, :amount, :username, :date_creation) RETURNING id_purchase_order;";
            //System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("id_supplier", purchaseOrderBean.getSupplier().getId_supplier());
            query.setParameter("amount", purchaseOrderBean.getAmount());
            query.setParameter("username", purchaseOrderBean.getUsername());
            query.setParameter("date_creation", purchaseOrderBean.getDateCreation());
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            int id_purchase_order=(Integer) ((HashMap)data.get(0)).get("id_purchase_order");       
            //System.out.println("list::::>"+rpta);
            
            //query.setParameter("clave", pass);
            //INSERT INTO inventory.purchase_order( id_supplier, amount, username, date_creation,registration_date) VALUES (4, 50, 'dasamo', now()::date, now() ) RETURNING id_purchase_order;
            /*
            ResultSet generatedKeys = null;
            int generatedKey = -1;
            generatedKey = Integer.parseInt(readOneValue("SELECT @@IDENTITY"));
            */
            
            List<PurchaseOrderDetailBean> detail=purchaseOrderBean.getDetails();
            int rsd;
            for (PurchaseOrderDetailBean p : detail) {
                
                String sqld="INSERT INTO inventory.purchase_order_detail(id_purchase_order, id_product, amount, cost_price, date_creation) VALUES ( :id_purchase_order, :id_product, :amount, :cost_price , :date_creation );";
                SQLQuery queryd = session.createSQLQuery(sqld);
                queryd.setParameter("id_purchase_order",id_purchase_order);
                queryd.setParameter("id_product", p.getProduct().getId());
                queryd.setParameter("amount", p.getAmount());
                queryd.setParameter("cost_price", p.getCostPrice());
                queryd.setParameter("date_creation",purchaseOrderBean.getDateCreation());            
                rsd=queryd.executeUpdate();   
                //System.out.println("res:::"+rsd);
            }
            rpta=0;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            //rpta=1;
            e.printStackTrace();
        } finally {
            //session.close(); 
            tx.commit();
        }
        return rpta;    
    }

    @Override
    public ArrayList<TablePurchaseOrder> get_list_purchaseOrderBean() {
        Transaction tx = null;
        ArrayList<TablePurchaseOrder> litsPurchaseOrderBean =new ArrayList<TablePurchaseOrder>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_purchase_order, id_supplier, code_suppliers,"
                    + " name_suppliers, amount, username, date_creation, registration_date "
                    + "FROM inventory.view_purchase_order;";
            SQLQuery query = session.createSQLQuery(sql);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TablePurchaseOrder purchaseOrder= new TablePurchaseOrder();
            purchaseOrder.setId((Integer)row.get("id_purchase_order"));
            //purchaseOrder.getSupplier().setId_supplier((Integer) row.get("id_supplier"));
            purchaseOrder.setCode_suppliers((String)row.get("code_suppliers"));
            purchaseOrder.setName_suppliers((String)row.get("name_suppliers"));
            purchaseOrder.setAmount(((BigDecimal)row.get("amount")).doubleValue());
            purchaseOrder.setUsername((String)row.get("username"));
            purchaseOrder.setDateCreation((Date) row.get("date_creation"));
            purchaseOrder.setRegistration_date((Date) row.get("registration_date"));
            
            litsPurchaseOrderBean.add(purchaseOrder);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsPurchaseOrderBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsPurchaseOrderBean;
    }

    @Override
    public PurchaseOrderBean get_purchaseOrderBean(int id_purchase_order) {
        Transaction tx = null;
      PurchaseOrderBean purchaseOrderBean=new PurchaseOrderBean();
      
        //System.out.println("get_purchaseOrderBean_id_purchase_order:"+id_purchase_order);
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_purchase_order, id_supplier, amount, username, date_creation, registration_date FROM inventory.purchase_order where id_purchase_order = :id_purchase_order ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_purchase_order",id_purchase_order);
         
          
          query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          
          if(query.list().size()>0){
            HashMap data = (HashMap) query.list().get(0);
            //HashMap beanData=(HashMap) data.get(0);   

            SupplierBean sp=new SupplierBean();
            sp.setId_supplier((Integer)data.get("id_supplier"));

            purchaseOrderBean.setId((Integer)data.get("id_purchase_order"));
            purchaseOrderBean.setUsername((String) data.get("username"));
            purchaseOrderBean.setDateCreation((Date)data.get("date_creation"));
            purchaseOrderBean.setAmount(((BigDecimal)data.get("amount")).doubleValue());
            purchaseOrderBean.setSupplier(sp);
          }else{
              purchaseOrderBean=null;
          }
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return purchaseOrderBean;
    }

    @Override
    public ArrayList<PurchaseOrderDetailBean> get_list_purchaseOrderDetailBean(int id_purchase_order) {
        Transaction tx = null;
        ArrayList<PurchaseOrderDetailBean> ListPurchaseOrderDetail=new ArrayList<PurchaseOrderDetailBean>();
        try{
            tx = session.beginTransaction();

            
          
           String sqld="SELECT id_purchase_order, id_product, amount, cost_price, date_creation FROM inventory.purchase_order_detail where id_purchase_order= :id ;";
           SQLQuery queryd = session.createSQLQuery(sqld);
           queryd.setParameter("id", id_purchase_order);
           
           queryd.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
           List dataList = queryd.list();
           
           //System.out.println("List data::"+dataList.size());
           
           for (Object object : dataList) {
               Map row = (Map)object;
               
               PurchaseOrderDetailBean purchaseOrderDetailBean=new PurchaseOrderDetailBean();
               purchaseOrderDetailBean.setAmount(((BigDecimal)row.get("amount")).doubleValue());
               purchaseOrderDetailBean.setCostPrice(((BigDecimal)row.get("cost_price")).doubleValue());
               purchaseOrderDetailBean.setDateCreation((Date)row.get("date_creation"));
               
               ProductBean product=new ProductBean();
               product.setId((Integer)row.get("id_product"));
               purchaseOrderDetailBean.setProduct(product);
               
              ListPurchaseOrderDetail.add(purchaseOrderDetailBean);
          }

          
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            ListPurchaseOrderDetail=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return ListPurchaseOrderDetail;
    }

    @Override
    public int deletePurchaseOrderBean(int id_purchase_order) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_purchase_order from inventory.delete_purchase_order( :id_purchase_order );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_purchase_order", id_purchase_order);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_purchase_order");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
        
    }
    
    @Override
    public int saveMerchandiseIncome(MerchandiseIncomeBean merchandiseIncomeBean) {
        int rpta = -1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sql = "INSERT INTO inventory.merchandise_income( id_supplier, amount, username, date_creation, nro_document, reason) VALUES ( :id_supplier, :amount, :username, :date_creation, :nro_document, :reason) RETURNING id_merchandise_income;";
            //System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("id_supplier", merchandiseIncomeBean.getSupplier().getId_supplier());
            query.setParameter("amount", merchandiseIncomeBean.getAmount());
            query.setParameter("username", merchandiseIncomeBean.getUsername());
            query.setParameter("date_creation", merchandiseIncomeBean.getDateCreation());
            query.setParameter("nro_document", merchandiseIncomeBean.getNro_document(), Hibernate.STRING);
            query.setParameter("reason", merchandiseIncomeBean.getReason(),Hibernate.STRING);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            int id_merchandise_income=(Integer) ((HashMap)data.get(0)).get("id_merchandise_income");       
            //System.out.println("list::::>"+rpta);
            
            //query.setParameter("clave", pass);
            //INSERT INTO inventory.purchase_order( id_supplier, amount, username, date_creation,registration_date) VALUES (4, 50, 'dasamo', now()::date, now() ) RETURNING id_purchase_order;
            /*
            ResultSet generatedKeys = null;
            int generatedKey = -1;
            generatedKey = Integer.parseInt(readOneValue("SELECT @@IDENTITY"));
            */
            
            List<MerchandiseIncomeDetailBean> detail=merchandiseIncomeBean.getDetails();
            int rsd;
            int rss;
            for (MerchandiseIncomeDetailBean p : detail) {
                
                String sqld="INSERT INTO inventory.merchandise_income_detail(id_merchandise_income, id_product, amount, cost_price, date_creation) VALUES ( :id_merchandise_income, :id_product, :amount, :cost_price , :date_creation );";
                SQLQuery queryd = session.createSQLQuery(sqld);
                queryd.setParameter("id_merchandise_income",id_merchandise_income);
                queryd.setParameter("id_product", p.getProduct().getId());
                queryd.setParameter("amount", p.getAmount());
                queryd.setParameter("cost_price", p.getCostPrice());
                queryd.setParameter("date_creation",merchandiseIncomeBean.getDateCreation());            
                rsd=queryd.executeUpdate();   
                //System.out.println("res:::"+rsd);
                
                //update inventory.products set stock=? where product_id=0
                String sqls="update inventory.products set stock=(stock + :pamount) where product_id=:product_id ;";
                SQLQuery querys = session.createSQLQuery(sqls);
                querys.setParameter("pamount", p.getAmount());
                querys.setParameter("product_id", p.getProduct().getId());
                rss=querys.executeUpdate();
                //System.out.println("rss:::"+rss);
            }
            rpta=0;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            //rpta=1;
            e.printStackTrace();
        } finally {
            //session.close(); 
            tx.commit();
        }
        return rpta;
        
    }

    @Override
    public ArrayList<TableMerchandiseIncome> get_list_merchandiseIncomeBean() {
        Transaction tx = null;
        ArrayList<TableMerchandiseIncome> litsMerchandiseIncomeBean =new ArrayList<TableMerchandiseIncome>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_merchandise_income, id_supplier, code_suppliers,"
                    + " name_suppliers, amount, username, date_creation, registration_date, nro_document, reason "
                    + "FROM inventory.view_merchandise_income;";
            SQLQuery query = session.createSQLQuery(sql);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableMerchandiseIncome merchandiseIncome= new TableMerchandiseIncome();
            merchandiseIncome.setId((Integer)row.get("id_merchandise_income"));
            //purchaseOrder.getSupplier().setId_supplier((Integer) row.get("id_supplier"));
            merchandiseIncome.setCode_suppliers((String)row.get("code_suppliers"));
            merchandiseIncome.setName_suppliers((String)row.get("name_suppliers"));
            merchandiseIncome.setAmount(((BigDecimal)row.get("amount")).doubleValue());
            merchandiseIncome.setUsername((String)row.get("username"));
            merchandiseIncome.setDateCreation((Date) row.get("date_creation"));
            merchandiseIncome.setRegistration_date((Date) row.get("registration_date"));
            merchandiseIncome.setNro_document((String)row.get("nro_document"));
            merchandiseIncome.setReason((String)row.get("reason"));
            
            litsMerchandiseIncomeBean.add(merchandiseIncome);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsMerchandiseIncomeBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsMerchandiseIncomeBean;
    }

    @Override
    public MerchandiseIncomeBean get_MerchandiseIncomeBean(int id_merchandise_income) {
        Transaction tx = null;
      MerchandiseIncomeBean merchandiseIncomeBean=new MerchandiseIncomeBean();
      
        //System.out.println("get_MerchandiseIncomeBean:"+id_merchandise_income);
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_merchandise_income, id_supplier, amount, username, date_creation,registration_date, nro_document, reason FROM inventory.merchandise_income where id_merchandise_income = :id_merchandise_income ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_merchandise_income",id_merchandise_income);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          
          SupplierBean sp=new SupplierBean();
          sp.setId_supplier((Integer)data.get("id_supplier"));
          
          merchandiseIncomeBean.setId((Integer)data.get("id_merchandise_income"));
          merchandiseIncomeBean.setUsername((String) data.get("username"));
          merchandiseIncomeBean.setDateCreation((Date)data.get("date_creation"));
          merchandiseIncomeBean.setAmount(((BigDecimal)data.get("amount")).doubleValue());
          merchandiseIncomeBean.setNro_document((String)data.get("nro_document"));
          merchandiseIncomeBean.setReason((String)data.get("reason"));
          merchandiseIncomeBean.setSupplier(sp);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return merchandiseIncomeBean;
    }

    @Override
    public ArrayList<MerchandiseIncomeDetailBean> get_list_merchandiseIncomeDetailBean(int id_merchandise_income) {
        Transaction tx = null;
        ArrayList<MerchandiseIncomeDetailBean> ListMerchandiseIncomeDetail=new ArrayList<MerchandiseIncomeDetailBean>();
        try{
            tx = session.beginTransaction();

            
          
           String sqld="SELECT id_merchandise_income, id_product, amount, cost_price, date_creation FROM inventory.merchandise_income_detail where id_merchandise_income= :id ;";
           SQLQuery queryd = session.createSQLQuery(sqld);
           queryd.setParameter("id", id_merchandise_income);
           
           queryd.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
           List dataList = queryd.list();
           
           //System.out.println("List data::"+dataList.size());
           
           for (Object object : dataList) {
               Map row = (Map)object;
               
               MerchandiseIncomeDetailBean merchandiseIncomeDetailBean=new MerchandiseIncomeDetailBean();
               merchandiseIncomeDetailBean.setAmount(((BigDecimal)row.get("amount")).doubleValue());
               merchandiseIncomeDetailBean.setCostPrice(((BigDecimal)row.get("cost_price")).doubleValue());
               merchandiseIncomeDetailBean.setDateCreation((Date)row.get("date_creation"));
               
               ProductBean product=new ProductBean();
               product.setId((Integer)row.get("id_product"));
               merchandiseIncomeDetailBean.setProduct(product);
               
              ListMerchandiseIncomeDetail.add(merchandiseIncomeDetailBean);
          }

          
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            ListMerchandiseIncomeDetail=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return ListMerchandiseIncomeDetail;
    }

    @Override
    public int deleteMerchandiseIncomeBean(int id_merchandise_income) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_merchandise_income from inventory.delete_merchandise_income( :id_merchandise_income );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_merchandise_income", id_merchandise_income);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_merchandise_income");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }  

    @Override
    public int saveTransfer(TransferBean transferBean) {
        int rpta = -1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sql = "INSERT INTO inventory.transfer( id_location, amount, username, date_creation) VALUES ( :id_location, :amount, :username, :date_creation) RETURNING id_transfer;";
            //System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("id_location", transferBean.getLocation().getId_location());
            query.setParameter("amount", transferBean.getAmount());
            query.setParameter("username", transferBean.getUsername());
            query.setParameter("date_creation", transferBean.getDateCreation());
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            int id_transfer=(Integer) ((HashMap)data.get(0)).get("id_transfer");       
            //System.out.println("list::::>"+rpta);
  
            List<TransferDetailBean> detail=transferBean.getDetails();
            int rsd;
            int rss;
            for (TransferDetailBean p : detail) {
                
                String sqld="INSERT INTO inventory.transfer_detail(id_transfer, id_product, amount, sell_price, date_creation) VALUES ( :id_transfer, :id_product, :amount, :sell_price , :date_creation );";
                SQLQuery queryd = session.createSQLQuery(sqld);
                queryd.setParameter("id_transfer",id_transfer);
                queryd.setParameter("id_product", p.getProduct().getId());
                queryd.setParameter("amount", p.getAmount());
                queryd.setParameter("sell_price", p.getSell_price());
                queryd.setParameter("date_creation",transferBean.getDateCreation());            
                rsd=queryd.executeUpdate();   
                //System.out.println("res:::"+rsd);
                
                //update inventory.products set stock=? where product_id=0
                String sqls="update inventory.products set stock=(stock - :pamount) where product_id=:product_id ;";
                SQLQuery querys = session.createSQLQuery(sqls);
                querys.setParameter("pamount", p.getAmount());
                querys.setParameter("product_id", p.getProduct().getId());
                rss=querys.executeUpdate();
                //System.out.println("rss:::"+rss);
            }
            rpta=0;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            //rpta=1;
            e.printStackTrace();
        } finally {
            //session.close(); 
            tx.commit();
        }
        return rpta;
    }

    @Override
    public ArrayList<TableTransferBean> get_list_transferBean() {
        Transaction tx = null;
        ArrayList<TableTransferBean> litsTransferBean =new ArrayList<TableTransferBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_transfer, id_location, name_location, amount, username, date_creation, registration_date FROM inventory.view_transfer;";
            SQLQuery query = session.createSQLQuery(sql);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableTransferBean transfer= new TableTransferBean();
            transfer.setId((Integer)row.get("id_transfer"));
            //purchaseOrder.getSupplier().setId_supplier((Integer) row.get("id_supplier"));
            transfer.setId_location((Integer)row.get("id_location"));
            transfer.setName_location((String)row.get("name_location"));
            transfer.setAmount(((BigDecimal)row.get("amount")).doubleValue());
            transfer.setUsername((String)row.get("username"));
            transfer.setDateCreation((Date) row.get("date_creation"));
            transfer.setRegistration_date((Date) row.get("registration_date"));
            
            litsTransferBean.add(transfer);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsTransferBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsTransferBean;
    }

    @Override
    public TransferBean get_TransferBean(int id_transfer) {
        Transaction tx = null;
      TransferBean transferBean=new TransferBean();
      
        //System.out.println("get_TransferBean:"+id_transfer);
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_transfer, id_location, amount, username, date_creation, registration_date, id_company FROM inventory.transfer where id_transfer = :id ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id",id_transfer);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          
          LocationBean lc=new LocationBean();
          lc.setId_location((Integer)data.get("id_location"));
          
          transferBean.setId((Integer)data.get("id_location"));
          transferBean.setUsername((String) data.get("username"));
          transferBean.setDateCreation((Date)data.get("date_creation"));
          transferBean.setAmount(((BigDecimal)data.get("amount")).doubleValue());
          transferBean.setLocation(lc);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return transferBean;
    }

    @Override
    public ArrayList<TransferDetailBean> get_list_transferDetailBean(int id_transfer) {
        Transaction tx = null;
        ArrayList<TransferDetailBean> ListTransferDetail=new ArrayList<TransferDetailBean>();
        try{
            tx = session.beginTransaction();

            
          
           String sqld="SELECT id_transfer, id_product, amount, sell_price, date_creation FROM inventory.transfer_detail where id_transfer= :id ;";
           SQLQuery queryd = session.createSQLQuery(sqld);
           queryd.setParameter("id", id_transfer);
           
           queryd.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
           List dataList = queryd.list();
           
           //System.out.println("List data::"+dataList.size());
           
           for (Object object : dataList) {
               Map row = (Map)object;
               
               TransferDetailBean transferDetailBean=new TransferDetailBean();
               transferDetailBean.setAmount(((BigDecimal)row.get("amount")).doubleValue());
               transferDetailBean.setSell_price(((BigDecimal)row.get("sell_price")).doubleValue());
               transferDetailBean.setDateCreation((Date)row.get("date_creation"));
               
               ProductBean product=new ProductBean();
               product.setId((Integer)row.get("id_product"));
               transferDetailBean.setProduct(product);
               
              ListTransferDetail.add(transferDetailBean);
          }

          
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            ListTransferDetail=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return ListTransferDetail;
    }

    @Override
    public int deleteTransferBean(int id_transfer) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_transfer from inventory.delete_transfer( :id_transfer );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_transfer", id_transfer);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_transfer");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public int validateIncomePO(String nro_document) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "SELECT CAST( count(*) AS int)  FROM inventory.merchandise_income where nro_document= :pnro_document ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("pnro_document", nro_document);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("count");       
          //System.out.println("list::::>"+rpta);
          
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         //rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        return rpta;
    }

    @Override
    public Map<String, Object> get_list_dataTable_Product(int offset, int limit) {
        
        //has
         Map<String, Object> dataTable = new HashMap<String, Object>();
        int count=0;
         
        Transaction tx = null;
        ArrayList<TableProductBean> litsProductBean =new ArrayList<TableProductBean>();
        try{
            tx = session.beginTransaction();
            
            String sql2 = "SELECT CAST( count(*) AS int) FROM inventory.view_products;";
            SQLQuery query2 = session.createSQLQuery(sql2);
            query2.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data2 = query2.list();
            count=(Integer) ((HashMap)data2.get(0)).get("count");            

            String sql = "SELECT product_id, code, name, description, unit_cost,registration_date,image_name,stock,alert_stock, id_category FROM inventory.view_products OFFSET :poffset LIMIT :plimit ;";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("poffset", offset);
            query.setParameter("plimit", limit);
            //SELECT count(*) FROM inventory.products;
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            //System.out.println("List data::"+data.size());
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            TableProductBean productBean= new TableProductBean();
            productBean.setId((Integer)row.get("product_id"));
            productBean.setCode((String) row.get("code"));
            productBean.setName((String)row.get("name"));
            productBean.setDescription((String)row.get("description"));
            productBean.setUnit_cost(((BigDecimal)row.get("unit_cost")).doubleValue());
            productBean.setRegistration_date((Date) row.get("registration_date"));
            productBean.setImage_name((String)row.get("image_name"));
            productBean.setStock((Integer)row.get("stock"));
            productBean.setAlert_stock((Integer)row.get("alert_stock"));
            productBean.setId_category((Integer)row.get("id_category"));
            
            litsProductBean.add(productBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsProductBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        dataTable.put("result",litsProductBean );
        dataTable.put("count",count );
        
        return dataTable;
    }
    
}
