/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans.inventory;

import java.util.Date;

/**
 *
 * @author dasamo
 */
public class PurchaseOrderDetailBean {
    
    private PurchaseOrderBean purchaseOrderBean;
    private ProductBean product;
    private double amount;
    private double costPrice;
    private Date dateCreation;

    /**
     * @return the purchaseOrderBean
     */
    public PurchaseOrderBean getPurchaseOrderBean() {
        return purchaseOrderBean;
    }

    /**
     * @param purchaseOrderBean the purchaseOrderBean to set
     */
    public void setPurchaseOrderBean(PurchaseOrderBean purchaseOrderBean) {
        this.purchaseOrderBean = purchaseOrderBean;
    }

    /**
     * @return the product
     */
    public ProductBean getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductBean product) {
        this.product = product;
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
     * @return the costPrice
     */
    public double getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice the costPrice to set
     */
    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
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

    @Override
    public String toString() {
        return "PurchaseOrderDetailBean{" + "purchaseOrderBean=" + purchaseOrderBean + ", product=" + product + ", amount=" + amount + ", costPrice=" + costPrice + ", dateCreation=" + dateCreation + '}';
    }
    
    
    
    
}
