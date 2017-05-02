/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao;

import com.google.gson.Gson;
import com.webdemo.beans.InfoUserBean;
import com.webdemo.beans.MenuPerfil;
import com.webdemo.beans.PerfilBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

/**
 *
 * @author dasamo
 */
public class AcessoDAOImplements extends GenericDAO implements IAccesosDAO  {
    private  Gson gson = new Gson(); 
    @Override
    public int validadUsuario(String usr, String pass) {
       // consultaFuncion(usr);
      //Session session = HibernateUtil.getSessionFactory().openSession();
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select valida_usuario from accesos.valida_usuario( :usuario , :clave )";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("usuario", usr);
         query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("valida_usuario");       
          System.out.println("list::::>"+rpta);
          
         /*for(Object object : data)
         {
            Map row = (Map)object;
            System.out.println("valida_usuario: " + row.get("valida_usuario")); 
            //System.out.println(", Salary: " + row.get("salary")); 
         }*/
         //tx.commit();
      }catch (HibernateException e) {
         if (tx!=null){
             tx.rollback();
         }
         rpta=1;
         e.printStackTrace(); 
      }finally {
         //session.close(); 
          tx.commit();
      }
        /*
        List data = consultaFuncion("select valida_usuario from  accesos.valida_usuario(?,");
       
        System.out.println("gson.toJson:::"+gson.toJson(data));
        
        for (Object object : data) {
            //Map row = (Map)object;
            System.out.println("valida_usuario: "+object.toString()); 
            //System.out.println(", Salary: " + row.get("salary")); 
        }*/
       
        
        System.out.println("AcessoDAOImplements extends GenericDAO implements IAccesosDAO");
        return rpta; 
    }

    @Override
    public InfoUserBean getUserInfo(String usuario) {
        
        Transaction tx = null;
        InfoUserBean infoUserBean= new InfoUserBean();
        try{
            tx = session.beginTransaction();

            String sql = "select id_usuario,login,clave,estado,fecha_reg,nro_sesion,sesion_activa,nombres,apellidos,foto,genero,dni,correo,id_perfil,perfil "
                    + "from accesos.info_user( :usuario )";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("usuario", usuario);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            
            
            HashMap map=(HashMap)data.get(0);
            //System.out.println("map fecha::"+map.get("fecha").getClass());
            
            infoUserBean.setId_usuario((Integer)map.get("id_usuario"));
            infoUserBean.setLogin((String)map.get("login"));
            infoUserBean.setClave((String)map.get("clave"));
            infoUserBean.setEstado((Integer)map.get("estado"));
            infoUserBean.setFecha_reg((Date)map.get("fecha_reg"));
            infoUserBean.setNro_sesion((Integer)map.get("nro_sesion"));
            infoUserBean.setSesion_activa((Integer)map.get("sesion_activa"));
            infoUserBean.setNombres((String)map.get("nombres"));
            infoUserBean.setApellidos((String)map.get("apellidos"));
            infoUserBean.setFoto((String)map.get("foto"));
            infoUserBean.setGenero((String)map.get("genero"));
            infoUserBean.setDni((String)map.get("dni"));
            infoUserBean.setCorreo((String)map.get("correo"));
            infoUserBean.setId_perfil((Integer)map.get("id_perfil"));
            infoUserBean.setPerfil((String)map.get("perfil"));
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            infoUserBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return infoUserBean;
    }
    
    
    /*
    
    */

    @Override
    public ArrayList<MenuPerfil> get_menus_accesos_perfiles(int padre, int idPerfil) {
        
    Transaction tx = null;
        
        ArrayList<MenuPerfil> lmenus =new ArrayList<MenuPerfil>();
        try{
            tx = session.beginTransaction();

            String sql = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,b.icono, b.orden "
                    + "from accesos.permisos a inner join accesos.menu b on b.id_menu=a.id_menu and id_perfil= :idPerfil where  padre= :padre order by b.orden";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            
            
        for(Object object : data)
         {
            Map row = (Map)object;
            MenuPerfil prf=new MenuPerfil();
            prf.setIdPerfil((Integer)row.get("id_perfil"));
            prf.setIdMenu((Integer)row.get("id_menu"));
            prf.setPadre((Integer)row.get("padre"));
            prf.setNivel((Integer)row.get("nivel"));
            prf.setNombreMenu((String) row.get("nombre_menu"));
            prf.setUrl((String) row.get("url"));
            prf.setEstado((Integer)row.get("estado"));
            prf.setIcono((String) row.get("icono"));
            prf.setOrden((Short)row.get("orden"));
            
            lmenus.add(prf);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace(); 
        }finally {
         tx.commit();
        }
        
        return lmenus;    
       
    }

    @Override
    public ArrayList<MenuPerfil> get_permisos_menus_accesos(int padre, int idPerfil) {
        Transaction tx = null;
        
        ArrayList<MenuPerfil> lmenus =new ArrayList<MenuPerfil>();
        try{
            tx = session.beginTransaction();

            String sql = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,(select count(m.*)"
                    + "from accesos.menu m where m.padre=b.id_menu)::integer ,b.icono,b.orden "
                    + "from accesos.permisos a right join accesos.menu b on b.id_menu=a.id_menu and id_perfil= :idPerfil where  padre= :padre order by b.orden";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            
            
          for(Object object : data)
         {
            Map row = (Map)object;
            MenuPerfil prf=new MenuPerfil();
            prf.setIdPerfil((Integer)row.get("id_perfil"));
            prf.setIdMenu((Integer)row.get("id_menu"));
            prf.setPadre((Integer)row.get("padre"));
            prf.setNivel((Integer)row.get("nivel"));
            prf.setNombreMenu((String) row.get("nombre_menu"));
            prf.setUrl((String) row.get("url"));
            prf.setEstado((Integer)row.get("estado"));
            prf.setNroh((Integer)row.get("count"));
            prf.setIcono((String) row.get("icono"));
            prf.setOrden((Short)row.get("orden"));
            lmenus.add(prf);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace(); 
        }finally {
           
         tx.commit();
        }
        
        return lmenus;
    }

    @Override
    public ArrayList<InfoUserBean> get_list_usuarios() {
        Transaction tx = null;
        ArrayList<InfoUserBean> litsInfoUserBean =new ArrayList<InfoUserBean>();
        try{
            tx = session.beginTransaction();

            String sql = "select id_usuario, login, clave, estado, fecha_reg, "
                    + "id_perfil, nro_sesion, sesion_activa, perfil, tiempo_sesion, "
                    + "nombres, apellidos, foto, genero, dni, correo from accesos.vista_usuarios";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            InfoUserBean infoUserBean= new InfoUserBean();
            infoUserBean.setId_usuario((Integer)row.get("id_usuario"));
            infoUserBean.setLogin((String) row.get("login"));
            infoUserBean.setClave((String) row.get("clave"));
            infoUserBean.setEstado((Integer)row.get("estado"));
            infoUserBean.setFecha_reg((Date) row.get("fecha_reg"));
            infoUserBean.setId_perfil((Integer)row.get("id_perfil"));
            infoUserBean.setNro_sesion((Integer)row.get("nro_sesion"));
            infoUserBean.setSesion_activa((Integer)row.get("sesion_activa"));
            infoUserBean.setPerfil((String) row.get("perfil"));
            infoUserBean.setNombres((String) row.get("nombres"));
            infoUserBean.setApellidos((String) row.get("apellidos"));
            infoUserBean.setFoto((String) row.get("foto"));
            infoUserBean.setGenero((String) row.get("genero"));
            infoUserBean.setDni((String) row.get("dni"));
            infoUserBean.setCorreo((String) row.get("correo"));
            
            litsInfoUserBean.add(infoUserBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsInfoUserBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsInfoUserBean;
    }

    @Override
    public int registraUsuarios(InfoUserBean usuario) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select inserta_usuario from accesos.inserta_usuario( :usuario,'123456',1,'2017-03-10 15:23:25', 1, 1, 1,'pablo','morgan ','default,jpg','m','45876547', 'pmorgan@gmail.com')";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("usuario", usuario.getLogin());
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("inserta_usuario");       
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
    public ArrayList<PerfilBean> get_list_perfiles() {
        Transaction tx = null;
        ArrayList<PerfilBean> litsPerfilBean =new ArrayList<PerfilBean>();
        try{
            tx = session.beginTransaction();

            String sql = "SELECT id_perfil, nombre, estado, fecha, tiempo_sesion FROM accesos.view_perfiles;";
            SQLQuery query = session.createSQLQuery(sql);
            /*query.setParameter("padre", padre);
            query.setParameter("idPerfil", idPerfil);*/

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            
            
            
          for(Object object : data)
         {
            Map row = (Map)object;
            
            PerfilBean perfilBean= new PerfilBean();
            perfilBean.setId_perfil((Integer)row.get("id_perfil"));
            perfilBean.setNombre((String) row.get("nombre"));
            perfilBean.setEstado((Integer)row.get("estado"));
            perfilBean.setFecha((Date) row.get("fecha"));
            perfilBean.setTiempo_sesion((Integer)row.get("tiempo_sesion"));
            
            
            litsPerfilBean.add(perfilBean);
         }
            
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            litsPerfilBean=null;
            e.printStackTrace(); 
        }finally {
         //session.close();
            tx.commit();
        }
        
        return litsPerfilBean;
    }
}