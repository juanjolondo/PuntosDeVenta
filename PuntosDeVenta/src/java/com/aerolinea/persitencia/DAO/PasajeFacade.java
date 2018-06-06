/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Pasaje;
import com.aerolinea.service.ReservarSillaWS_Service;
import com.aerolinea.util.Fecha;
import com.sistema.financiero.service.AutorizadorWS_Service;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

@Stateless
public class PasajeFacade extends AbstractFacade<Pasaje> implements PasajeFacadeLocal {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_9999/Asientos/ReservarSillaWS.wsdl")
    private ReservarSillaWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_9999/SistemaFinancieroWS/AutorizadorWS.wsdl")
    private AutorizadorWS_Service service;

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
    public String asignarSilla(Pasaje pasaje) {
        String categoriaSocio = pasaje.getSocio().getCategoria().getAccesoClase();
        XMLGregorianCalendar fecha = null;
        try {
            fecha = Fecha.getXmlGregorianCalendarFromDate(pasaje.getFecha());
        } catch (DatatypeConfigurationException ex) {
            return "error";
        }
        
        return asignarSillaWs(categoriaSocio, fecha);
    }

    @Override
    public boolean newPasaje(Pasaje pasaje) {
        em.persist(pasaje);
        return getPasaje(pasaje.getCodigoPasaje()) != null;
    }

    @Override
    public boolean autorizarTarjeta(String numeroTarjeta, int precio) {
        return autorizarTarjetaWs(numeroTarjeta, precio);
    }

    @Override
    public List<Pasaje> getAllPasajes() {
        Query q = em.createNamedQuery("Pasaje.findAll");
        return q.getResultList();
    }

    @Override
    public Pasaje updatePasaje(Pasaje pasaje) {        
        return em.merge(pasaje);
    }

    private boolean autorizarTarjetaWs(java.lang.String numeroTarjeta, int precio) {
        com.sistema.financiero.service.AutorizadorWS port = service.getAutorizadorWSPort();
        return port.autorizarTarjeta(numeroTarjeta, precio);
    }

    private String asignarSillaWs(java.lang.String accesoClase, XMLGregorianCalendar fecha) {
        com.aerolinea.service.ReservarSillaWS port = service_1.getReservarSillaWSPort();
        return port.asignarSilla(accesoClase, fecha);
    }

}
