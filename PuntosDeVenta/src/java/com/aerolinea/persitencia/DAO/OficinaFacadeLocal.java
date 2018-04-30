/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Oficina;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface OficinaFacadeLocal {

    void create(Oficina oficina);

    void edit(Oficina oficina);

    void remove(Oficina oficina);

    Oficina find(Object id);

    List<Oficina> findAll();

    List<Oficina> findRange(int[] range);

    int count();

    Oficina getOficina(String codigoOficina);

    List<Oficina> getAllOficinas();
    
}
