/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans.inventory;

import java.util.Date;

/**
 *
 * @author developer
 */
public class SupplierBean {
  private int id_supplier; //serial NOT NULL,
  private String code_suppliers; //character varying(50),
  private String name_suppliers; //character varying(100),
  private String phone_number; //character varying(100),
  private String web; //character varying(100),
  private Date date_creation; //timestamp without time zone,
  private Date date_modification; //timestamp; /without time zone,

    /**
     * @return the id_supplier
     */
    public int getId_supplier() {
        return id_supplier;
    }

    /**
     * @param id_supplier the id_supplier to set
     */
    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
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
     * @return the phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * @param phone_number the phone_number to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * @return the date_creation
     */
    public Date getDate_creation() {
        return date_creation;
    }

    /**
     * @param date_creation the date_creation to set
     */
    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    /**
     * @return the date_modification
     */
    public Date getDate_modification() {
        return date_modification;
    }

    /**
     * @param date_modification the date_modification to set
     */
    public void setDate_modification(Date date_modification) {
        this.date_modification = date_modification;
    }

    @Override
    public String toString() {
        return "SuppliersBean{" + "id_supplier=" + id_supplier + ", code_suppliers=" + code_suppliers + ", name_suppliers=" + name_suppliers + ", phone_number=" + phone_number + ", web=" + web + ", date_creation=" + date_creation + ", date_modification=" + date_modification + '}';
    }
    
  
}
