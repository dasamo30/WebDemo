/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableSupplierBean;
import com.webdemo.service.inventory.ServiceInventory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dasamo
 */

@Controller
@RequestMapping("/purchaseOrdersController")
public class PurchaseOrdersController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelCategories(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Purchase Orders";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Purchases");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_9:#lim_10";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_purchase_orders");
        return mv;    
    }
    
    @RequestMapping(value = "ActViewNewPurchaseOrder", method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView ActViewNewPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
      HttpSession sesion=request.getSession();
        String namemenu="New Purchase Orders";
        String titlemenu="Information";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Purchases");
        route.put(2,"Purchase Orders");
        route.put(3,namemenu);
        
        String opmnu="#lim_9:#lim_10";
        
        
        ArrayList<TableSupplierBean> listSupplier=serviceInventory.get_list_Suppliers();

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.addObject("listSupplier",listSupplier);
        mv.setViewName("view_new_purchase_order");
        return mv;  
    }
    
    @RequestMapping(value="searchByName", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ProductBean> searchByName(@ModelAttribute("term") @Validated String name, HttpServletRequest httpServletRequest)   {
        
        System.out.println("name"+name);
        
        ArrayList<ProductBean> listProductBean=serviceInventory.get_Product_Search(3, name);
    /*
        name
        java.util.Date fecha = new java.util.Date(); 
        location.setDate_creation(fecha);
        System.out.println("location:"+location.toString());
        return serviceInventory.registerLocation(location);*/
        
        return  listProductBean;
        
    }
    
    
}
