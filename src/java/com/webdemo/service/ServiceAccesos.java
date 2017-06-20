/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.service;

import com.webdemo.beans.InfoUserBean;
import com.webdemo.beans.MenuPerfil;
import com.webdemo.beans.PerfilBean;
import com.webdemo.beans.TableUsuarioBean;
import com.webdemo.dao.DAOFactory;
import com.webdemo.dao.IAccesosDAO;
import java.util.ArrayList;



/**
 *
 * @author dasamo
 */
public class ServiceAccesos {
    private DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
    private IAccesosDAO accesoDao=fabrica.getAccesosDAO();
    
    public int ValidaLogin(InfoUserBean usuarioBean){
        //System.out.println("ServiceAccesos.ValidaLogin");
        //return "sssss";
        return accesoDao.validadUsuario(usuarioBean);
    }
    
    public InfoUserBean getUserInfo(String usuario){
        //System.out.println("ServiceAccesos.getUserInfo");
        //return "sssss";
        return accesoDao.getUserInfo(usuario);
    }
    
    public ArrayList<MenuPerfil> get_menus_accesos_perfiles(int padre,int idPerfil){
    
        return accesoDao.get_menus_accesos_perfiles(padre, idPerfil);
    }
    
    public ArrayList<MenuPerfil> get_permisos_menus_accesos(int padre, int idPerfil){
        return accesoDao.get_permisos_menus_accesos(padre, idPerfil);
    
    }
    
    public ArrayList<TableUsuarioBean> get_list_usuarios(){
        return accesoDao.get_list_usuarios();
        
    }
    
    public int registraUsuarios(InfoUserBean usuario){
        return accesoDao.registraUsuarios(usuario);
    }
    
    public int eliminaUsuario(int idUsuario){
        return accesoDao.eliminaUsuario(idUsuario);
    }
    
    public int modificarUsuario(InfoUserBean usuario){
        return accesoDao.modificarUsuario(usuario);
    }
    
    public InfoUserBean get_usuario(int idUsuario){
        return accesoDao.get_usuario(idUsuario);
    }
    
    public ArrayList<PerfilBean> get_list_perfiles(){
        return accesoDao.get_list_perfiles();
    }
    
     public int registraPerfil(PerfilBean perfil){
         return accesoDao.registraPerfil(perfil);
     }
     
    public PerfilBean get_perfil(int idPerfil){
        return accesoDao.get_perfil(idPerfil);
    }
    
    public int modificarPerfil(PerfilBean perfil){
        return accesoDao.modificarPerfil(perfil);
    }
    
    public ArrayList<MenuPerfil> get_menus_accesos_perfil(int idPerfil){
        return accesoDao.get_menus_accesos_perfil(idPerfil);
    }
    
    public int cambioPassword(int idUsuario, String passact, String newpass){
         return accesoDao.cambioPassword(idUsuario, passact, newpass);
    }
    
    public int asignaPermiso(boolean opcion, int id_perfil, int id_menu) {
        return accesoDao.asignaPermiso(opcion, id_perfil, id_menu);
    }
}
