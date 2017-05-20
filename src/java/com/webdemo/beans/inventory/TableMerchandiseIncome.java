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
public class TableMerchandiseIncome extends MerchandiseIncomeBean{
    
    private String code_suppliers;
    private String name_suppliers;
    private String ico_search;
    private String ico_delete;
    private String ico_print;

    public TableMerchandiseIncome() {
        super();
    
    }

    /**
     * @return the code_suppliers
     */
    public String getCode_suppliers() {
        return code_suppliers;
    }

    /**
     * @param code_suppliers the code_suppliers to set
     */
    public void setCode_suppliers(String code_suppliers) {
        this.code_suppliers = code_suppliers;
    }

    /**
     * @return the name_suppliers
     */
    public String getName_suppliers() {
        return name_suppliers;
    }

    /**
     * @param name_suppliers the name_suppliers to set
     */
    public void setName_suppliers(String name_suppliers) {
        this.name_suppliers = name_suppliers;
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
    
    
    
    
}
