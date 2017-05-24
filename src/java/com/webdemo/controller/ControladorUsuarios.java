/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.InfoUserBean;
import com.webdemo.beans.PerfilBean;
import com.webdemo.beans.TableUsuarioBean;
import com.webdemo.service.ServiceAccesos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dasamo
 */
@Controller
@RequestMapping("/usuarios")
public class ControladorUsuarios {
    private ServiceAccesos serviceAccesos= new ServiceAccesos();
    //Gson gson = new Gson();
    
    @RequestMapping("/inicio")
    public ModelAndView WiewPanelUsuarios(HttpServletRequest request, HttpServletResponse response) {
    HttpSession sesion=request.getSession();    

    
    String namemenu="Usuarios";
    String titlemenu="inicio";
    
    Map route = new HashMap<Integer, String>();
    route.put(1,"Accesos");
    route.put(2,"Usuario");
    //route.put(3,"Alumno");
    
    String opmnu="#lim_1:#lim_2";
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("menus",sesion.getAttribute("lmemus"));
    mv.addObject("foto",sesion.getAttribute("foto"));
    mv.addObject("namemenu",namemenu);
    mv.addObject("titlemenu",titlemenu);
    mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/accesos.js\" type=\"text/javascript\" ></script>");
    mv.addObject("route",route);
    mv.addObject("opmnu",opmnu);
    mv.setViewName("view_panel_usuarios");
    return mv;    
    }
    
    //
    @RequestMapping(value="/ActlistaUsuarios",method = RequestMethod.POST)
    @ResponseBody 
    public String ActlistaUsuarios(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableUsuarioBean> listUsuarios=serviceAccesos.get_list_usuarios();
        
        for (TableUsuarioBean l : listUsuarios) {
            
         String icono =(l.getEstado()==1)? "<span class=\"label label-success\">A</span>":"<span class=\"label label-danger\">D</span>";
            l.setIco_estado(icono);
            l.setIco_edit("<button data-toggle=\"modal\" data-target=\"#myModalViewUsuario\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId_usuario()+"\" id=\"btnViewEditUsuario\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/usuarios/ActViewModifUsuario\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId_usuario()+"\" id=\"btnEliminaUsuario\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
        
            
        }
        
        DataTableObject dataTableObject = new DataTableObject();
        
        dataTableObject.setAaData(listUsuarios);
        dataTableObject.setiTotalDisplayRecords(listUsuarios.size());
        dataTableObject.setiTotalRecords(listUsuarios.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    //ActViewUsuario
    @RequestMapping(value = "ActViewUsuario", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewUsuario() {
        
        
      ArrayList<PerfilBean>  listPerfil=serviceAccesos.get_list_perfiles();

      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_nuevo_usuario");
      mav.addObject("listPerfil", listPerfil);
      mav.addObject("formUsuario", "frmrRegistraUsuario");
       return mav;  
    }
    
    @RequestMapping(value="ActRegistraUsuario", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegistraUsuario(@RequestBody InfoUserBean usuario){
    
 
        int rpta=serviceAccesos.registraUsuarios(usuario);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rpta;
    }
    
    @RequestMapping(value = "ActViewModifUsuario", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifUsuario(@RequestParam("idUsuario") int idUsuario,HttpServletRequest request) {
        
        
      ArrayList<PerfilBean>  listPerfil=serviceAccesos.get_list_perfiles();
      InfoUserBean usuarioBean=serviceAccesos.get_usuario(idUsuario);
      
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_nuevo_usuario");
      mav.addObject("listPerfil", listPerfil);
      mav.addObject("usuarioBean", usuarioBean);
      mav.addObject("formUsuario", "frmrModificaUsuario");
       return mav;  
    }
    
    
    @RequestMapping(value="ActModificaUsuario", method = RequestMethod.POST)
    @ResponseBody
    public int ActModificaUsuario(@RequestBody InfoUserBean usuario)   {
    

        
        System.out.println("ActModificaUsuario:"+usuario.toString());
        return serviceAccesos.modificarUsuario(usuario);
        
        
    }
 
    @RequestMapping(value="ActEliminaUsuario", method = RequestMethod.POST)
    @ResponseBody
    public int ActEliminaUsuario(@RequestParam("idUsuario") int idUsuario){
        
        System.out.println(":::"+idUsuario);
        int rpta=serviceAccesos.eliminaUsuario(idUsuario);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
}
