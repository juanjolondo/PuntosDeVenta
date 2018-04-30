/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Viaje;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class ViajeFacade extends AbstractFacade<Viaje> implements ViajeFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViajeFacade() {
        super(Viaje.class);
    }

    @Override
    public Viaje getViaje(String codigo) {
        return em.find(Viaje.class, codigo);
    }

    @Override
    public List<Viaje> getAllViajes() {
        Query q = em.createNamedQuery("Viaje.findAll");
        return q.getResultList();
    }
    
}
