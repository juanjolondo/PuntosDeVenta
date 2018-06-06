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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "vuelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT v FROM Vuelo v")
    , @NamedQuery(name = "Vuelo.findByCodigoAvion", query = "SELECT v FROM Vuelo v WHERE v.vueloPK.codigoAvion = :codigoAvion")
    , @NamedQuery(name = "Vuelo.findByCodigoSilla", query = "SELECT v FROM Vuelo v WHERE v.vueloPK.codigoSilla = :codigoSilla")
    , @NamedQuery(name = "Vuelo.findByFecha", query = "SELECT v FROM Vuelo v WHERE v.vueloPK.fecha = :fecha")
    , @NamedQuery(name = "Vuelo.findByDisponible", query = "SELECT v FROM Vuelo v WHERE v.disponible = :disponible")})
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VueloPK vueloPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DISPONIBLE")
    private String disponible;
    @JoinColumn(name = "CODIGO_AVION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Avion avion;
    @JoinColumn(name = "CODIGO_SILLA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Silla silla;

    public Vuelo() {
    }

    public Vuelo(VueloPK vueloPK) {
        this.vueloPK = vueloPK;
    }

    public Vuelo(VueloPK vueloPK, String disponible) {
        this.vueloPK = vueloPK;
        this.disponible = disponible;
    }

    public Vuelo(String codigoAvion, String codigoSilla, Date fecha) {
        this.vueloPK = new VueloPK(codigoAvion, codigoSilla, fecha);
    }

    public VueloPK getVueloPK() {
        return vueloPK;
    }

    public void setVueloPK(VueloPK vueloPK) {
        this.vueloPK = vueloPK;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vueloPK != null ? vueloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.vueloPK == null && other.vueloPK != null) || (this.vueloPK != null && !this.vueloPK.equals(other.vueloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.entidades.Vuelo[ vueloPK=" + vueloPK + " ]";
    }
    
}
