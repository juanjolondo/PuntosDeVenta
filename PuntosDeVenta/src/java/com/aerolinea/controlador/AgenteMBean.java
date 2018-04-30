package com.aerolinea.controlador;

import com.aerolinea.persistencia.entidades.Agente;
import com.aerolinea.persitencia.DAO.AgenteFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "agenteMBean")
@SessionScoped
public class AgenteMBean implements Serializable{

    @EJB
    private AgenteFacadeLocal agenteFacade;

    private Agente agente;
    
    public AgenteMBean() {
    }
    
    public String login(String usuario, String contraseña){
        String url;
        if(agenteFacade.checkCuenta(usuario, contraseña)){
            agente = agenteFacade.getAgente(usuario, contraseña);
            url = "inicio";
        }else{
            agente = null;
            url = "index";
        }
        return url;
    }
    
    public Agente getAgente(){
        return agente;
    }
    
}
