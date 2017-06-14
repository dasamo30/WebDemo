/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans;

/**
 *
 * @author dasamo
 */
public class MenuEstado {
    
    private boolean checked; //false,
    private boolean disabled; //false,
    private boolean expanded; //false,
    private boolean selected; //true

    public MenuEstado(boolean checked, boolean disabled, boolean expanded, boolean selected) {
        this.checked = checked;
        this.disabled = disabled;
        this.expanded = expanded;
        this.selected = selected;
    }

    public MenuEstado() {
    }
/*
    public MenuEstado(boolean checked) {
        this.checked = checked;
    */
    
    
    
    
    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the expanded
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * @param expanded the expanded to set
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    
}
