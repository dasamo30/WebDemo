/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.AjaxResponseBE;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.PurchaseOrderBean;
import com.webdemo.beans.inventory.PurchaseOrderDetailBean;
import com.webdemo.beans.inventory.TablePurchaseOrder;
import com.webdemo.beans.inventory.TableSupplierBean;
import com.webdemo.service.inventory.ServiceInventory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @RequestMapping(value="ActSavePurchaseOrder", method = RequestMethod.POST)
    @ResponseBody
    public int ActSavePurchaseOrder(@ModelAttribute("data") String data,HttpServletRequest request) throws IOException   {
        HttpSession sesion = request.getSession();
        System.out.println("data:"+data);
        //PurchaseOrderBean purchaseOrderBean=new PurchaseOrderBean();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper a=new ObjectMapper();
        a.setDateFormat(df);
         
        PurchaseOrderBean purchaseOrderBean=a.readValue(data,PurchaseOrderBean.class);
        
        
        purchaseOrderBean.setUsername(sesion.getAttribute("usuario").toString());
        
        System.out.println("purchaseOrderBean:"+purchaseOrderBean.toString());
        
        

       
        String fecha = df.format(purchaseOrderBean.getDateCreation());
        System.out.println("fecha:"+fecha);

        return serviceInventory.savePurchaseOrder(purchaseOrderBean);
    }
    //ActListPurchaseOrder
    @RequestMapping(value="/ActListPurchaseOrder",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TablePurchaseOrder> listPurchaseOrder=serviceInventory.get_list_purchaseOrderBean();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TablePurchaseOrder l : listPurchaseOrder) {
            
            //l.setDate_modification("");
           // p.setIco_edit("<a id=\""+p.getId()+"\" class=\"view_edit_produc\" href=\"#\"><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></a>");
            l.setIco_search("<button data-toggle=\"modal\" data-target=\"#myModalDetailOrders\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId()+"\" id=\"btnViewDetailOrders\" class=\"btn bg-olive btn-xs\" href=\""+baseurl+"/purchaseOrdersController/ActViewDetailOrders\" ><i style=\"font-size: 18px;\" class=\"fa fa-search\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnDeleteSupplier\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
            l.setIco_print("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnDeleteSupplier\" class=\"btn btn-info btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-print\"></i></button>");
           
        }
     
        dataTableObject.setAaData(listPurchaseOrder);
        dataTableObject.setiTotalDisplayRecords(listPurchaseOrder.size());
        dataTableObject.setiTotalRecords(listPurchaseOrder.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    //ActViewDetailOrders
    @RequestMapping(value = "ActViewDetailOrders", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBE ActViewDetailOrders(@RequestParam("idPurchaseOrder") int idPurchaseOrder,
            HttpServletRequest request){
       
        
        
      PurchaseOrderBean purchaseOrderBean=serviceInventory.get_purchaseOrderBean(idPurchaseOrder);
      
      ArrayList<PurchaseOrderDetailBean> detail=serviceInventory.get_list_purchaseOrderDetailBean(idPurchaseOrder);
        
                
        for (PurchaseOrderDetailBean pd : detail) {
            ProductBean product=serviceInventory.get_Product(pd.getProduct().getId());
            pd.setProduct(product);
        }      
      purchaseOrderBean.setDetails(detail);
      
      
      AjaxResponseBE ajaxResponseBE;
      ajaxResponseBE = new AjaxResponseBE();
      ajaxResponseBE.setState("200");
      ajaxResponseBE.setMessage("Request processed correctly.");
      ajaxResponseBE.setDescription("The transfer has been successfully finded.");
      ajaxResponseBE.setData(purchaseOrderBean);
      
      System.out.println("idPurchaseOrder:"+idPurchaseOrder);
      
 
       return ajaxResponseBE;  
    }
}
