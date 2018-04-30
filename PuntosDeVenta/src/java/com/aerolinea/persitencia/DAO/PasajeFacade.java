/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Pasaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class PasajeFacade extends AbstractFacade<Pasaje> implements PasajeFacadeLocal {

    @PersistenceContext(unitName = "PuntosDeVentaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasajeFacade() {
        super(Pasaje.class);
    }

    @Override
    public Pasaje getPasaje(String codigoPasaje) {
        return em.find(Pasaje.class, codigoPasaje);
    }

    @Override
    public Pasaje asignarSilla(Pasaje pasaje) {
        int categoriaSocio = pasaje.getSocio().getCategoria().getTipo();
        
        // TODO: Solicitar reserva de silla con parámetro "categoriaSocio" y que se almacena en la variable "silla".
        String silla = "";
        
        pasaje.setSilla(silla);
        return pasaje;
    }

    @Override
    public boolean newPasaje(Pasaje pasaje) {
        em.persist(pasaje);
        return getPasaje(pasaje.getCodigoPasaje()) != null;
    }

    @Override
    public Pasaje autorizarTarjeta(Pasaje pasaje) {
        return pasaje;
    }
    
}
