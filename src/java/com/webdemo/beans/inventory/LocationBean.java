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
public class LocationBean {
  private int id_location; //serial NOT NULL,
  private String name_location; //character varying(100),
  private Date date_creation; //timestamp without time zone,
  private Date date_modification; //timestamp without time zone, 

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
        return "LocationBean{" + "id_location=" + id_location + ", name_location=" + name_location + ", date_creation=" + date_creation + ", date_modification=" + date_modification + '}';
    }
  
  
}
