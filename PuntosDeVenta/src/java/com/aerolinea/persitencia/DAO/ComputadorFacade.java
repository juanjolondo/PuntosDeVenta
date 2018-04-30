/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Computador;
import com.aerolinea.persistencia.entidades.ComputadorPK;
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
public class ComputadorFacade extends AbstractFacade<Computador> implements ComputadorFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComputadorFacade() {
        super(Computador.class);
    }

    @Override
    public Computador getComputador(String codigoOficina, String codigoPc) {
        ComputadorPK pk = new ComputadorPK(codigoOficina, codigoPc);
        return em.find(Computador.class, pk);
    }

    @Override
    public List<Computador> getAllComputadores() {
        Query q = em.createNamedQuery("Computador.findAll");
        return q.getResultList();
    }
    
}
