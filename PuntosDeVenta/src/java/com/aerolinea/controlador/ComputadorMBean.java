package com.aerolinea.controlador;

import com.aerolinea.persistencia.entidades.Pasaje;
import com.aerolinea.persistencia.entidades.Socio;
import com.aerolinea.persitencia.DAO.ComputadorFacadeLocal;
import java.util.List;
import javax.ejb.EJB;

public class ComputadorMBean {

    @EJB
    private ComputadorFacadeLocal computadorFacade;
    
    private List<Socio> socios;
    private List<Pasaje> pasajes;

    public ComputadorMBean() {
    }
    
    public List<Socio> getSocios(){
        return socios;
    }
    
    public List<Pasaje> getPasajes(){
        return pasajes;
    }
    
    public void sincronizarInfo(){
        computadorFacade.sincronizarInfo(socios, pasajes);
    }
    
}
