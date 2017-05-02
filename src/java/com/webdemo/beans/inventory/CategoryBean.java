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
public class CategoryBean {
    private int id_category; //serial NOT NULL,
    private String name_category; //character varying(100),
    private Date date_creation; //timestamp without time zone,
    private Date date_modification; //timestamp without time zone,

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

    /**
     * @return the name_category
     */
    public String getName_category() {
        return name_category;
    }

    /**
     * @param name_category the name_category to set
     */
    public void setName_category(String name_category) {
        this.name_category = name_category;
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
        return "CategoryBean{" + "id_category=" + id_category + ", name_category=" + name_category + ", date_creation=" + date_creation + ", date_modification=" + date_modification + '}';
    }
    
}
