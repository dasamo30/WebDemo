/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.reports;


import com.webdemo.beans.inventory.PurchaseOrderDetailBean;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dasamo
 */
public class PurchaseOrderDS implements JRDataSource{
    
     private List<PurchaseOrderDetailBean> lstPurchaseOrderDetail = new ArrayList<PurchaseOrderDetailBean>();
     private int currentIndex = -1;

    @Override
    public boolean next() throws JRException {
        return (++currentIndex < lstPurchaseOrderDetail.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
        //System.out.println("objeto:"+lstPurchaseOrderDetail.get(currentIndex).toString());
        Object valor=null;
        
       // System.out.println("getnaem:"+jrf);
        
        if ("code".equals(jrf.getName())) {
            valor = lstPurchaseOrderDetail.get(currentIndex).getProduct().getCode();
        }
        if ("product".equals(jrf.getName())) {            
            valor = lstPurchaseOrderDetail.get(currentIndex).getProduct().getName();
        }
        if ("amount".equals(jrf.getName())) {
        	valor = lstPurchaseOrderDetail.get(currentIndex).getAmount();
        }
        
        if ("costPrice".equals(jrf.getName())) {
        	valor =lstPurchaseOrderDetail.get(currentIndex).getCostPrice();
        }
        
        return valor;
    }
    
    public void setPurchaseOrderDetails(List<PurchaseOrderDetailBean> lstPurchaseOrderDetail) {
        this.lstPurchaseOrderDetail = lstPurchaseOrderDetail;
    }
}
