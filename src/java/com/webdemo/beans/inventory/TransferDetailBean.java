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
public class TransferDetailBean {
    
    private TransferBean transfer;
    private ProductBean product;
    private double amount;
    private double sell_price;
    private Date dateCreation;

    /**
     * @return the transfer
     */
    public TransferBean getTransfer() {
        return transfer;
    }

    /**
     * @param transfer the transfer to set
     */
    public void setTransfer(TransferBean transfer) {
        this.transfer = transfer;
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
     * @return the sell_price
     */
    public double getSell_price() {
        return sell_price;
    }

    /**
     * @param sell_price the sell_price to set
     */
    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    @Override
    public String toString() {
        return "TransferDetailBean{" + "transfer=" + transfer + ", product=" + product + ", amount=" + amount + ", sell_price=" + sell_price + ", dateCreation=" + dateCreation + '}';
    }

    
    
    
}
