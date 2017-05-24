/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.dao;
/*
import com.conciliacion.beans.accesos.MenuPerfil;
import com.conciliacion.beans.accesos.Perfiles;
import com.conciliacion.beans.accesos.Tab_Usuarios;*/
import com.webdemo.beans.InfoUserBean;
import com.webdemo.beans.MenuPerfil;
import com.webdemo.beans.PerfilBean;
import com.webdemo.beans.TableUsuarioBean;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author dasamo
 */
public interface IAccesosDAO {
    
    public int validadUsuario(InfoUserBean userBean); 
    public InfoUserBean getUserInfo(String usuario);
    
    public ArrayList<MenuPerfil> get_menus_accesos_perfiles(int padre,int idPerfil);
    public ArrayList<MenuPerfil> get_permisos_menus_accesos(int padre,int idPerfil);
    
    public ArrayList<TableUsuarioBean> get_list_usuarios();
    public int registraUsuarios(InfoUserBean usuario);
     public int eliminaUsuario(int idUsuario);
     public int modificarUsuario(InfoUserBean usuario);
     public InfoUserBean get_usuario(int idUsuario);
   
    public ArrayList<PerfilBean> get_list_perfiles();
    public int registraPerfil(PerfilBean perfil);
    public PerfilBean get_perfil(int idPerfil);
    public int modificarPerfil(PerfilBean perfil);
    
  /* public Map<String, String> getUserInfo(String login);
   
   public ArrayList<MenuPerfil> get_menus_accesos_perfiles(int padre,int idPerfil);
   
   public ArrayList<MenuPerfil> get_menus_accesos_perfiles2(int idPerfil);
   
   public ArrayList<String[]> toArrayListTabUsuarios();
   
   public ArrayList<Perfiles> listaPerfiles();
   
   
   
   public int eliminaUsuario(int idUsuario);
   public Tab_Usuarios obtieneUsuario(int idUsuario);
   
   public int modificarUsuario(Tab_Usuarios usuario);
   
   public int mantePermisos(int opcion,int id_perfil,int id_menu);
   
   public int registraPerfiles(Perfiles perfil);
   
   public int modificarPerfil(Perfiles perfil);*/
    
  
}
