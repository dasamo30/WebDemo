/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans;

/**
 *
 * @author dasamo
 */
public class TableUsuarioBean extends InfoUserBean{
    
    private String ico_estado;
    private String ico_edit;
    private String ico_delete;

    public TableUsuarioBean() {
        super();
    }

    /**
     * @return the ico_estado
     */
    public String getIco_estado() {
        return ico_estado;
    }

    /**
     * @param ico_estado the ico_estado to set
     */
    public void setIco_estado(String ico_estado) {
        this.ico_estado = ico_estado;
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
        return super.toString()+"TableUsuarioBean{" + "ico_estado=" + ico_estado + ", ico_edit=" + ico_edit + ", ico_delete=" + ico_delete + '}';
    }
    
}
