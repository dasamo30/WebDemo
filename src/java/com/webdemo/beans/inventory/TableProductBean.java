/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans.inventory;

/**
 *
 * @author dasamo
 */
public class TableProductBean  extends ProductBean{
    
    private String ico_edit;
    private String ico_delete;
    private String ico_img;
    private String msj_alert;

    public TableProductBean() {
        super();
    }

    /**
     * @return the ico_edit
     */
    public String getIco_edit() {
        return ico_edit;
    }

    /**
     * @param ico_edit the ico_edit to set
     */
    public void setIco_edit(String ico_edit) {
        this.ico_edit = ico_edit;
    }

    /**
     * @return the ico_delete
     */
    public String getIco_delete() {
        return ico_delete;
    }

    /**
     * @param ico_delete the ico_delete to set
     */
    public void setIco_delete(String ico_delete) {
        this.ico_delete = ico_delete;
    }

    @Override
    public String toString() {
        return super.toString()+"TableProductBean{" + "ico_edit=" + ico_edit + ", ico_delete=" + ico_delete + ", msj_alert=" + msj_alert + '}';
    }
   
     

    /**
     * @return the ico_img
     */
    public String getIco_img() {
        return ico_img;
    }

    /**
     * @param ico_img the ico_img to set
     */
    public void setIco_img(String ico_img) {
        this.ico_img = ico_img;
    }

    /**
     * @return the msj_alert
     */
    public String getMsj_alert() {
        return msj_alert;
    }

    /**
     * @param msj_alert the msj_alert to set
     */
    public void setMsj_alert(String msj_alert) {
        this.msj_alert = msj_alert;
    }
    
    
}
