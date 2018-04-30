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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "socio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s")
    , @NamedQuery(name = "Socio.findByTipoId", query = "SELECT s FROM Socio s WHERE s.socioPK.tipoId = :tipoId")
    , @NamedQuery(name = "Socio.findById", query = "SELECT s FROM Socio s WHERE s.socioPK.id = :id")
    , @NamedQuery(name = "Socio.findByNombres", query = "SELECT s FROM Socio s WHERE s.nombres = :nombres")
    , @NamedQuery(name = "Socio.findByApellidos", query = "SELECT s FROM Socio s WHERE s.apellidos = :apellidos")
    , @NamedQuery(name = "Socio.findByTelefono", query = "SELECT s FROM Socio s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Socio.findByMillas", query = "SELECT s FROM Socio s WHERE s.millas = :millas")})
public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SocioPK socioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MILLAS")
    private int millas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio", fetch = FetchType.EAGER)
    private List<Pasaje> pasajeList;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "TIPO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Categoria categoria;

    public Socio() {
    }

    public Socio(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public Socio(SocioPK socioPK, String nombres, String apellidos, int millas) {
        this.socioPK = socioPK;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.millas = millas;
    }

    public Socio(String tipoId, String id) {
        this.socioPK = new SocioPK(tipoId, id);
    }

    public SocioPK getSocioPK() {
        return socioPK;
    }

    public void setSocioPK(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMillas() {
        return millas;
    }

    public void setMillas(int millas) {
        this.millas = millas;
    }

    @XmlTransient
    public List<Pasaje> getPasajeList() {
        return pasajeList;
    }

    public void setPasajeList(List<Pasaje> pasajeList) {
        this.pasajeList = pasajeList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioPK != null ? socioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.socioPK == null && other.socioPK != null) || (this.socioPK != null && !this.socioPK.equals(other.socioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.Socio[ socioPK=" + socioPK + " ]";
    }
    
}
