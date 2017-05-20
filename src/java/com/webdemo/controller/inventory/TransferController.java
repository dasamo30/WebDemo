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
import com.webdemo.beans.inventory.TableLocationBean;
import com.webdemo.beans.inventory.TableTransferBean;
import com.webdemo.beans.inventory.TransferBean;
import com.webdemo.beans.inventory.TransferDetailBean;
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
@RequestMapping("/transferController")
public class TransferController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelTransfer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Transfer";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Inventory");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_11:#lim_13";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_transfer");
        return mv;    
    }
    
    @RequestMapping(value = "ActViewNewTransfer", method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView ActViewNewTransfer(HttpServletRequest request, HttpServletResponse response) {
      HttpSession sesion=request.getSession();
        String namemenu="New Transfer";
        String titlemenu="Information";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Inventory");
        route.put(2,"Transfer");
        route.put(3,namemenu);
        
        String opmnu="#lim_11:#lim_13";
        
        
        ArrayList<TableLocationBean> listLocation=serviceInventory.get_list_Location();

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.addObject("listLocation",listLocation);
        mv.setViewName("view_new_transfer");
        return mv;  
    }
    
    
    @RequestMapping(value="ActSaveTransfer", method = RequestMethod.POST)
    @ResponseBody
    public int ActSaveMerchandiseIncome(@ModelAttribute("data") String data,HttpServletRequest request) throws IOException   {
        HttpSession sesion = request.getSession();
        System.out.println("data:"+data);
        //PurchaseOrderBean purchaseOrderBean=new PurchaseOrderBean();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper a=new ObjectMapper();
        a.setDateFormat(df);
         
        TransferBean transferBean=a.readValue(data,TransferBean.class);
        
        
        transferBean.setUsername(sesion.getAttribute("usuario").toString());
        
        System.out.println("transferBean:"+transferBean.toString());
        
        

       
        String fecha = df.format(transferBean.getDateCreation());
        System.out.println("fecha:"+fecha);

        return serviceInventory.saveTransfer(transferBean);
    }
    
    
    //ActListTransfer
    @RequestMapping(value="/ActListTransfer",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListTransfer(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableTransferBean> listTransferBean=serviceInventory.get_list_transferBean();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableTransferBean l : listTransferBean) {
          
            l.setIco_search("<button data-toggle=\"modal\" data-target=\"#myModalDetailTransfer\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId()+"\" id=\"btnViewDetailTransfer\" class=\"btn bg-olive btn-xs\" href=\""+baseurl+"/transferController/ActViewDetailTransfer\" ><i style=\"font-size: 18px;\" class=\"fa fa-search\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnDeleteTransfer\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
            //l.setIco_print("<button type=\"button\" data-id=\""+l.getId()+"\" id=\"btnPrintPurchaseOrder\" class=\"btn btn-info btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-print\"></i></button>");
            
           
        }
     
        dataTableObject.setAaData(listTransferBean);
        dataTableObject.setiTotalDisplayRecords(listTransferBean.size());
        dataTableObject.setiTotalRecords(listTransferBean.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    
    //ActViewDetailIncomes
    @RequestMapping(value = "ActViewDetailTransfer", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBE ActViewDetailTransfer(@RequestParam("id_transfer") int id_transfer,
            HttpServletRequest request){
       
        
        
      TransferBean transferBean=serviceInventory.get_TransferBean(id_transfer);
      
      ArrayList<TransferDetailBean> detail=serviceInventory.get_list_transferDetailBean(id_transfer);
        
                
        for (TransferDetailBean pd : detail) {
            ProductBean product=serviceInventory.get_Product(pd.getProduct().getId());
            pd.setProduct(product);
        }      
      transferBean.setDetails(detail);
      
      
      AjaxResponseBE ajaxResponseBE;
      ajaxResponseBE = new AjaxResponseBE();
      ajaxResponseBE.setState("200");
      ajaxResponseBE.setMessage("Request processed correctly.");
      ajaxResponseBE.setDescription("The transfer has been successfully finded.");
      ajaxResponseBE.setData(transferBean);
      
      System.out.println("id_transfer:"+id_transfer);
      
 
       return ajaxResponseBE;  
    }
    
    //ActDeletePurchaseOrder
    @RequestMapping(value="ActDeleteTransfer", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteTransfer(@RequestParam("id_transfer") int id_transfer){
        
        System.out.println("ActDeleteTransfer:::"+id_transfer);
        int rpta=serviceInventory.deleteTransferBean(id_transfer);
     
        return rpta;
    }

}
