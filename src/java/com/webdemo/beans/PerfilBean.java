/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webdemo.beans;

import java.util.Date;

/**
 *
 * @author dasamo
 */
public class PerfilBean {
    private int id_perfil;
    private String nombre;
    private int estado;
    private Date fecha;
    private int tiempo_sesion;
    private String ico_estado;
    private String ico_editar;
    private String ico_permiso;

    /**
     * @return the id_perfil
     */
    public int getId_perfil() {
        return id_perfil;
    }

    /**
     * @param id_perfil the id_perfil to set
     */
    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tiempo_sesion
     */
    public int getTiempo_sesion() {
        return tiempo_sesion;
    }

    /**
     * @param tiempo_sesion the tiempo_sesion to set
     */
    public void setTiempo_sesion(int tiempo_sesion) {
        this.tiempo_sesion = tiempo_sesion;
    }

    /**
     * @return the ico_editar
     */
    public String getIco_editar() {
        return ico_editar;
    }

    /**
     * @param ico_editar the ico_editar to set
     */
    public void setIco_editar(String ico_editar) {
        this.ico_editar = ico_editar;
    }

    /**
     * @return the ico_permiso
     */
    public String getIco_permiso() {
        return ico_permiso;
    }

    /**
     * @param ico_permiso the ico_permiso to set
     */
    public void setIco_permiso(String ico_permiso) {
        this.ico_permiso = ico_permiso;
    }

    /**
     * @return the ico_estado
     */
    public String getIco_estado() {
        return ico_estado;
    }

    /**
     * @param ico_estado the ico_estado to set
     */
    public void setIco_estado(String ico_estado) {
        this.ico_estado = ico_estado;
    }

    @Override
    public String toString() {
        return "PerfilBean{" + "id_perfil=" + id_perfil + ", nombre=" + nombre + ", estado=" + estado + ", fecha=" + fecha + ", tiempo_sesion=" + tiempo_sesion + ", ico_estado=" + ico_estado + ", ico_editar=" + ico_editar + ", ico_permiso=" + ico_permiso + '}';
    }

    
}
