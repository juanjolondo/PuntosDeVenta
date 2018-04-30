package com.aerolinea.controlador;

import com.aerolinea.persistencia.entidades.Socio;
import com.aerolinea.persistencia.entidades.SocioPK;
import com.aerolinea.persitencia.DAO.CategoriaFacadeLocal;
import com.aerolinea.persitencia.DAO.SocioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "socioMBean")
@SessionScoped
public class SocioMBean implements Serializable{

    @EJB
    private CategoriaFacadeLocal categoriaFacade;

    @EJB
    private SocioFacadeLocal socioFacade;
    
    private Socio socio;

    public SocioMBean() {
    }
    
    public Socio getSocio(){
        return socio;
    }
    
    public String atras(){
        socio = new Socio();
        return "inicio";
    }
    
    public String newSocio(String tipoId, String id, String nombres, String apellidos, String telefono, int categoria, int millas){
        if(!(socioFacade.checkSocio(tipoId, id))){
            Socio s = new Socio();
            s.setSocioPK(new SocioPK(tipoId, id));
            s.setNombres(nombres);
            s.setApellidos(apellidos);
            s.setTelefono(telefono);
            s.setMillas(millas);
            s.setCategoria(categoriaFacade.getCategoria(categoria));
 
            if(socioFacade.newSocio(s)){
                System.out.println("Socio ingresado");
            }else{
                System.out.println("No se pudo ingresar el socio, por favor inténtelo nuevamente");
            }
        }else{
            System.out.println("El Socio ya se encuentra registrado");
        }
        return atras();
    }
    
    public String getSocio(String tipoId, String id){
        if(socioFacade.checkSocio(tipoId, id)){
            socio = socioFacade.getSocio(tipoId, id);
        }else{
            System.out.println("El Socio no se encuentra registrado");
        }
        return "detalles";
    }
    
    public String updateSocio(String tipoId, String id, String nombres, String apellidos, String telefono, int categoria, int millas){
        if(socioFacade.checkSocio(tipoId, id)){
            Socio s = new Socio();
            s.setSocioPK(new SocioPK(tipoId, id));
            s.setNombres(nombres);
            s.setApellidos(apellidos);
            s.setTelefono(telefono);
            s.setMillas(millas);
            s.setCategoria(categoriaFacade.getCategoria(categoria));
 
            socioFacade.updateSocio(s);
        }else{
            System.out.println("El Socio no se encuentra registrado");
        }
        return atras();
    }
    
    public String deleteSocio(String tipoId, String id){
        if(socioFacade.checkSocio(tipoId, id)){
            
            if(socioFacade.deleteSocio(tipoId, id)){
                System.out.println("Socio eliminado");
            }else{
                System.out.println("No se pudo eliminar el socio, por favor inténtelo nuevamente");
            }
            
        }else{
            System.out.println("El Socio no se encuentra registrado");
        }
        return atras();
    }
    
}
