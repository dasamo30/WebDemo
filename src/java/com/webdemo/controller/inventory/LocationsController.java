/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.inventory.LocationBean;
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.service.inventory.ServiceInventory;
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
 * @author developer
 */

@Controller
@RequestMapping("/locationController")
public class LocationsController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelLocations(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Locations";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Maintenance");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_4:#lim_6";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_locations");
        return mv;    
    }
    
    @RequestMapping(value = "ActViewNewLocation", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewNewLocation() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_new_location");
      mav.addObject("formLocation", "frmrRegisterLocation");
       return mav;  
    }
    
    @RequestMapping(value="ActRegisterLocation", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegisterLocation(@RequestBody LocationBean location)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        location.setDate_creation(fecha);
        System.out.println("location:"+location.toString());
        return serviceInventory.registerLocation(location);
    }
    
    //ActListLocation
    @RequestMapping(value="/ActListLocation",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListLocation(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableLocationBean> listLocations=serviceInventory.get_list_Location();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableLocationBean l : listLocations) {
            
            //l.setDate_modification("");
           // p.setIco_edit("<a id=\""+p.getId()+"\" class=\"view_edit_produc\" href=\"#\"><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></a>");
            l.setIco_edit("<button data-toggle=\"modal\" data-target=\"#myModalNewLocation\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId_location()+"\" id=\"btnViewEditLocation\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/locationController/ActViewModifLocation\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId_location()+"\" id=\"btnDeletetLocation\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
           
        }
     
        dataTableObject.setAaData(listLocations);
        dataTableObject.setiTotalDisplayRecords(listLocations.size());
        dataTableObject.setiTotalRecords(listLocations.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    //ActDeleteLocation
    @RequestMapping(value="ActDeleteLocation", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteLocation(@RequestParam("idLocation") int idLocation){
        
        System.out.println(":::"+idLocation);
        int rpta=serviceInventory.deleteLocation(idLocation);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
    
    //ActViewModifLocation
    @RequestMapping(value = "ActViewModifLocation", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifLocation(@RequestParam("location_id") int location_id,HttpServletRequest request){
      ModelAndView mav = new ModelAndView();  
      //String baseurl = request.getContextPath();
      LocationBean l=serviceInventory.get_Location(location_id);
      
      System.out.println("location:"+l.toString());
      mav.setViewName("view_new_location");
      mav.addObject("formLocation", "frmModifLocation");
      mav.addObject("LocationBean", l);
       return mav;  
    }
    
    @RequestMapping(value="ActModifyLocation", method = RequestMethod.POST)
    @ResponseBody
    public int ActModifyLocation(@RequestBody LocationBean location)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        location.setDate_modification(fecha);
        System.out.println("location:"+location.toString());
        return serviceInventory.modifyLocation(location);
        
    }
}
