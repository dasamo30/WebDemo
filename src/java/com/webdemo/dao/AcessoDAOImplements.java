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
import com.webdemo.beans.TableUsuarioBean;
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
public class AcessoDAOImplements extends GenericDAO implements IAccesosDAO  {
    private  Gson gson = new Gson(); 
    @Override
    public int validadUsuario(InfoUserBean userBean) {
       // consultaFuncion(usr);
      //Session session = HibernateUtil.getSessionFactory().openSession();
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select valida_usuario from accesos.valida_usuario( :usuario , :clave )";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("usuario", userBean.getLogin());
         query.setParameter("clave", userBean.getClave());
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("valida_usuario");       
          ////System.out.println("list::::>"+rpta);
          
         /*for(Object object : data)
         {
            Map row = (Map)object;
            //System.out.println("valida_usuario: " + row.get("valida_usuario")); 
            ////System.out.println(", Salary: " + row.get("salary")); 
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
       
        //System.out.println("gson.toJson:::"+gson.toJson(data));
        
        for (Object object : data) {
            //Map row = (Map)object;
            //System.out.println("valida_usuario: "+object.toString()); 
            ////System.out.println(", Salary: " + row.get("salary")); 
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

            String sql = "select id_usuario,login,clave,estado,fecha_reg,nro_sesion,sesion_activa,nombres,apellidos,foto,genero,dni,correo,id_perfil,perfil,pestado "
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
            infoUserBean.setPestado((Integer)map.get("pestado"));
            
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
    public ArrayList<TableUsuarioBean> get_list_usuarios() {
        Transaction tx = null;
        ArrayList<TableUsuarioBean> litsInfoUserBean =new ArrayList<TableUsuarioBean>();
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
            
            TableUsuarioBean infoUserBean= new TableUsuarioBean();
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
        
        java.util.Date fecha = new java.util.Date(); 
        usuario.setFecha_reg(fecha);
        usuario.setEstado(1);
        usuario.setNro_sesion(1);
        usuario.setSesion_activa(1);
        usuario.setFoto("user.png");
        
        //System.out.println("DAO:"+usuario.toString());
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select inserta_usuario from accesos.inserta_usuario( :usuario, :clave, :estado, :fecha_reg, "
                 + ":id_perfil, :nro_sesion, :sesion_activa, :nombres, :apellidos, :foto, :genero, :dni, :correo)";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("usuario", usuario.getLogin());
         query.setParameter("clave", usuario.getClave());
         query.setParameter("estado", usuario.getEstado());
         query.setParameter("fecha_reg", usuario.getFecha_reg());
         query.setParameter("id_perfil", usuario.getId_perfil());
         query.setParameter("nro_sesion", usuario.getNro_sesion());
         query.setParameter("sesion_activa", usuario.getSesion_activa());
         query.setParameter("nombres", usuario.getNombres());
         query.setParameter("apellidos", usuario.getApellidos());
         query.setParameter("foto", usuario.getFoto());
         query.setParameter("genero", usuario.getGenero());
         query.setParameter("dni", usuario.getDni());
         query.setParameter("correo", usuario.getCorreo());
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("inserta_usuario");       
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
    synchronized public ArrayList<PerfilBean> get_list_perfiles() {
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

    @Override
    public int registraPerfil(PerfilBean perfil) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select inserta_perfil from accesos.inserta_perfil( :nombre, :estado, :fecha, :tiempo_sesion);";
          //System.out.println("sql::"+sql);
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("nombre", perfil.getNombre());
         query.setParameter("estado", perfil.getEstado());
         query.setParameter("fecha", perfil.getFecha());
         query.setParameter("tiempo_sesion", perfil.getTiempo_sesion());
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("inserta_perfil");       
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
    public PerfilBean get_perfil(int idPerfil) {
        Transaction tx = null;
      PerfilBean perfilBean=new PerfilBean();
      
      try{
         tx = session.beginTransaction();
         String sql = "SELECT id_perfil, nombre, estado, fecha, tiempo_sesion FROM accesos.perfiles where id_perfil= :id_perfil ;";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_perfil",idPerfil);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
          perfilBean.setId_perfil((Integer) data.get("id_perfil"));
          perfilBean.setNombre((String) data.get("nombre"));
          perfilBean.setEstado((Integer)data.get("estado"));
          perfilBean.setFecha((Date)data.get("fecha"));
          perfilBean.setTiempo_sesion((Integer)data.get("tiempo_sesion"));
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
        return perfilBean;
    }

    @Override
    public int modificarPerfil(PerfilBean perfil) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modifica_perfil from accesos.modifica_perfil( :id_perfil, :nombre, :estado, :tiempo_sesion );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_perfil", perfil.getId_perfil());
         query.setParameter("nombre", perfil.getNombre());
         query.setParameter("estado", perfil.getEstado());
         query.setParameter("tiempo_sesion", perfil.getTiempo_sesion());
         
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modifica_perfil");       
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
    public int eliminaUsuario(int idUsuario) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select delete_usuario from accesos.delete_usuario( :idUsuario );";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("idUsuario", idUsuario);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("delete_usuario");       
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
    public int modificarUsuario(InfoUserBean usuario) {

        usuario.setNro_sesion(1);
        usuario.setSesion_activa(1);
        usuario.setFoto("user.png");
        
        //System.out.println("DAO:"+usuario.toString());
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select modifica_usuario from accesos.modifica_usuario( :id_usuario, :usuario, :estado, "
                 + ":id_perfil, :nro_sesion, :sesion_activa, :nombres, :apellidos, :foto, :genero, :dni, :correo)";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_usuario", usuario.getId_usuario());
         query.setParameter("usuario", usuario.getLogin());
         //query.setParameter("clave", usuario.getClave());
         query.setParameter("estado", usuario.getEstado());
         query.setParameter("id_perfil", usuario.getId_perfil());
         query.setParameter("nro_sesion", usuario.getNro_sesion());
         query.setParameter("sesion_activa", usuario.getSesion_activa());
         query.setParameter("nombres", usuario.getNombres());
         query.setParameter("apellidos", usuario.getApellidos());
         query.setParameter("foto", usuario.getFoto());
         query.setParameter("genero", usuario.getGenero());
         query.setParameter("dni", usuario.getDni());
         query.setParameter("correo", usuario.getCorreo());
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("modifica_usuario");       
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
    public InfoUserBean get_usuario(int idUsuario) {
        
        Transaction tx = null;
      InfoUserBean userBean=new InfoUserBean();
      
      try{
         tx = session.beginTransaction();
         
         String sql = "select id_usuario, login, clave, estado, fecha_reg, "
                    + "id_perfil, nro_sesion, sesion_activa, perfil, tiempo_sesion, "
                    + "nombres, apellidos, foto, genero, dni, correo from accesos.vista_usuarios where id_usuario= :id_usuario";
         
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("id_usuario",idUsuario);
         //query.setParameter("clave", pass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          HashMap data = (HashMap) query.list().get(0);
          //HashMap beanData=(HashMap) data.get(0);   
            userBean.setId_usuario((Integer) data.get("id_usuario"));
            userBean.setLogin((String) data.get("login"));
            userBean.setClave((String) data.get("clave"));
            userBean.setEstado((Integer)data.get("estado"));
            userBean.setFecha_reg((Date)data.get("fecha_reg"));
            userBean.setId_perfil((Integer)data.get("id_perfil"));
            userBean.setNro_sesion((Integer)data.get("nro_sesion"));
            userBean.setSesion_activa((Integer)data.get("sesion_activa"));
            userBean.setPerfil((String) data.get("perfil"));
            userBean.setNombres((String) data.get("nombres"));
            userBean.setApellidos((String) data.get("apellidos"));
            userBean.setFoto((String) data.get("foto"));
            userBean.setGenero((String) data.get("genero"));
            userBean.setDni((String) data.get("dni"));
            userBean.setCorreo((String) data.get("correo"));
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
        return userBean;
    }

    @Override
    synchronized public  ArrayList<MenuPerfil> get_menus_accesos_perfil(int idPerfil) {
        Transaction tx = null;
        
        ArrayList<MenuPerfil> lmenus =new ArrayList<MenuPerfil>();
        try{
            tx = session.beginTransaction();

            String sql = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,cast((select count(m.*) from accesos.menu m where m.padre=b.id_menu) as int) as hijos ,b.icono,b.orden \n" +
"from accesos.permisos a right join accesos.menu b on b.id_menu=a.id_menu and id_perfil= :idPerfil order by b.nivel,b.orden";
            SQLQuery query = session.createSQLQuery(sql);
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
            prf.setNroh((Integer)row.get("hijos"));
            prf.setIcono((String) row.get("icono"));
            prf.setOrden((Short)row.get("orden"));
            lmenus.add(prf);
         }
          tx.commit();   
        }catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        
        return lmenus;
    }

    @Override
    public int cambioPassword(int idUsuario, String passact, String newpass) {
      int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select cambio_password from accesos.cambio_password( :idUsuario , :passact , :newpass )";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("idUsuario", idUsuario);
         query.setParameter("passact", passact);
         query.setParameter("newpass", newpass);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("cambio_password");       
          ////System.out.println("list::::>"+rpta);
          
         tx.commit();
        }catch (HibernateException e) {
            if (tx!=null){
             tx.rollback();
            }
            rpta=1;
            e.printStackTrace(); 
      }
      return rpta; 
    }

    @Override
    public int asignaPermiso(boolean opcion, int id_perfil, int id_menu) {
        int rpta =-1;  
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "select asigna_permiso from accesos.asigna_permiso( :opcion , :id_perfil, :id_menu)";
         SQLQuery query = session.createSQLQuery(sql);
         query.setParameter("opcion", opcion, Hibernate.BOOLEAN);
         query.setParameter("id_perfil", id_perfil);
         query.setParameter("id_menu", id_menu);
         
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

          rpta=(Integer) ((HashMap)data.get(0)).get("asigna_permiso");       
          ////System.out.println("list::::>"+rpta);
          
         tx.commit();
        }catch (HibernateException e) {
            if (tx!=null){
             tx.rollback();
            }
            rpta=1;
            e.printStackTrace(); 
      }
      return rpta; 
    }
}
