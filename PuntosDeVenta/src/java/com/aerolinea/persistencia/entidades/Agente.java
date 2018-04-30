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
@Table(name = "agente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a")
    , @NamedQuery(name = "Agente.findByTipoId", query = "SELECT a FROM Agente a WHERE a.agentePK.tipoId = :tipoId")
    , @NamedQuery(name = "Agente.findById", query = "SELECT a FROM Agente a WHERE a.agentePK.id = :id")
    , @NamedQuery(name = "Agente.findByNombres", query = "SELECT a FROM Agente a WHERE a.nombres = :nombres")
    , @NamedQuery(name = "Agente.findByApellidos", query = "SELECT a FROM Agente a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Agente.findByTelefono", query = "SELECT a FROM Agente a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Agente.findByUsuario", query = "SELECT a FROM Agente a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "Agente.findByContrase\u00f1a", query = "SELECT a FROM Agente a WHERE a.contrase\u00f1a = :contrase\u00f1a")})
public class Agente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AgentePK agentePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CONTRASE\u00d1A")
    private String contraseña;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agente", fetch = FetchType.EAGER)
    private List<Pasaje> pasajeList;

    public Agente() {
    }

    public Agente(AgentePK agentePK) {
        this.agentePK = agentePK;
    }

    public Agente(AgentePK agentePK, String nombres, String apellidos, String usuario, String contraseña) {
        this.agentePK = agentePK;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Agente(String tipoId, String id) {
        this.agentePK = new AgentePK(tipoId, id);
    }

    public AgentePK getAgentePK() {
        return agentePK;
    }

    public void setAgentePK(AgentePK agentePK) {
        this.agentePK = agentePK;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @XmlTransient
    public List<Pasaje> getPasajeList() {
        return pasajeList;
    }

    public void setPasajeList(List<Pasaje> pasajeList) {
        this.pasajeList = pasajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agentePK != null ? agentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.agentePK == null && other.agentePK != null) || (this.agentePK != null && !this.agentePK.equals(other.agentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aerolinea.persistencia.entidades.Agente[ agentePK=" + agentePK + " ]";
    }
    
}
