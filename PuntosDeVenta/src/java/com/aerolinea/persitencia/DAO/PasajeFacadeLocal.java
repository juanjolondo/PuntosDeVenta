/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Pasaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface PasajeFacadeLocal {

    void create(Pasaje pasaje);

    void edit(Pasaje pasaje);

    void remove(Pasaje pasaje);

    Pasaje find(Object id);

    List<Pasaje> findAll();

    List<Pasaje> findRange(int[] range);

    int count();

    Pasaje getPasaje(String codigoPasaje);

    Pasaje asignarSilla(Pasaje pasaje);

    boolean newPasaje(Pasaje pasaje);

    Pasaje autorizarTarjeta(Pasaje pasaje);
    
}
