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
public class TableTransferBean extends TransferBean{
    
    private int id_location;
    private String name_location;
    private String ico_search;
    private String ico_delete;
    private String ico_print;

    public TableTransferBean() {
        super();
    }

    /**
     * @return the id_location
     */
    public int getId_location() {
        return id_location;
    }

    /**
     * @param id_location the id_location to set
     */
    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    /**
     * @return the name_location
     */
    public String getName_location() {
        return name_location;
    }

    /**
     * @param name_location the name_location to set
     */
    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    /**
     * @return the ico_search
     */
    public String getIco_search() {
        return ico_search;
    }

    /**
     * @param ico_search the ico_search to set
     */
    public void setIco_search(String ico_search) {
        this.ico_search = ico_search;
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

    /**
     * @return the ico_print
     */
    public String getIco_print() {
        return ico_print;
    }

    /**
     * @param ico_print the ico_print to set
     */
    public void setIco_print(String ico_print) {
        this.ico_print = ico_print;
    }

    @Override
    public String toString() {
        return "TableTransferBean{" + "id_location=" + id_location + ", name_location=" + name_location + ", ico_search=" + ico_search + ", ico_delete=" + ico_delete + ", ico_print=" + ico_print + '}';
    }
    
}
