/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.financiero.service;

import com.sistema.financiero.DAO.CompraDAO;
import com.sistema.financiero.DAO.TarjetaDAO;
import com.sistema.financiero.persistencia.Compra;
import com.sistema.financiero.persistencia.CompraId;
import com.sistema.financiero.persistencia.Tarjeta;
import java.time.Instant;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(serviceName = "AutorizadorWS")
@XmlSeeAlso({Tarjeta.class})
public class AutorizadorWS {

    @WebMethod(operationName = "autorizarTarjeta")
    public boolean autorizarTarjeta(@WebParam(name = "numeroTarjeta") String numeroTarjeta, @WebParam(name = "precio") int precio) {
        TarjetaDAO tarjetaDao = new TarjetaDAO();
        Tarjeta t = tarjetaDao.consultarTarjeta(numeroTarjeta);
        boolean result = false;
        
        if(t != null){
            int suma = 0;
            for(Compra c : t.getCompras()){
                if("Activa".equalsIgnoreCase(c.getEstado())){
                    suma = c.getPrecioCompra() + suma;
                }
            }
            if((suma + precio) <= t.getCupo()){
                CompraDAO compraDao = new CompraDAO();
                System.out.println("Se autorizó la compra");
                Compra c = new Compra(new CompraId(numeroTarjeta, Date.from(Instant.now())), t, "Vuelo de Aerolinea", precio, "Activa");
                compraDao.ingresarCompra(c);
                result = true;
            }
        }else{
            System.out.println("La tarjeta con número " + numeroTarjeta + " no existe");
        }
        
        return result;
    }
}
