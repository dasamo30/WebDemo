/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans.inventory;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dasamo
 */
public class TransferBean {
    
    private int id;
    private LocationBean location;
    private double amount;
    private String username;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreation;
    private String date;
    private List<TransferDetailBean> details;
    private Date registration_date;

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
     * @return the location
     */
    public LocationBean getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(LocationBean location) {
        this.location = location;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the details
     */
    public List<TransferDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<TransferDetailBean> details) {
        this.details = details;
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

    @Override
    public String toString() {
        return "TransferDetailBean{" + "id=" + id + ", location=" + location + ", amount=" + amount + ", username=" + username + ", dateCreation=" + dateCreation + ", date=" + date + ", details=" + details + ", registration_date=" + registration_date + '}';
    }
    
    
}
