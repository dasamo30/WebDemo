/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

import com.google.gson.Gson;
import com.webdemo.beans.inventory.CategoryBean;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.PurchaseOrderBean;
import com.webdemo.beans.inventory.PurchaseOrderDetailBean;
import com.webdemo.beans.inventory.SupplierBean;
import com.webdemo.beans.inventory.TableCategoryBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.beans.inventory.TablePurchaseOrder;
import com.webdemo.beans.inventory.TableSupplierBean;
import com.webdemo.dao.GenericDAO;
import java.math.BigDecimal;
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
          System.out.println("list::::>"+rpta);
          
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
            
            System.out.println("List data::"+data.size());
            
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
          System.out.println("list::::>"+rpta);
          
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
        return productBean;
    }

    @Override
    public int ModifyProduct(ProductBean product) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modify_product from inventory.modify_product( :product_id , :code , :name , :description , :unit_cost, :image_name );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("product_id", product.getId());
         query.setParameter("code", product.getCode());
         query.setParameter("name", product.getName());
         query.setParameter("description", product.getDescription());
         query.setParameter("unit_cost", product.getUnit_cost());
         query.setParameter("image_name", product.getImage_name(), Hibernate.STRING);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modify_product");       
          System.out.println("list::::>"+rpta);
          
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
          System.out.println("sql::"+sql);
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("name_location", location.getName_location());
         query.setParameter("date_creation", location.getDate_creation());
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("register_location");       
          System.out.println("list::::>"+rpta);
          
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
          System.out.println("list::::>"+rpta);
          
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
          System.out.println("list::::>"+rpta);
          
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
            
            System.out.println("List data::"+data.size());
            
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
            System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("name_category", category.getName_category());
            query.setParameter("date_creation", category.getDate_creation());
            //query.setParameter("clave", pass);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            rpta = (Integer) ((HashMap) data.get(0)).get("register_category");
            System.out.println("list::::>" + rpta);

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
          System.out.println("list::::>"+rpta);
          
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
          System.out.println("list::::>"+rpta);
          
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
            
            System.out.println("List data::"+data.size());
            
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
            System.out.println("sql::" + sql);
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
            System.out.println("list::::>" + rpta);

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
          System.out.println("list::::>"+rpta);
          
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
          System.out.println("list::::>"+rpta);
          
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
            
            System.out.println("List data::"+data.size());
            
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
            
            System.out.println("List data::"+data.size());
            
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
            System.out.println("sql::" + sql);
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("id_supplier", purchaseOrderBean.getSupplier().getId_supplier());
            query.setParameter("amount", purchaseOrderBean.getAmount());
            query.setParameter("username", purchaseOrderBean.getUsername());
            query.setParameter("date_creation", purchaseOrderBean.getDateCreation());
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            int id_purchase_order=(Integer) ((HashMap)data.get(0)).get("id_purchase_order");       
            System.out.println("list::::>"+rpta);
            
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
                System.out.println("res:::"+rsd);
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
            
            System.out.println("List data::"+data.size());
            
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
      
        System.out.println("get_purchaseOrderBean_id_purchase_order:"+id_purchase_order);
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_purchase_order, id_supplier, amount, username, date_creation, registration_date FROM inventory.purchase_order where id_purchase_order = :id_purchase_order ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_purchase_order",id_purchase_order);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          
          purchaseOrderBean.setId((Integer)data.get("id_purchase_order"));
          purchaseOrderBean.setUsername((String) data.get("username"));
          purchaseOrderBean.setDateCreation((Date)data.get("date_creation"));
          purchaseOrderBean.setAmount(((BigDecimal)data.get("amount")).doubleValue());
          
          /*
          ArrayList<PurchaseOrderDetailBean> details=new ArrayList<PurchaseOrderDetailBean>();
          
           String sqld="SELECT id_purchase_order, id_product, amount, cost_price, date_creation FROM inventory.purchase_order_detail where id_purchase_order= :id ;";
           SQLQuery queryd = session.createSQLQuery(sqld);
           queryd.setParameter("id", id_purchase_order);
           
           queryd.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
           List dataList = queryd.list();
           
           System.out.println("List data::"+dataList.size());
           
           for (Object object : dataList) {
               Map row = (Map)object;
               
               PurchaseOrderDetailBean purchaseOrderDetailBean=new PurchaseOrderDetailBean();
               purchaseOrderDetailBean.setAmount(((BigDecimal)row.get("amount")).doubleValue());
               purchaseOrderDetailBean.setCostPrice(((BigDecimal)row.get("cost_price")).doubleValue());
               purchaseOrderDetailBean.setDateCreation((Date)row.get("date_creation"));
               
               ProductBean product=this.get_Product((Integer)row.get("id_product"));
               purchaseOrderDetailBean.setProduct(product);
               
              details.add(purchaseOrderDetailBean);
          }

          purchaseOrderBean.setDetails(details);*/
                  
          
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
           
           System.out.println("List data::"+dataList.size());
           
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
          System.out.println("list::::>"+rpta);
          
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

    
    
}
