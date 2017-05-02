/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.inventory.CategoryBean;
import com.webdemo.beans.inventory.TableCategoryBean;
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
@RequestMapping("/categoryController")
public class CategoriesController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelCategories(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Categories";
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
        mv.setViewName("view_panel_categories");
        return mv;    
    }
    
    //ActViewNewCategory
    @RequestMapping(value = "ActViewNewCategory", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewNewCategory() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_new_category");
      mav.addObject("formCategory", "frmrRegisterCategory");
       return mav;  
    }
    
    @RequestMapping(value="ActRegisterCategory", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegisterCategory(@RequestBody CategoryBean category)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        category.setDate_creation(fecha);
        System.out.println("category:"+category.toString());
        return serviceInventory.registerCategory(category);
    }
    
    @RequestMapping(value="/ActListCategories",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListCategories(HttpServletRequest request, HttpServletResponse response) {
        String baseurl = request.getContextPath();
    
        ArrayList<TableCategoryBean> listCategories=serviceInventory.get_list_Categories();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableCategoryBean l : listCategories) {
            
            //l.setDate_modification("");
           // p.setIco_edit("<a id=\""+p.getId()+"\" class=\"view_edit_produc\" href=\"#\"><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></a>");
            l.setIco_edit("<button data-toggle=\"modal\" data-target=\"#myModalNewCategory\" data-remote=\"false\" type=\"button\" data-id=\""+l.getId_category()+"\" id=\"btnViewEditCategory\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/categoryController/ActViewModifCategory\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            l.setIco_delete("<button type=\"button\" data-id=\""+l.getId_category()+"\" id=\"btnDeleteCategory\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
           
        }
     
        dataTableObject.setAaData(listCategories);
        dataTableObject.setiTotalDisplayRecords(listCategories.size());
        dataTableObject.setiTotalRecords(listCategories.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    //ActDeleteLocation
    @RequestMapping(value="ActDeleteCategory", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteCategory(@RequestParam("idCategory") int idCategory){
        
        System.out.println(":::"+idCategory);
        int rpta=serviceInventory.deleteCategory(idCategory);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
    
    //ActViewModifLocation
    @RequestMapping(value = "ActViewModifCategory", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifCategory(@RequestParam("idCategory") int idCategory,
            HttpServletRequest request){
      ModelAndView mav = new ModelAndView();  
      //String baseurl = request.getContextPath();
      CategoryBean c=serviceInventory.get_Category(idCategory);
      
      System.out.println("categoryBean:"+c.toString());
      mav.setViewName("view_new_category");
      mav.addObject("formCategory", "frmModifCategory");
      mav.addObject("CategoryBean", c);
       return mav;  
    }
    
    
    @RequestMapping(value="ActModifyCategory", method = RequestMethod.POST)
    @ResponseBody
    public int ActModifyCategory(@RequestBody CategoryBean category)   {
    
        
        java.util.Date fecha = new java.util.Date(); 
        category.setDate_modification(fecha);
        System.out.println("category:"+category.toString());
        return serviceInventory.modifyCategory(category);
    }
    
    
}
