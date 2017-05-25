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
public class MerchandiseIncomeBean {
    
    private int id;
    private SupplierBean supplier;
    private double amount;
    private String username;
    private Date dateCreation;
    private String date;
    private List<MerchandiseIncomeDetailBean> details;
    private Date registration_date;
    private String nro_document;
    private String reason;

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
    public List<MerchandiseIncomeDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<MerchandiseIncomeDetailBean> details) {
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

    /**
     * @return the nro_document
     */
    public String getNro_document() {
        return nro_document;
    }

    /**
     * @param nro_document the nro_document to set
     */
    public void setNro_document(String nro_document) {
        this.nro_document = nro_document;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "MerchandiseIncomeBean{" + "id=" + id + ", supplier=" + supplier + ", amount=" + amount + ", username=" + username + ", dateCreation=" + dateCreation + ", date=" + date + ", details=" + details + ", registration_date=" + registration_date + ", nro_document=" + nro_document + ", reason=" + reason + '}';
    }

}
