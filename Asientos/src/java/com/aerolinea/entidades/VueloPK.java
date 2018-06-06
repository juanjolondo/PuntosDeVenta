/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Embeddable
public class VueloPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_AVION")
    private String codigoAvion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_SILLA")
    private String codigoSilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public VueloPK() {
    }

    public VueloPK(String codigoAvion, String codigoSilla, Date fecha) {
        this.codigoAvion = codigoAvion;
        this.codigoSilla = codigoSilla;
        this.fecha = fecha;
    }

    public String getCodigoAvion() {
        return codigoAvion;
    }

    public void setCodigoAvion(String codigoAvion) {
        this.codigoAvion = codigoAvion;
    }

    public String getCodigoSilla() {
        return codigoSilla;
    }

    public void setCodigoSilla(String codigoSilla) {
        this.codigoSilla = codigoSilla;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAvion != null ? codigoAvion.hashCode() : 0);
        hash += (codigoSilla != null ? codigoSilla.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VueloPK)) {
            return false;
        }
        VueloPK other = (VueloPK) object;
        if ((this.codigoAvion == null && other.codigoAvion != null) || (this.codigoAvion != null && !this.codigoAvion.equals(other.codigoAvion))) {
            return false;
        }
        if ((this.codigoSilla == null && other.codigoSilla != null) || (this.codigoSilla != null && !this.codigoSilla.equals(other.codigoSilla))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.entidades.VueloPK[ codigoAvion=" + codigoAvion + ", codigoSilla=" + codigoSilla + ", fecha=" + fecha + " ]";
    }
    
}
