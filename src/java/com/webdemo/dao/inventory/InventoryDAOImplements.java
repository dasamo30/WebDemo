/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao.inventory;

import com.google.gson.Gson;
import com.webdemo.beans.PerfilBean;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableProductBean;
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
         String sql = "select register_product from inventory.register_product( :code , :name , :description , :unit_cost, :image_name );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("code", product.getCode());
         query.setParameter("name", product.getName());
         query.setParameter("description", product.getDescription());
         query.setParameter("unit_cost", product.getUnit_cost());
         query.setParameter("image_name", product.getImage_name(), Hibernate.STRING);
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

            String sql = "SELECT product_id, code, name, description, unit_cost, registration_date,image_name FROM inventory.view_products;";
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
         String sql = "SELECT product_id, code, name, description, unit_cost,registration_date,image_name FROM inventory.view_products where product_id= :product_id ;";
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
    
}
