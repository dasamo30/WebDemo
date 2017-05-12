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
public class PurchaseOrderBean {
    
        
    	private int id;
	private SupplierBean supplier;
	private double amount;
	private String username;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateCreation;
	private String date;
	private List<PurchaseOrderDetailBean> details;
	/*private CompanyBE company;
	private SubsidiaryBE subsidiary;*/

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
     * @return the supplier
     */
    public SupplierBean getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(SupplierBean supplier) {
        this.supplier = supplier;
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
    public List<PurchaseOrderDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<PurchaseOrderDetailBean> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PurchaseOrderBean{" + "id=" + id + ", supplier=" + supplier + ", amount=" + amount + ", username=" + username + ", dateCreation=" + dateCreation + ", date=" + date + ", details=" + details + '}';
    }
        
        
    
}
