/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.inventory.SupplierBean;
import com.webdemo.beans.inventory.TableSupplierBean;
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
@RequestMapping("/supplierController")
public class SuppliersController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelSuppliers(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Supplier";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Maintenance");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_4:#lim_7";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_suppliers");
        return mv;    
    }
    
    
    @RequestMapping(value = "ActViewNewSupplier", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewNewSupplier() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_new_supplier");
      mav.addObject("formSupplier", "frmrRegisterSupplier");
       return mav;  
    }
    
    
    @RequestMapping(value="ActRegisterSupplier", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegisterSupplier(@RequestBody SupplierBean supplier)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        supplier.setDate_creation(fecha);
        System.out.println("supplier:"+supplier.toString());
        return serviceInventory.registerSupplier(supplier);
    }
    
    @RequestMapping(value="/ActListSuppliers",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListSuppliers(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableSupplierBean> listSupplier=serviceInventory.get_list_Suppliers();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableSupplierBean l : listSupplier) {
            
            //l.setDate_modification("");
           // p.setIco_edit("<a id=\""+p.getId()+"\" class=\"view_edit_produc\" href=\"#\"><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></a>");
            l.setIco_edit("<button data-toggle=\"modal\" data-target=\"#myModalNewSupplier\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId_supplier()+"\" id=\"btnViewEditSupplier\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/supplierController/ActViewModifSupplier\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId_supplier()+"\" id=\"btnDeleteSupplier\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
           
        }
     
        dataTableObject.setAaData(listSupplier);
        dataTableObject.setiTotalDisplayRecords(listSupplier.size());
        dataTableObject.setiTotalRecords(listSupplier.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    //ActDeleteLocation
    @RequestMapping(value="ActDeleteSupplier", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteSupplier(@RequestParam("idSupplier") int idSupplier){
        
        System.out.println(":::"+idSupplier);
        int rpta=serviceInventory.deleteSupplier(idSupplier);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
    
    //ActViewModifLocation
    @RequestMapping(value = "ActViewModifSupplier", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifSupplier(@RequestParam("idSupplier") int idSupplier,
            HttpServletRequest request){
      ModelAndView mav = new ModelAndView();  
      //String baseurl = request.getContextPath();
      SupplierBean s=serviceInventory.get_Supplier(idSupplier);
      
      System.out.println("SupplierBean:"+s.toString());
      mav.setViewName("view_new_supplier");
      mav.addObject("formSupplier", "frmModifSupplier");
      mav.addObject("SupplierBean", s);
       return mav;  
    }
    
    
    @RequestMapping(value="ActModifySupplier", method = RequestMethod.POST)
    @ResponseBody
    public int ActModifySupplier(@RequestBody SupplierBean supplier)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        supplier.setDate_modification(fecha);
        System.out.println("supplier:"+supplier.toString());
        return serviceInventory.modifySupplier(supplier);
       
    }
    
    
}
