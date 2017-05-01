/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdemo.dao;


import com.webdemo.dao.util.HibernateUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author diegobenavides
 */

/**
 *
 * @author diegobenavides
 */
public abstract class GenericDAO  {

    private final static Logger log = Logger.getLogger("GenericDAO");

    //private final Class<T> claseDePersistencia;
    protected Session session;
   /* private Transaction tx;
    private PreparedStatement stm;
    public Statement stat;
    private ResultSet rest;
    private Connection con;
    private CallableStatement proc;
    public EntityManager em;*/
   
    @SuppressWarnings("unchecked")
    public GenericDAO() {

        // this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
         this.session = HibernateUtil.getSessionFactory().openSession();
    }
/*
   public List consultaFuncion(String sql) {
            
        PropertyConfigurator.configure("/opt/apps/etc/log4j.properties");
       // ArrayList<String[]> listData = new ArrayList<String[]>();
            //session = HibernateUtil.getSessionFactory().openSession();
            //session = getSessionFactory().getCurrentSession();
        List rows = null;
        try {
            
           // String sql = "SELECT idalumno, fnacimiento FROM alumno where dni='00000000C'";
            
            // emm = HibernateUtil.getSessionFactory();
           // session = emm.openSession();
            //session.getSessionFactory().getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            rows = query.list();
             
            //iniciaOperacion();
            //em.getTransaction().begin();
            //tx = session.beginTransaction();
           // tx.begin();
           // con = session.connection();
            //stat = con.createStatement();

            //rest = stat.executeQuery(query);

            //em.getTransaction().commit();
            /*if (!tx.wasCommitted()) {
                tx.commit();
            } else {
                tx.rollback();
            }*/

           // metadata = rest.getMetaData();
           
            
            //int numeroColumnas = metadata.getColumnCount();

           /* while (rest.next()) {

                String[] fila = new String[numeroColumnas];
                for (int i = 1; i <= numeroColumnas; i++) {
                    //if(rest.getString(i) == null){break;}
                    if (rest.getString(i) == null || "".equals(rest.getString(i))) {
                        fila[i - 1] = "0";
                    } else {
                        fila[i - 1] = rest.getString(i);
                    }
                        //System.out.println("nuuuu: "+rest.getString(i));

                }
                listData.add(fila);

            }*
            
            log.trace("Conexion Hibernate-Posgrest query: " + sql);
        } catch (HibernateException he) {
            System.out.println("===========> H" + he + " " + sql);
            log.error(he + " " + sql);
            /*if(em.getTransaction().isActive()) {//moved to top
             em.getTransaction().rollback();
             }*
           // tx.rollback();
        } catch (RuntimeException re) {
            System.out.println("===========> R" + re + " " + sql);
            log.error("persist failed", re);
            /*if(em.getTransaction().isActive()) {//moved to top
             em.getTransaction().rollback();
             }*
           // tx.rollback();
            throw re;
        } finally {
            /*if(em.isOpen()){
             if (em.getTransaction().isActive())
             em.getTransaction().rollback();
             }*
        }

       return rows;

    }*/
/*
    public ArrayList<String[]> consultaFuncion2(String query) {
        PropertyConfigurator.configure("/opt/conciliacion/connection/log4j.properties");
        ArrayList<String[]> listData = new ArrayList<String[]>();

        try {

            iniciaOperacion();
            //em.getTransaction().begin();
            tx = session.beginTransaction();
            tx.begin();
            con = session.connection();
            stat = con.createStatement();

            rest = stat.executeQuery(query);

            //em.getTransaction().commit();
            if (!tx.wasCommitted()) {
                tx.commit();
            } else {
                tx.rollback();
            }

            ResultSetMetaData metadata = rest.getMetaData();
            int numeroColumnas = metadata.getColumnCount();

            while (rest.next()) {

                String[] fila = new String[numeroColumnas];
                for (int i = 1; i <= numeroColumnas; i++) {

                    fila[i - 1] = rest.getString(i);

                }
                listData.add(fila);

            }

            log.trace("Conexion JPA-Hibernate-Posgrest query: " + query);
        } catch (SQLException ex) {
            System.out.println("===========> S" + ex + " " + query);
            log.error(ex + " " + query);
            //tx.rollback();
        } catch (HibernateException he) {
            System.out.println("===========> H" + he + " " + query);
            log.error(he + " " + query);

            tx.rollback();
        } catch (RuntimeException re) {
            System.out.println("===========> R" + re + " " + query);
            log.error("persist failed", re);

            tx.rollback();
            throw re;
        } finally {

        }

        return listData;

    }/*
/*
        public int updateFuncion(String query) {
        int r = -1;
        try {

            iniciaOperacion();
            //em.getTransaction().begin();
            tx = session.beginTransaction();
            tx.begin();
            con = session.connection();
            stat = con.createStatement();
            r = stat.executeUpdate(query);

            //em.getTransaction().commit();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
            log.trace("Conexion Hibernate-Posgrest query: " + query);
        } catch (SQLException ex) {
            System.out.println("===========> S" + ex + " " + query);
            log.error(ex + " " + query);
         
        } catch (HibernateException he) {
            System.out.println("===========> H" + he + " " + query);
            log.error(he + " " + query);
  
            tx.rollback();
        } catch (RuntimeException re) {
            System.out.println("===========> R" + re + " " + query);
        
            tx.rollback();
            throw re;

        } finally {
            //if(em.isOpen()){
           //  if (em.getTransaction().isActive())
            // em.getTransaction().rollback();
             //}
            //tx.rollback();
        }
        return r;

    }
*/
    /*
        public ArrayList<String[]> FRCursor(String query, int tipo) {
        ArrayList<String[]> listData = new ArrayList<String[]>();

        try {

            iniciaOperacion();
            //em.getTransaction().begin();
            tx = session.beginTransaction();
            tx.begin();
            con = session.connection();
            con.setAutoCommit(false);
            proc = con.prepareCall(query);
            proc.registerOutParameter(1, tipo);
            proc.execute();

            //em.getTransaction().commit();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
            rest = (ResultSet) proc.getObject(1);

            ResultSetMetaData metadata = rest.getMetaData();
            int numeroColumnas = metadata.getColumnCount();

            while (rest.next()) {

                String[] fila = new String[numeroColumnas];
                for (int i = 1; i <= numeroColumnas; i++) {
                    //if(rest.getString(i) == null){break;}
                    if (rest.getString(i) == null || "".equals(rest.getString(i))) {
                        fila[i - 1] = "0";
                    } else {
                        fila[i - 1] = rest.getString(i);
                    }
                        //System.out.println("nuuuu: "+rest.getString(i));

                }
                listData.add(fila);

            }

            log.trace("Conexion JPA-Hibernate-Posgrest query: " + query);
        } catch (SQLException ex) {
            System.out.println("===========> S" + ex + " " + query);
            log.error(ex + " " + query);

        } catch (HibernateException he) {
            System.out.println("===========> H" + he + " " + query);
            log.error(he + " " + query);

            tx.rollback();
        } catch (RuntimeException re) {
            System.out.println("===========> R" + re + " " + query);

            throw re;
        } catch (Exception e) {
            System.out.println("===========> " + e + " " + query);

        } finally {

        }

        return listData;
    }*/

    /*public void iniciaOperacion() throws HibernateException {
        emm = HibernateUtil.getSessionFactory();
        session = emm.openSession();
    }*/
/*
    private void manejaException(HibernateException he) throws HibernateException {
        tx.rollback();
        log.trace("Ocurrio en la cada de acceso de datos " + he);
        throw new HibernateException("Ocurrio en la cada de acceso de datos", he);
    }*/

}
