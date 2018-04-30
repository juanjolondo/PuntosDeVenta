/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Oficina;
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
public class OficinaFacade extends AbstractFacade<Oficina> implements OficinaFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficinaFacade() {
        super(Oficina.class);
    }

    @Override
    public Oficina getOficina(String codigoOficina) {
        return em.find(Oficina.class, codigoOficina);
    }

    @Override
    public List<Oficina> getAllOficinas() {
        Query q = em.createNamedQuery("Oficina.findAll");
        return q.getResultList();
    }
    
}
