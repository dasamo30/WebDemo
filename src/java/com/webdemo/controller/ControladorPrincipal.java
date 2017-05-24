/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller;


import com.webdemo.libraries.Menu_recursivo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dasamo
 */
@Controller
@RequestMapping("/panel")
public class ControladorPrincipal {
    
   // private ServiceAccesos serviceAccesos= new ServiceAccesos();
    
    @RequestMapping("/home")
    public ModelAndView WiewPanelPrincipal(HttpServletRequest request, HttpServletResponse response) {
    HttpSession sesion=request.getSession();    
    /*
    String usuario=request.getParameter("usuario");  
    String clave=request.getParameter("clave"); 
    */
    //Menu_recursivo menus=new Menu_recursivo();
    //String listado =menus.muestra_menu_familias(request.getContextPath(),1);
            
    //System.out.println("listado:"+listado);
    System.out.println("carga la vista del home");
    
    String namemenu="Panel Principal";
    String titlemenu="";
    Map route = new HashMap<Integer, String>();
    route.put(1,"Panel Principal");
    
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("menus",sesion.getAttribute("lmemus"));
    mv.addObject("foto",sesion.getAttribute("foto"));
    mv.addObject("namemenu",namemenu);
    mv.addObject("titlemenu",titlemenu);
    mv.addObject("route",route);
    mv.setViewName("view_panel");
    return mv;    
    }
    
    @RequestMapping(value = "cerrar", method = RequestMethod.GET)
    @ResponseBody
    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException  {
      System.out.println("entro al login cerrar");
        HttpSession sesionOk = request.getSession();
       // log.trace("Cierre de sesion: usuario -> " + sesionOk.getAttribute("usuario"));
        sesionOk.invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
        
    }
    
    
}
