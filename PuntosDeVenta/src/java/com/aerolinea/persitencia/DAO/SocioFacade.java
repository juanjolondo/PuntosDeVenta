/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Socio;
import com.aerolinea.persistencia.entidades.SocioPK;
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
public class SocioFacade extends AbstractFacade<Socio> implements SocioFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFacade() {
        super(Socio.class);
    }

    @Override
    public boolean checkSocio(String tipoId, String id) {
        return (getSocio(tipoId, id) != null);
    }

    @Override
    public Socio getSocio(String tipoId, String id) {
        SocioPK pk = new SocioPK(tipoId, id);
        return em.find(Socio.class, pk);
    }

    @Override
    public Socio updateSocio(Socio socio) {
        return em.merge(socio);
    }

    @Override
    public boolean deleteSocio(String tipoId, String id) {
        Socio s = getSocio(tipoId, id);
        em.remove(s);
        return !(checkSocio(tipoId, id));
    }

    @Override
    public boolean newSocio(Socio socio) {
        em.persist(socio);
        return checkSocio(socio.getSocioPK().getTipoId(), socio.getSocioPK().getId());
    }

    @Override
    public List<Socio> getAllSocios() {
        Query q = em.createNamedQuery("Socio.findAll");
        return q.getResultList();
    }
    
}
