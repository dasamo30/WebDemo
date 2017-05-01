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
    
    
        ArrayList<InfoUserBean> listUsuarios=serviceAccesos.get_list_usuarios();
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
    @RequestMapping(value = "ActViewUsuario", method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView ActViewAddEmployee() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_nuevo_usuario");
       return mav;  
    }
    
    @RequestMapping(value="ActRegistraUsuario", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegistraUsuario(@RequestBody InfoUserBean usuarios){
        
       //String rta ="clave="+usuarios.getClave();// this.getAdminservice().saveUsr(usr, usrpass, perfil);
       /* String response_msg = "";
        switch (rta) {
            case "1":
                response_msg = "El usuario se guardo con exito.";
                break;
            case "2":
                response_msg = "El usuario ya existe.";
                break;
        }*/
        int rpta=serviceAccesos.registraUsuarios(usuarios);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rpta;
    }
      /*  
    @RequestMapping(value = "viewConsultaListasActivas", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewConsultaListasActivas() {
        
        String btnsgamcla=btn.crear("submit", 80, "btn_run.png", "btnsgamcla", "btnsgamcla", "Consultar", "", "Consultar");
        ModelAndView mav = new ModelAndView();
       // mav.addObject("btnsgapr", btnsgapr);
        mav.addObject("btnsgamcla", btnsgamcla);
       // mav.addObject("cbosccl", opciones);
        mav.setViewName("../../view_sga_movil_tab_consulta_listas_activas.htm");
        return mav;
    }*/
}
