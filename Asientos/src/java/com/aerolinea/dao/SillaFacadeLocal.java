/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidades.Silla;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface SillaFacadeLocal {

    void create(Silla silla);

    void edit(Silla silla);

    void remove(Silla silla);

    Silla find(Object id);

    List<Silla> findAll();

    List<Silla> findRange(int[] range);

    int count();
    
}
