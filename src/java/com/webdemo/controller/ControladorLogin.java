/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.controller;



import com.webdemo.beans.InfoUserBean;
import com.webdemo.libraries.Menu_recursivo;
import com.webdemo.service.ServiceAccesos;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author dasamo
 */

@Controller
public class ControladorLogin {
    
    private ServiceAccesos serviceAccesos= new ServiceAccesos();
             
    @RequestMapping("/login")
    public ModelAndView WiewLogin(HttpServletRequest request,HttpServletResponse res) {
        /*
        String usuario=request.getParameter("usuario");  
        String clave=request.getParameter("clave"); 
        */
        Date dNow = new Date();
        SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy");
        String datefooter = ft.format(dNow);
        
        
        HttpSession sesionOk = request.getSession();    
            //System.out.println("carga la vista del login");

        ModelAndView mv = null;
        
        if (sesionOk.getAttribute("usuario") == null) {
            mv=new ModelAndView();
            mv.setViewName("view_login");
            mv.addObject("datefooter",datefooter);
        }else{
            mv = new ModelAndView("redirect:panel/home");
        }

        
        return mv;    
    }
    
    //@RequestMapping("/validatelogin")
    @RequestMapping(value = "/validatelogin", method = RequestMethod.POST)
    public ModelAndView validatelogin(@ModelAttribute("usuarioBean") InfoUserBean usuarioBean ,HttpServletRequest request,HttpServletResponse res, RedirectAttributes redir) {
        HttpSession sesion = request.getSession();
        //System.out.println("XXXXXXXXXXXX:"+usuarioBean.toString());

        ModelAndView mv=null;

        int rpta=serviceAccesos.ValidaLogin(usuarioBean);
        if(rpta==0){
            
            InfoUserBean bean=serviceAccesos.getUserInfo(usuarioBean.getLogin());
            //System.out.println("getUserInfo"+bean.toString());
            
            sesion.setAttribute("usuario",bean.getLogin());
            sesion.setMaxInactiveInterval(1000);
            //System.out.println("usuario:"+usuario+"  clave:"+clave);
            
            
            String listado=null;
            
            
            if(bean.getPestado()==1){
                Menu_recursivo menus=new Menu_recursivo();
                listado =menus.muestra_menu_familias(request.getContextPath(),bean.getId_perfil());
            }
            
            
            
            sesion.setAttribute("lmemus",listado);
            //System.out.println("listado:"+listado);
            
            //carga foto
        File file=new File("/opt/apps/dist/fotos/user/"+bean.getFoto());
        String encodedString = null;
        try {
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
            encodedString = new String(encoded);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            sesion.setAttribute("foto",encodedString);
            sesion.setAttribute("nombre",bean.getNombres()+" "+bean.getApellidos());
            sesion.setAttribute("perfil",bean.getPerfil());
            sesion.setAttribute("pestado",bean.getPestado());
            sesion.setAttribute("idusuario",bean.getId_usuario());
            
            Date fecha = new Date();
            DateFormat df=DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("ES"));
            String fecHeader=df.format(bean.getFecha_reg());
            
            sesion.setAttribute("fechaRegistro",fecHeader);
            mv = new ModelAndView("redirect:panel/home");
        }else{
            mv = new ModelAndView("redirect:login");
            String msjlogin;
            
            switch (rpta) {
            case 1:  msjlogin = "el usuario no exite";
                     break;
            case 2:  msjlogin = "La ontraseña  es incorrecta";
                     break;
            case 3:  msjlogin = "Usuario inactivo";
                     break;    
            default: msjlogin = "Error!!!";
                     break;
            }
            
            redir.addFlashAttribute("rpta",msjlogin);
            //mv.addObject("rpta","Credenciales no validas");
            
        }

        return mv;    
    }
    
    
    /*
    public ModelAndView ValidaLogin(HttpServletRequest request,HttpServletResponse res) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("view_login");
    return mv;    
    }*/

    /**
     * @return the serviceAccesos
     */
   /* public ServiceAccesos getServiceAccesos() {
        return serviceAccesos;
    }*/

    /**
     * @param serviceAccesos the serviceAccesos to set
     */
    
    /*public void setServiceAccesos(ServiceAccesos serviceAccesos) {
        this.serviceAccesos = serviceAccesos;
    }
    */
    
}
