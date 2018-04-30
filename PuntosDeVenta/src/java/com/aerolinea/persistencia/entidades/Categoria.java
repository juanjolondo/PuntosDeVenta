/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByTipo", query = "SELECT c FROM Categoria c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Categoria.findByMillasPorTrayecto", query = "SELECT c FROM Categoria c WHERE c.millasPorTrayecto = :millasPorTrayecto")
    , @NamedQuery(name = "Categoria.findByCantEquipaje", query = "SELECT c FROM Categoria c WHERE c.cantEquipaje = :cantEquipaje")
    , @NamedQuery(name = "Categoria.findByAccesoClase", query = "SELECT c FROM Categoria c WHERE c.accesoClase = :accesoClase")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private Integer tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MILLAS_POR_TRAYECTO")
    private int millasPorTrayecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CANT_EQUIPAJE")
    private String cantEquipaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCESO_CLASE")
    private String accesoClase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Socio> socioList;

    public Categoria() {
    }

    public Categoria(Integer tipo) {
        this.tipo = tipo;
    }

    public Categoria(Integer tipo, String nombre, int millasPorTrayecto, String cantEquipaje, String accesoClase) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.millasPorTrayecto = millasPorTrayecto;
        this.cantEquipaje = cantEquipaje;
        this.accesoClase = accesoClase;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMillasPorTrayecto() {
        return millasPorTrayecto;
    }

    public void setMillasPorTrayecto(int millasPorTrayecto) {
        this.millasPorTrayecto = millasPorTrayecto;
    }

    public String getCantEquipaje() {
        return cantEquipaje;
    }

    public void setCantEquipaje(String cantEquipaje) {
        this.cantEquipaje = cantEquipaje;
    }

    public String getAccesoClase() {
        return accesoClase;
    }

    public void setAccesoClase(String accesoClase) {
        this.accesoClase = accesoClase;
    }

    @XmlTransient
    public List<Socio> getSocioList() {
        return socioList;
    }

    public void setSocioList(List<Socio> socioList) {
        this.socioList = socioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipo != null ? tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.Categoria[ tipo=" + tipo + " ]";
    }
    
}
