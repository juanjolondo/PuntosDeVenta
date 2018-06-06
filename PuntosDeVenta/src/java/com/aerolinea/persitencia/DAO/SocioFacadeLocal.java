/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Socio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface SocioFacadeLocal {

    void create(Socio socio);

    void edit(Socio socio);

    void remove(Socio socio);

    Socio find(Object id);

    List<Socio> findAll();

    List<Socio> findRange(int[] range);

    int count();

    boolean checkSocio(String tipoId, String id);

    Socio getSocio(String tipoId, String id);

    Socio updateSocio(Socio socio);

    boolean deleteSocio(String tipoId, String id);

    boolean newSocio(Socio socio);

    List<Socio> getAllSocios();
    
}
