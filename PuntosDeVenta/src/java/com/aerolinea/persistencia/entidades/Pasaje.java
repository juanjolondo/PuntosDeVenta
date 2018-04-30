/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "pasaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasaje.findAll", query = "SELECT p FROM Pasaje p")
    , @NamedQuery(name = "Pasaje.findByCodigoPasaje", query = "SELECT p FROM Pasaje p WHERE p.codigoPasaje = :codigoPasaje")
    , @NamedQuery(name = "Pasaje.findByMedioPago", query = "SELECT p FROM Pasaje p WHERE p.medioPago = :medioPago")
    , @NamedQuery(name = "Pasaje.findByFecha", query = "SELECT p FROM Pasaje p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pasaje.findBySilla", query = "SELECT p FROM Pasaje p WHERE p.silla = :silla")})
public class Pasaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_PASAJE")
    private String codigoPasaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MEDIO_PAGO")
    private String medioPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SILLA")
    private String silla;
    @JoinColumn(name = "CODIGO_VIAJE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Viaje codigoViaje;
    @JoinColumns({
        @JoinColumn(name = "TIPO_ID_SOCIO", referencedColumnName = "TIPO_ID")
        , @JoinColumn(name = "ID_SOCIO", referencedColumnName = "ID")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Socio socio;
    @JoinColumns({
        @JoinColumn(name = "TIPO_ID_AGENTE", referencedColumnName = "TIPO_ID")
        , @JoinColumn(name = "ID_AGENTE", referencedColumnName = "ID")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Agente agente;

    public Pasaje() {
    }

    public Pasaje(String codigoPasaje) {
        this.codigoPasaje = codigoPasaje;
    }

    public Pasaje(String codigoPasaje, String medioPago, Date fecha, String silla) {
        this.codigoPasaje = codigoPasaje;
        this.medioPago = medioPago;
        this.fecha = fecha;
        this.silla = silla;
    }

    public String getCodigoPasaje() {
        return codigoPasaje;
    }

    public void setCodigoPasaje(String codigoPasaje) {
        this.codigoPasaje = codigoPasaje;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSilla() {
        return silla;
    }

    public void setSilla(String silla) {
        this.silla = silla;
    }

    public Viaje getCodigoViaje() {
        return codigoViaje;
    }

    public void setCodigoViaje(Viaje codigoViaje) {
        this.codigoViaje = codigoViaje;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPasaje != null ? codigoPasaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasaje)) {
            return false;
        }
        Pasaje other = (Pasaje) object;
        if ((this.codigoPasaje == null && other.codigoPasaje != null) || (this.codigoPasaje != null && !this.codigoPasaje.equals(other.codigoPasaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.Pasaje[ codigoPasaje=" + codigoPasaje + " ]";
    }
    
}
