/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persistencia.entidades;

import java.io.Serializable;
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
@Table(name = "computador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computador.findAll", query = "SELECT c FROM Computador c")
    , @NamedQuery(name = "Computador.findByCodigoOficina", query = "SELECT c FROM Computador c WHERE c.computadorPK.codigoOficina = :codigoOficina")
    , @NamedQuery(name = "Computador.findByCodigoPc", query = "SELECT c FROM Computador c WHERE c.computadorPK.codigoPc = :codigoPc")
    , @NamedQuery(name = "Computador.findByProcesador", query = "SELECT c FROM Computador c WHERE c.procesador = :procesador")
    , @NamedQuery(name = "Computador.findByRam", query = "SELECT c FROM Computador c WHERE c.ram = :ram")
    , @NamedQuery(name = "Computador.findByDiscoDuro", query = "SELECT c FROM Computador c WHERE c.discoDuro = :discoDuro")
    , @NamedQuery(name = "Computador.findBySo", query = "SELECT c FROM Computador c WHERE c.so = :so")})
public class Computador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComputadorPK computadorPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PROCESADOR")
    private String procesador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "RAM")
    private String ram;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DISCO_DURO")
    private String discoDuro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "SO")
    private String so;
    @JoinColumn(name = "CODIGO_OFICINA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Oficina oficina;

    public Computador() {
    }

    public Computador(ComputadorPK computadorPK) {
        this.computadorPK = computadorPK;
    }

    public Computador(ComputadorPK computadorPK, String procesador, String ram, String discoDuro, String so) {
        this.computadorPK = computadorPK;
        this.procesador = procesador;
        this.ram = ram;
        this.discoDuro = discoDuro;
        this.so = so;
    }

    public Computador(String codigoOficina, String codigoPc) {
        this.computadorPK = new ComputadorPK(codigoOficina, codigoPc);
    }

    public ComputadorPK getComputadorPK() {
        return computadorPK;
    }

    public void setComputadorPK(ComputadorPK computadorPK) {
        this.computadorPK = computadorPK;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (computadorPK != null ? computadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Computador)) {
            return false;
        }
        Computador other = (Computador) object;
        if ((this.computadorPK == null && other.computadorPK != null) || (this.computadorPK != null && !this.computadorPK.equals(other.computadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.Computador[ computadorPK=" + computadorPK + " ]";
    }
    
}
