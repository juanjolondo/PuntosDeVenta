package com.aerolinea.controlador;

import com.aerolinea.persistencia.entidades.Agente;
import com.aerolinea.persistencia.entidades.Pasaje;
import com.aerolinea.persitencia.DAO.PasajeFacadeLocal;
import com.aerolinea.persitencia.DAO.SocioFacadeLocal;
import com.aerolinea.persitencia.DAO.ViajeFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "pasajeMBean")
@SessionScoped
public class PasajeMBean implements Serializable{

    @EJB
    private ViajeFacadeLocal viajeFacade;

    @EJB
    private SocioFacadeLocal socioFacade;

    @EJB
    private PasajeFacadeLocal pasajeFacade;
    
    private Pasaje pasaje;

    public PasajeMBean() {
    }
    
    public Pasaje getPasaje(){
        return pasaje;
    }
    
    public String comprarPasaje(String codigoPasaje, String medioPago, Date fecha, String silla, String codigoViaje, String tipoIdSocio, String idSocio, Agente agente){
        if(socioFacade.checkSocio(tipoIdSocio, idSocio)){
            Pasaje p = new Pasaje();
            p.setCodigoPasaje(codigoPasaje);
            p.setMedioPago(medioPago);
            p.setFecha(fecha);
            p.setCodigoViaje(viajeFacade.getViaje(codigoViaje));
            p.setSocio(socioFacade.getSocio(tipoIdSocio, idSocio));
            p.setAgente(agente);
            
            if("Crédito".equalsIgnoreCase(medioPago)){
                p = pasajeFacade.autorizarTarjeta(p);
            }
            
            pasajeFacade.asignarSilla(p);
            
            if(pasajeFacade.newPasaje(p)){
                System.out.println("Pasaje registrado");
            }else{
                System.out.println("No se pudo ingresar el pasaje, por favor inténtelo nuevamente");
            }
        }else{
            System.out.println("El socio no se encuentra registrado");
        }
        return "inicio";
    }
    
}
