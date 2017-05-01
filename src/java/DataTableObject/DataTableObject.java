/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTableObject;

import com.webdemo.beans.InfoUserBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dasamo
 */
public class DataTableObject {
 
    int  iTotalRecords;
 
    int  iTotalDisplayRecords;

    String  sEcho;

    String sColumns;

    private Object aaData;

    public int getiTotalRecords() {
     return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
     this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
     return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
     this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
     return sEcho;
    }

    public void setsEcho(String sEcho) {
     this.sEcho = sEcho;
    }

    public String getsColumns() {
     return sColumns;
    }

    public void setsColumns(String sColumns) {
     this.sColumns = sColumns;
    }
    
/*
    public List<student> getAaData() {
     return aaData;
    }

    public void setAaData(List<student> aaData) {
     this.aaData = aaData;
    }*/

    /**
     * @return the aaData
     */
    public Object getAaData() {
        return aaData;
    }

    /**
     * @param aaData the aaData to set
     */
    public void setAaData(Object aaData) {
       this.aaData = aaData;
    }
    
}
