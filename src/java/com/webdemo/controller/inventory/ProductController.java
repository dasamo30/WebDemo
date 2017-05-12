/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller.inventory;

import DataTableObject.DataTableObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdemo.beans.inventory.ProductBean;
import com.webdemo.beans.inventory.TableCategoryBean;
import com.webdemo.beans.inventory.TableProductBean;
import com.webdemo.service.inventory.ServiceInventory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;
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
@RequestMapping("/ProductController")
public class ProductController {
    private ServiceInventory serviceInventory=new ServiceInventory();
    private final String UPLOAD_DIRECTORY = "/opt/apps/dist/fotos/products";     
    
    @RequestMapping("/initial")
    public ModelAndView  ViewPanelProducts(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion=request.getSession();
        String namemenu="Products";
        String titlemenu="initial";

        Map route = new HashMap<Integer, String>();
        route.put(1,"Maintenance");
        route.put(2,namemenu);
        //route.put(3,"Alumno");

        String opmnu="#lim_4:#lim_5";

        ModelAndView mv = new ModelAndView();
        mv.addObject("menus",sesion.getAttribute("lmemus"));
        mv.addObject("foto",sesion.getAttribute("foto"));
        mv.addObject("namemenu",namemenu);
        mv.addObject("titlemenu",titlemenu);
        mv.addObject("funtion","<script src=\""+request.getContextPath()+"/js/maintenance.js\" type=\"text/javascript\" ></script>");
        mv.addObject("route",route);
        mv.addObject("opmnu",opmnu);
        mv.setViewName("view_panel_products");
        return mv;    
    }
    
    @RequestMapping(value = "ActViewNewProduct", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewAddProducts() {
        //String  btnSaveEmployee = btn.crear("submit", 70, "grabar.png", "btnSaveEmployee","btnSaveEmployee", "Aceptar", "", "Aceptar");
      ArrayList<TableCategoryBean> listCategories=serviceInventory.get_list_Categories();
        
      ModelAndView mav = new ModelAndView();  
      mav.setViewName("view_new_product");
      mav.addObject("formHardware", "frmrRegisterProduct");
      mav.addObject("listCategories",listCategories);
       return mav;  
    }
    
    @RequestMapping(value = "ActViewModifProduct", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView ActViewModifProduct(@RequestParam("product_id") int product_id,HttpServletRequest request) throws FileNotFoundException, IOException {
      ModelAndView mav = new ModelAndView();  
      String baseurl = request.getContextPath();
      ProductBean p=serviceInventory.get_Product(product_id);
      
      System.out.println("producto:"+p.toString());
      
      ArrayList<TableCategoryBean> listCategories=serviceInventory.get_list_Categories();
      
      
       File file=new File(UPLOAD_DIRECTORY+"/"+p.getImage_name());
            String encodedString ="";
            
            if(file.exists()){
                FileInputStream fis=new FileInputStream(file);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                int b;
                byte[] buffer = new byte[1024];
                while((b=fis.read(buffer))!=-1){
                bos.write(buffer,0,b);
                }
                byte[] fileBytes=bos.toByteArray();
                fis.close();
                bos.close();
                byte[] encoded=Base64.encodeBase64(fileBytes);
                encodedString = "src=\"data:image/jpeg;base64,"+new String(encoded)+" \""; 
            }
      
      
       // System.out.println(":::::::::"+encodedString);
      mav.setViewName("view_new_product");
      mav.addObject("formHardware", "frmModifProduct");
      mav.addObject("imagebase64",encodedString);
      mav.addObject("listCategories",listCategories);
      mav.addObject("productBean", p);
       return mav;  
    }
    
    @RequestMapping(value="ActRegisterProduct", method = RequestMethod.POST)
    @ResponseBody
    public int ActRegisterProduct(HttpServletRequest request,HttpServletResponse response)   {
        int rpta = 0;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        ProductBean p=new ProductBean();
	// process only if its multipart content
	if (isMultipart) {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			List<FileItem> multiparts = upload.parseRequest(request);
                       
                        FileItem imagen = null;  
			for (FileItem item : multiparts) {
                         if(item.isFormField()){
                                 System.out.println("getFieldName"+item.getFieldName()+"---"+item.getName()+"---"+item.getString());
                                 
                                 if(item.getFieldName().equals("code")){
                                     p.setCode(item.getString());
                                     //p.setImage_name(item.getString().replace(" ",""));
                                 }
                                 if(item.getFieldName().equals("name")){
                                     p.setName(item.getString());
                                 }
                                 if(item.getFieldName().equals("description")){
                                     p.setDescription(item.getString());
                                 }
                                 if(item.getFieldName().equals("unit_cost")){
                                     p.setUnit_cost(Double.parseDouble(item.getString()));
                                 }
                                 
                                 if(item.getFieldName().equals("id_category")){
                                     p.setId_category(Integer.parseInt(item.getString()));
                                 }
                                 
                                 if(item.getFieldName().equals("alert_stock")){
                                     p.setAlert_stock(Integer.parseInt(item.getString()));
                                 }
                                 
                                         
                                         
                                           
                                
                         }else if (!item.isFormField()) {
                            if(item.getFieldName().equals("imageProd")){
                                 System.out.println("iamgen::getFieldName"+item.getFieldName()+"---"+item.getName()+"---"+item.getContentType());
                                 /*String name = new File(p.getImage_name()).getName();
                                 item.write(new File(UPLOAD_DIRECTORY + File.separator + name));*/
                                 imagen=item;
                             }
                             

                        }
                      }
                        System.out.println("iamgen: "+imagen.getName()+" leng:"+imagen.getName().length());
                        if((imagen.getName() != null) && (!imagen.getName().equals(""))){
                            System.out.println("cumple el if");
                            String fileName=imagen.getName();
                            String extension=fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
                            System.out.println("*******:"+extension);   
                            p.setImage_name(p.getCode()+"."+extension);
                        }
                        
                        
                        rpta=serviceInventory.RegisterProduct(p);   
                     
                     if(rpta==0 && p.getImage_name()!=null){
                         System.out.println("graba imagen");
                        String name = new File(p.getImage_name()).getName();


                        imagen.write(new File(UPLOAD_DIRECTORY + File.separator + name));   
                     }   
                        
		} 
		catch (Exception e) 
		{
                  rpta=3;
		  e.printStackTrace();
                    System.out.println("ActRegisterProduct:"+e.getMessage());
		}
	}


            System.out.println("archivo:"+p.toString());
       // InputStream stream = multipartFile.getInputStream();
        //@RequestBody ProductBean product
        //System.out.println(":::"+product.toString());
        //int rpta=0;//serviceInventory.RegisterProduct(product);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
    
    @RequestMapping(value="ActModifyProduct", method = RequestMethod.POST)
    @ResponseBody
    public int ActModifyProduct(HttpServletRequest request,HttpServletResponse response){
        int rpta = 0;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        ProductBean p=new ProductBean();
	// process only if its multipart content
	if (isMultipart) {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			List<FileItem> multiparts = upload.parseRequest(request);
                       
                        FileItem imagen = null;  
			for (FileItem item : multiparts) {
                         if(item.isFormField()){
                                 System.out.println("getFieldName"+item.getFieldName()+"---"+item.getName()+"---"+item.getString());
                                 
                                 
                                 if(item.getFieldName().equals("id")){
                                     p.setId(Integer.parseInt(item.getString()));
                                     //p.setImage_name(item.getString().replace(" ",""));
                                 }        
                                 if(item.getFieldName().equals("code")){
                                     p.setCode(item.getString());
                                     //p.setImage_name(item.getString().replace(" ",""));
                                 }
                                 if(item.getFieldName().equals("name")){
                                     p.setName(item.getString());
                                 }
                                 if(item.getFieldName().equals("description")){
                                     p.setDescription(item.getString());
                                 }
                                 if(item.getFieldName().equals("unit_cost")){
                                     p.setUnit_cost(Double.parseDouble(item.getString()));
                                 }
                                 if(item.getFieldName().equals("id_category")){
                                     p.setId_category(Integer.parseInt(item.getString()));
                                 }
                                 
                                 if(item.getFieldName().equals("alert_stock")){
                                     p.setAlert_stock(Integer.parseInt(item.getString()));
                                 }
                                           
                                
                         }else if (!item.isFormField()) {
                            if(item.getFieldName().equals("imageProd")){
                                 System.out.println("iamgen::getFieldName"+item.getFieldName()+"---"+item.getName()+"---"+item.getContentType());
                                 /*String name = new File(p.getImage_name()).getName();
                                 item.write(new File(UPLOAD_DIRECTORY + File.separator + name));*/
                                 imagen=item;
                             }
                             

                        }
                      }
                        System.out.println("iamgen: "+imagen.getName()+" leng:"+imagen.getName().length());
                        if((imagen.getName() != null) && (!imagen.getName().equals(""))){
                            System.out.println("cumple el if");
                            String fileName=imagen.getName();
                            String extension=fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
                            System.out.println("*******:"+extension);   
                            p.setImage_name(p.getCode()+"."+extension);
                        }
                        
                        System.out.println(":::"+p.toString());
                        rpta=serviceInventory.ModifyProduct(p);   
                     
                     if(rpta==0 && p.getImage_name()!=null){
                         System.out.println("graba imagen");
                        String name = new File(p.getImage_name()).getName();


                        imagen.write(new File(UPLOAD_DIRECTORY + File.separator + name));   
                     }   
                        
		} 
		catch (Exception e) 
		{
                  rpta=3;
		  e.printStackTrace();
                    System.out.println("ActRegisterProduct:"+e.getMessage());
		}
	}
        //rpta=serviceInventory.ModifyProduct(p);
     
        return rpta;
    }
    
    @RequestMapping(value="/ActListProduct",method = RequestMethod.POST)
    @ResponseBody 
    public String ActListProduct(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        String baseurl = request.getContextPath();
    
        ArrayList<TableProductBean> listProduct=serviceInventory.get_list_Product();
        DataTableObject dataTableObject = new DataTableObject();
        
        for (TableProductBean p : listProduct) {
            
            /**********************************/
            File file=new File(UPLOAD_DIRECTORY+"/"+p.getImage_name());
            String encodedString = baseurl+"/images/noimage-3.png";
            
            if(file.exists()){
                FileInputStream fis=new FileInputStream(file);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                int b;
                byte[] buffer = new byte[1024];
                while((b=fis.read(buffer))!=-1){
                bos.write(buffer,0,b);
                }
                byte[] fileBytes=bos.toByteArray();
                fis.close();
                bos.close();
                byte[] encoded=Base64.encodeBase64(fileBytes);
                encodedString = "data:image/jpeg;base64,"+new String(encoded); 
            }
            
            
            /*********************************/
           // p.setIco_edit("<a id=\""+p.getId()+"\" class=\"view_edit_produc\" href=\"#\"><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></a>");
            p.setIco_edit("<button data-toggle=\"modal\" data-target=\"#myModalNewProduct\" data-remote=\"false\" type=\"button\" data-id=\""+p.getId()+"\" id=\"btnViewEditProduc\" class=\"btn btn-info btn-xs\" href=\""+baseurl+"/ProductController/ActViewModifProduct\" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>");
            p.setIco_delete("<button type=\"button\" data-id=\""+p.getId()+"\" id=\"btnDeletetProduct\" class=\"btn btn-danger btn-xs\"  ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
            p.setIco_img("<img src=\""+encodedString+"\" class=\"img-responsive center-block\"  height=\"42\" width=\"42\">");
            if(p.getStock() <= p.getAlert_stock()){
                p.setMsj_alert( p.getStock()+" <span class=\"label label-warning text-gray-dark\">ALERT!</span>");
            }else{
                p.setMsj_alert(""+p.getStock());
            }
                
            //p.setIco_delete("<button type=\"button\" id=\"btnDeletetProduct\" class=\"btn btn-danger btn-xs\" data-toggle=\"modal\" data-target=\"#confirmDelete\" data-remote=\"false\" ><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button>");
        }
     
        dataTableObject.setAaData(listProduct);
        dataTableObject.setiTotalDisplayRecords(listProduct.size());
        dataTableObject.setiTotalRecords(listProduct.size());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataTableObject);
        
        //out.print(json);
        
        return json;
        
    }
    
    @RequestMapping(value="ActDeleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public int ActDeleteProduct(@RequestParam("idProduct") int idProduct){
        
        System.out.println(":::"+idProduct);
        int rpta=serviceInventory.deleteProduct(idProduct);
        //int rpta=serviceAccesos.registraUsuarios(usuarios);
     
        return rpta;
    }
}
