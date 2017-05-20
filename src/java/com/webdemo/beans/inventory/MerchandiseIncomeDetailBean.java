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
public class MerchandiseIncomeDetailBean {
    
    private MerchandiseIncomeBean merchandiseIncomeBean;
    private ProductBean product;
    private double amount;
    private double costPrice;
    private Date dateCreation;

    /**
     * @return the merchandiseIncomeBean
     */
    public MerchandiseIncomeBean getMerchandiseIncomeBean() {
        return merchandiseIncomeBean;
    }

    /**
     * @param merchandiseIncomeBean the merchandiseIncomeBean to set
     */
    public void setMerchandiseIncomeBean(MerchandiseIncomeBean merchandiseIncomeBean) {
        this.merchandiseIncomeBean = merchandiseIncomeBean;
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
        return "MerchandiseIncomeDetailBean{" + "merchandiseIncomeBean=" + merchandiseIncomeBean + ", product=" + product + ", amount=" + amount + ", costPrice=" + costPrice + ", dateCreation=" + dateCreation + '}';
    }
    
}
