/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Agente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class AgenteFacade extends AbstractFacade<Agente> implements AgenteFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgenteFacade() {
        super(Agente.class);
    }

    @Override
    public boolean checkCuenta(String usuario, String contraseña) {
        Query q = em.createQuery("SELECT a FROM Agente a WHERE a.usuario = :usuario AND a.contraseña = :contraseña");
        q.setParameter("usuario", usuario);
        q.setParameter("contraseña", contraseña);
        return q.getResultList().size() > 0;
    }

    @Override
    public Agente getAgente(String usuario, String contraseña) {
        Query q = em.createQuery("SELECT a FROM Agente a WHERE a.usuario = :usuario AND a.contraseña = :contraseña");
        q.setParameter("usuario", usuario);
        q.setParameter("contraseña", contraseña);
        return (Agente)q.getResultList().get(0);
    }
    
}
