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
public class InfoUserBean {
    
    private int id_usuario;
    private String login;
    private String clave;
    private int estado;
    private Date fecha_reg;
    private int nro_sesion;
    private int sesion_activa;
    private String nombres;
    private String apellidos;
    private String foto;
    private String genero;
    private String dni;
    private String correo;
    private int id_perfil;
    private String perfil;
    private int pestado;

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
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
     * @return the fecha_reg
     */
    public Date getFecha_reg() {
        return fecha_reg;
    }

    /**
     * @param fecha_reg the fecha_reg to set
     */
    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    /**
     * @return the nro_sesion
     */
    public int getNro_sesion() {
        return nro_sesion;
    }

    /**
     * @param nro_sesion the nro_sesion to set
     */
    public void setNro_sesion(int nro_sesion) {
        this.nro_sesion = nro_sesion;
    }

    /**
     * @return the sesion_activa
     */
    public int getSesion_activa() {
        return sesion_activa;
    }

    /**
     * @param sesion_activa the sesion_activa to set
     */
    public void setSesion_activa(int sesion_activa) {
        this.sesion_activa = sesion_activa;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

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
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the pestado
     */
    public int getPestado() {
        return pestado;
    }

    /**
     * @param pestado the pestado to set
     */
    public void setPestado(int pestado) {
        this.pestado = pestado;
    }

    @Override
    public String toString() {
        return "InfoUserBean{" + "id_usuario=" + id_usuario + ", login=" + login + ", clave=" + clave + ", estado=" + estado + ", fecha_reg=" + fecha_reg + ", nro_sesion=" + nro_sesion + ", sesion_activa=" + sesion_activa + ", nombres=" + nombres + ", apellidos=" + apellidos + ", foto=" + foto + ", genero=" + genero + ", dni=" + dni + ", correo=" + correo + ", id_perfil=" + id_perfil + ", perfil=" + perfil + ", pestado=" + pestado + '}';
    }
}
