/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans.inventory;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author dasamo
 */
public class ProductBean {
    
   private int id;// serial NOT NULL, 
   private String code;// character varying(20), 
   private String name;// character varying(50), 
   private String description; // character varying, 
   private double unit_cost;// numeric,
   private Date registration_date;
   private String image_name;
   private int stock;
   private int alert_stock;
   private int id_category;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the unit_cost
     */
    public double getUnit_cost() {
        return unit_cost;
    }

    /**
     * @param unit_cost the unit_cost to set
     */
    public void setUnit_cost(double unit_cost) {
        this.unit_cost = unit_cost;
    }

    /**
     * @return the registration_date
     */
    public Date getRegistration_date() {
        return registration_date;
    }

    /**
     * @param registration_date the registration_date to set
     */
    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    /**
     * @return the image_name
     */
    public String getImage_name() {
        return image_name;
    }

    /**
     * @param image_name the image_name to set
     */
    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the alert_stock
     */
    public int getAlert_stock() {
        return alert_stock;
    }

    /**
     * @param alert_stock the alert_stock to set
     */
    public void setAlert_stock(int alert_stock) {
        this.alert_stock = alert_stock;
    }

    /**
     * @return the id_category
     */
    public int getId_category() {
        return id_category;
    }

    /**
     * @param id_category the id_category to set
     */
    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "ProductBean{" + "id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", unit_cost=" + unit_cost + ", registration_date=" + registration_date + ", image_name=" + image_name + ", stock=" + stock + ", alert_stock=" + alert_stock + ", id_category=" + id_category + '}';
    }

}
