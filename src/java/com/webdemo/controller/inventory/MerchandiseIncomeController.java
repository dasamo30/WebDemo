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
import com.webdemo.beans.inventory.MerchandiseIncomeBean;
import com.webdemo.beans.inventory.MerchandiseIncomeDetailBean;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableMerchandiseIncome;
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
@RequestMapping("/merchandiseIncomeController")
public class MerchandiseIncomeController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelMerchandiseIncome(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Merchandise Income";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Inventory");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_11:#lim_12";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_merchandise_income");
        return mv;    
    }
    
    
    @RequestMapping(value = "ActViewNewPurchaseOrder", method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView ActViewNewPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
      HttpSession sesion=request.getSession();
        String namemenu="New Merchandise Income";
        String titlemenu="Information";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Inventory");
        route.put(2,"Merchandise Income");
        route.put(3,namemenu);
        
        String opmnu="#lim_11:#lim_12";
        
        
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
        mv.setViewName("view_new_merchandise_income");
        return mv;  
    }
    
    @RequestMapping(value="ActSaveMerchandiseIncome", method = RequestMethod.POST)
    @ResponseBody
    public int ActSaveMerchandiseIncome(@ModelAttribute("data") String data,HttpServletRequest request) throws IOException   {
        HttpSession sesion = request.getSession();
        //System.out.println("data:"+data);
        //PurchaseOrderBean purchaseOrderBean=new PurchaseOrderBean();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper a=new ObjectMapper();
        a.setDateFormat(df);
         
        MerchandiseIncomeBean merchandiseIncomeBean=a.readValue(data,MerchandiseIncomeBean.class);
        
        merchandiseIncomeBean.setUsername(sesion.getAttribute("usuario").toString());        
        //System.out.println("merchandiseIncomeBean:"+merchandiseIncomeBean.toString());


       /*
        String fecha = df.format(merchandiseIncomeBean.getDateCreation());
        System.out.println("fecha:"+fecha);*/

        return serviceInventory.saveMerchandiseIncome(merchandiseIncomeBean);
    }
    
    
    //ActListMerchandiseIncome
    @RequestMapping(value="/ActListMerchandiseIncome",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListMerchandiseIncome(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableMerchandiseIncome> listMerchandiseIncome=serviceInventory.get_list_merchandiseIncomeBean();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableMerchandiseIncome l : listMerchandiseIncome) {
          
            l.setIco_search("<button data-toggle=\"modal\" data-target=\"#myModalDetailIncomes\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId()+"\" id=\"btnViewDetailIncomes\" class=\"btn bg-olive btn-xs\" href=\""+baseurl+"/merchandiseIncomeController/ActViewDetailIncomes\" ><i style=\"font-size: 18px;\" class=\"fa fa-search\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnDeleteMerchandiseIncome\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
            //l.setIco_print("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnPrintPurchaseOrder\" class=\"btn btn-info btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-print\"></i></button>");
            
           
        }
     
        dataTableObject.setAaData(listMerchandiseIncome);
        dataTableObject.setiTotalDisplayRecords(listMerchandiseIncome.size());
        dataTableObject.setiTotalRecords(listMerchandiseIncome.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    
    //ActViewDetailIncomes
    @RequestMapping(value = "ActViewDetailIncomes", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBE ActViewDetailIncomes(@RequestParam("id_merchandise_income") int id_merchandise_income,
            HttpServletRequest request){
       
        
        
      MerchandiseIncomeBean merchandiseIncomeBean=serviceInventory.get_MerchandiseIncomeBean(id_merchandise_income);
      
      ArrayList<MerchandiseIncomeDetailBean> detail=serviceInventory.get_list_merchandiseIncomeDetailBean(id_merchandise_income);
        
                
        for (MerchandiseIncomeDetailBean pd : detail) {
            ProductBean product=serviceInventory.get_Product(pd.getProduct().getId());
            pd.setProduct(product);
        }      
      merchandiseIncomeBean.setDetails(detail);
      
      
      AjaxResponseBE ajaxResponseBE;
      ajaxResponseBE = new AjaxResponseBE();
      ajaxResponseBE.setState("200");
      ajaxResponseBE.setMessage("Request processed correctly.");
      ajaxResponseBE.setDescription("The transfer has been successfully finded.");
      ajaxResponseBE.setData(merchandiseIncomeBean);
      
      //System.out.println("idPurchaseOrder:"+id_merchandise_income);
      
 
       return ajaxResponseBE;  
    }
    
    //ActDeletePurchaseOrder
    @RequestMapping(value="ActDeleteMerchandiseIncome", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteMerchandiseIncome(@RequestParam("id_merchandise_income") int id_merchandise_income){
        
        //System.out.println("ActDeleteMerchandiseIncome:::"+id_merchandise_income);
        int rpta=serviceInventory.deleteMerchandiseIncomeBean(id_merchandise_income);
     
        return rpta;
    }
    
    
}
