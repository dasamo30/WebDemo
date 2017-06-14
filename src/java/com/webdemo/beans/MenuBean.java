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
public abstract class MenuBean {
   /* 
   // private ArrayList<MenuBean> nodes=new ArrayList<MenuBean>();

    public ArrayList<MenuBean> getMenu() {
        return nodes;
    }

   
    public void setMenu(ArrayList<MenuBean> menu) {
        this.nodes = menu;
    }*/
    
    private MenuEstado state;
      /**
     * @return the state
     */
    public MenuEstado getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(MenuEstado state) {
        this.state = state;
    }
    
    
    
    protected int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    protected String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    protected String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
    protected String href;

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    protected String parent_id;

    public String getParentId() {
        return this.parent_id;
    }

    public void setParentId(String parent_id) {
        this.parent_id = parent_id;
    }
    
    
    public abstract void agregarMenuHijo(MenuBean menu);
    
    public abstract boolean tieneMenuHijo(); 

  


}
