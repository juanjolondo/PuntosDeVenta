/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Computador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ComputadorFacadeLocal {

    void create(Computador computador);

    void edit(Computador computador);

    void remove(Computador computador);

    Computador find(Object id);

    List<Computador> findAll();

    List<Computador> findRange(int[] range);

    int count();

    Computador getComputador(String codigoOficina, String codigoPc);

    List<Computador> getAllComputadores();
    
}
