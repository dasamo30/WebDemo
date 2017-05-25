/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.PerfilBean;
import com.webdemo.service.ServiceAccesos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@RequestMapping("/perfiles")
public class ControladorPerfiles {
    private ServiceAccesos serviceAccesos= new ServiceAccesos();
    
    @RequestMapping("/inicio")
    public ModelAndView WiewPanelPerfiles(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();    


        String namemenu="Perfiles";
        String titlemenu="inicio";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Accesos");
        route.put(2,"Perfiles");
        //route.put(3,"Alumno");
        String opmnu="#lim_1:#lim_3";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/accesos.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_perfiles");
        return mv;    
    }
    
    
    @RequestMapping(value="/ActlistaPerfiles",method = RequestMethod.POST)
    @ResponseBody 
    public String ActlistaUsuarios(HttpServletRequest request, HttpServletResponse response) {
    
        String baseurl = request.getContextPath();
        DataTableObject dataTableObject = new DataTableObject();    
        ArrayList<PerfilBean> listPerfiles=serviceAccesos.get_list_perfiles();
        
        for (PerfilBean p : listPerfiles) {
            //<span class="label label-danger">A </span>
            String icono =(p.getEstado()==1)? "<span class=\"label label-success\">A</span>":"<span class=\"label label-danger\">D</span>";
            p.setIco_estado(icono);
            p.setIco_editar("<button data-toggle=\"modal\" data-target=\"#myModalViewPerfil\" data-remote=\"false\" type=\"button\" data-id=\""+p.getId_perfil()+"\" id=\"btnViewEditPerfil\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/perfiles/ActViewModifPerfil\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            
            //p.setIco_permiso("<a id=\""+p.getId_perfil()+"|"+p.getNombre()+"\" class=\"view_conf_prf\" href=\"\"><i style=\"font-size: 18px;\" class=\"fa fa-unlock-alt\"></i></a>");
        }
        
        dataTableObject.setAaData(listPerfiles);
        dataTableObject.setiTotalDisplayRecords(listPerfiles.size());
        dataTableObject.setiTotalRecords(listPerfiles.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    //ActViewNewPerfil
    @RequestMapping(value = "ActViewNewPerfil", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewNewPerfil() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_new_perfil");
      mav.addObject("formPerfil", "frmrRegisterPerfil");
       return mav;  
    }
    
    
    @RequestMapping(value="ActRegistraPerfil", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegistraPerfil(@RequestBody PerfilBean perfilbean)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        perfilbean.setFecha(fecha);
        perfilbean.setEstado(1);
        perfilbean.setTiempo_sesion(1200);
        
        //System.out.println("ActRegistraPerfil:"+perfilbean.toString());
        return serviceAccesos.registraPerfil(perfilbean);
    }
    
    //ActViewModifPerfil
    @RequestMapping(value = "ActViewModifPerfil", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifPerfil(@RequestParam("idPerfil") int idPerfil,HttpServletRequest request){
      ModelAndView mav = new ModelAndView();  
      //String baseurl = request.getContextPath();
      PerfilBean p=serviceAccesos.get_perfil(idPerfil);
      
      //System.out.println("location:"+l.toString());
      mav.setViewName("view_new_perfil");
      mav.addObject("formPerfil", "frmModifPerfil");
      mav.addObject("PerfilBean", p);
       return mav;  
    }
    
    @RequestMapping(value="ActModificaPerfil", method = RequestMethod.POST)
    @ResponseBody
    public int ActModificaPerfil(@RequestBody PerfilBean perfilbean)   {
    

        perfilbean.setTiempo_sesion(1200);
        
        //System.out.println("ActModificaPerfil:"+perfilbean.toString());
        return serviceAccesos.modificarPerfil(perfilbean);
        
    }
    
    
}
