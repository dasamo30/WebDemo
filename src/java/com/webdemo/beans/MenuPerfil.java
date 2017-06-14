package com.webdemo.beans;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author dasamo
 */
public class MenuPerfil {
    
     private int idPerfil;
     private int idMenu;
     private int padre;
     private int nivel;
     private String nombreMenu;
     private String url;
     private Integer estado;
     private int nroh;
     private String icono;
     private int orden;
     
    public void setNroh(int nroh) {
        this.nroh = nroh;
    }

    public int getNroh() {
        return nroh;
    }

    /**
     * @return the idPerfil
     */
    public int getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * @return the idMenu
     */
    public int getIdMenu() {
        return idMenu;
    }

    /**
     * @param idMenu the idMenu to set
     */
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    /**
     * @return the padre
     */
    public int getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(int padre) {
        this.padre = padre;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the nombreMenu
     */
    public String getNombreMenu() {
        return nombreMenu;
    }

    /**
     * @param nombreMenu the nombreMenu to set
     */
    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "MenuPerfil{" + "idPerfil=" + idPerfil + ", idMenu=" + idMenu + ", padre=" + padre + ", nivel=" + nivel + ", nombreMenu=" + nombreMenu + ", url=" + url + ", estado=" + estado + ", nroh=" + nroh + ", icono=" + icono + ", orden=" + orden + '}';
    }

    
}
