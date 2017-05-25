/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdemo.libraries;

/*import BE.Menu;
import BE.SP_GET_PRFPRV;
import DAO.Helper.HibernateUtil;
import DAO.SODALI.AdministracionDAOJPAlmpl;
import DAO.SODALI.LoginDAOJPAlmpl;
import com.google.gson.Gson;*/


import com.webdemo.beans.MenuPerfil;
import com.webdemo.service.ServiceAccesos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diegobenavides
 */
public class Menu_recursivo<T> {
    
    private ServiceAccesos serviceAccesos= new ServiceAccesos();
    
/*
    public ConsumoDatosDAO getLogindao() {
        return logindao;
    }

    public void setLogindao(ConsumoDatosDAO logindao) {
        this.logindao = logindao;
    }*/
    /*private AdministracionDAOJPAlmpl admindao = new AdministracionDAOJPAlmpl();
    private AdministracionDAOJPAlmpl admindao2 = new AdministracionDAOJPAlmpl();*/
    public int  nivel = -1;
    public String _html = "";


    
public String  muestra_menu(int padre,String nivelAnterior,String contextPath,int idPerfil){
     
    //ConsumoDatosDAO logindao = new ConsumoDatosDAO();
    
     ArrayList<MenuPerfil> datamenus = serviceAccesos.get_menus_accesos_perfiles(padre,idPerfil);
    
     //HibernateUtil.getSessionFactory().close();
        //System.out.println("numero de lineas::"+datamenus.size());
       for(int i=0;i<datamenus.size();i++){
                   //System.out.println(datamenus.get(i).getIdMenu());
               
         if (this.nivel==-1) {

            _html +="<li id=\"lim_"+datamenus.get(i).getIdMenu()+"\" class=\"treeview\" >\n";

        }else{
            int  diferencia=datamenus.get(i).getNivel()-this.nivel;
            if (diferencia==0)  _html+="</li>\n<li id=\"lim_"+datamenus.get(i).getIdMenu()+"\" class=\"treeview\">\n";
            if (diferencia==1) _html+="<ul class=\"treeview-menu\">\n<li id=\"lim_"+datamenus.get(i).getIdMenu()+"\" >\n";
            if (diferencia==-1) _html+="</li>\n</ul>\n<li id=\"lim_"+datamenus.get(i).getIdMenu()+"\" >\n";

            if(diferencia < (-1))
            {
                    _html += "</li>";
                    for(int e=diferencia;e<0;e++)
                    {
                            _html += "</ul>\n</li>\n";
                    }
                    _html += "<li id=\"lim_"+datamenus.get(i).getIdMenu()+"\" >\n";
            }
            
         }
         
            //System.out.println(datamenus.get(i).getNombreMenu()+"   nivel::"+this.nivel+"  url::"+datamenus.get(i).getUrl());
            String urldb;
            urldb=("".equals(datamenus.get(i).getUrl()) || datamenus.get(i).getUrl() == null)? "javascript:void(0)" : contextPath+"/"+datamenus.get(i).getUrl();
            
            
            String parent=(datamenus.get(i).getPadre()==0 && datamenus.get(i).getUrl() == null)? "<i class=\"fa fa-angle-left pull-right\"></i>":"";
            //System.out.println("nivel:"+this.nivel+"  --url:"+datamenus.get(i).getUrl());
            
            _html += "<a id=\"m_"+datamenus.get(i).getIdMenu()+"\" href='"+urldb+"'><i class=\""+datamenus.get(i).getIcono()+"\"></i><span>"+datamenus.get(i).getNombreMenu()+"</span>"+parent+"</a>";
            this.nivel = datamenus.get(i).getNivel();
            //this.muestra_menu(datamenus.get(i).getIdMenu(),String.valueOf(datamenus.get(i).getNivel()),contextPath);
            this.muestra_menu(datamenus.get(i).getIdMenu(),String.valueOf(datamenus.get(i).getNivel()),contextPath,idPerfil);
               
    }
    

   /***********/ 
 return _html;
}



public String muestra_menu_familias(String contextPath,int idPerfil){
    
    String string = "";
    string += this.muestra_menu(0,"",contextPath,idPerfil);//mostrar_menu(0, "");
    string += "</li>\n";

    for(int i=0;i==this.nivel;i++)
    {
            //string+= "</ul>\n</li>\n";
    }
    return string;
}

public String  muestra_menu_accesos(int padre,String nivelAnterior,String contextPath,int idPerfil){
     
     ArrayList<MenuPerfil> datamenus = serviceAccesos.get_permisos_menus_accesos(padre, idPerfil);
    
     //HibernateUtil.getSessionFactory().close();

   for(int i=0;i<datamenus.size();i++){
                   //System.out.println(datamenus.get(i).getIdMenu());
               
        if (this.nivel==-1) {

            _html +="<li>\n";

        }else{
            int  diferencia=datamenus.get(i).getNivel()-this.nivel;
            if (diferencia==0)  _html+="</li>\n<li>\n";
            if (diferencia==1) _html+="<ul>\n<li>\n";
            if (diferencia==-1) _html+="</li>\n</ul>\n<li>\n";

            if(diferencia < (-1))
            {
                    _html += "</li>";
                    for(int e=diferencia;e<0;e++)
                    {
                            _html += "</ul>\n</li>\n";
                    }
                    _html += "<li>\n";
            }

         }
            String urldb;
            String checked;
            checked = (datamenus.get(i).getIdPerfil() == 0) ? "" : "checked";
            String classtipo=(datamenus.get(i).getNroh()==0)? "file" : "folder";
            urldb=("null".equals(datamenus.get(i).getUrl()) || datamenus.get(i).getUrl() == null)? "javascript:void(0)" : contextPath+"/"+datamenus.get(i).getUrl();
            //_html += "<a href='"+urldb+"'>"+datamenus.get(i).getNombreMenu()+"</a>";
            _html += "<span class=\""+classtipo+"\" ><input type='checkbox' "+checked+" value='" + datamenus.get(i).getIdMenu() + "' id='ckpermisos' >" + datamenus.get(i).getNombreMenu() + "</span>";
            //"<a ><input type='checkbox' {$checked} value='" . $row['id_menu'] . "|" . $row['nombre_menu'] . "' id='ckpermisos' >" . $row['nombre_menu'] . "</a>";
            this.nivel = datamenus.get(i).getNivel();
            this.muestra_menu_accesos(datamenus.get(i).getIdMenu(),String.valueOf(datamenus.get(i).getNivel()),contextPath,idPerfil);
               
    }
    


 return _html;
}

public String muestra_familias_accesos(String contextPath,int idPerfil){
    
    String string = "";
    string += this.muestra_menu_accesos(0,"",contextPath,idPerfil);//mostrar_menu(0, "");
    string += "</li>\n";

    for(int i=0;i==this.nivel;i++)
    {
            string += "</ul>\n</li>\n";
    }
    return string;
}


}
