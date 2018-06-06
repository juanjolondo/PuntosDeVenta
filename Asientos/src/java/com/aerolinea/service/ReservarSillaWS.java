/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.service;

import com.aerolinea.dao.VueloFacadeLocal;
import com.aerolinea.entidades.Vuelo;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "ReservarSillaWS")
public class ReservarSillaWS {

    @EJB
    private VueloFacadeLocal vueloFacade;

    @WebMethod(operationName = "asignarSilla")
    public String asignarSilla(@WebParam(name = "accesoClase") String accesoClase, @WebParam(name = "fecha") Date fecha) {
        List<Vuelo> vuelos = vueloFacade.findAll();
        String silla = "";
        
        if(vuelos.size() > 0){
            for(Vuelo v : vuelos){
                if((v.getVueloPK().getFecha().compareTo(fecha) != 0) || !(v.getSilla().getTipo().equalsIgnoreCase(accesoClase)) || ("No".equalsIgnoreCase(v.getDisponible()))){
                    vuelos.remove(v);
                }
            }
            
            if(vuelos.isEmpty()){
                silla = "No hay sillas disponibles";
            }else{
                Vuelo v = vuelos.get(0);
                silla = v.getAvion().getCodigo() + " - " + v.getSilla().getCodigo();
            }
        }       
        
        return silla;
    }

}
