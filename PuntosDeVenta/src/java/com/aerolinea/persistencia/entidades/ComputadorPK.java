/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Embeddable
public class ComputadorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_OFICINA")
    private String codigoOficina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_PC")
    private String codigoPc;

    public ComputadorPK() {
    }

    public ComputadorPK(String codigoOficina, String codigoPc) {
        this.codigoOficina = codigoOficina;
        this.codigoPc = codigoPc;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public String getCodigoPc() {
        return codigoPc;
    }

    public void setCodigoPc(String codigoPc) {
        this.codigoPc = codigoPc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOficina != null ? codigoOficina.hashCode() : 0);
        hash += (codigoPc != null ? codigoPc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputadorPK)) {
            return false;
        }
        ComputadorPK other = (ComputadorPK) object;
        if ((this.codigoOficina == null && other.codigoOficina != null) || (this.codigoOficina != null && !this.codigoOficina.equals(other.codigoOficina))) {
            return false;
        }
        if ((this.codigoPc == null && other.codigoPc != null) || (this.codigoPc != null && !this.codigoPc.equals(other.codigoPc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.ComputadorPK[ codigoOficina=" + codigoOficina + ", codigoPc=" + codigoPc + " ]";
    }
    
}
