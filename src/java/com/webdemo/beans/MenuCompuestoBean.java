/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans;

import java.util.ArrayList;

/**
 *
 * @author dasamo
 */
public class MenuCompuestoBean  extends MenuBean{
    
     private ArrayList<MenuBean> nodes=new ArrayList<MenuBean>();


    @Override
    public void agregarMenuHijo(MenuBean menu) {
        
        this.nodes.add(menu);
        
    }

    @Override
    public boolean tieneMenuHijo() {
        return nodes.size()>0;
    }

    
}
