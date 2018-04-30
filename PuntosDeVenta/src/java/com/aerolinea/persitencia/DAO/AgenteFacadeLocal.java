/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.persitencia.DAO;

import com.aerolinea.persistencia.entidades.Agente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface AgenteFacadeLocal {

    void create(Agente agente);

    void edit(Agente agente);

    void remove(Agente agente);

    Agente find(Object id);

    List<Agente> findAll();

    List<Agente> findRange(int[] range);

    int count();

    boolean checkCuenta(String usuario, String contraseña);

    Agente getAgente(String usuario, String contraseña);
    
}
