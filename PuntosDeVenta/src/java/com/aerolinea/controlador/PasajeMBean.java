package com.aerolinea.controlador;

import com.aerolinea.persistencia.entidades.Agente;
import com.aerolinea.persistencia.entidades.Pasaje;
import com.aerolinea.persistencia.entidades.Socio;
import com.aerolinea.persistencia.entidades.Viaje;
import com.aerolinea.persitencia.DAO.PasajeFacadeLocal;
import com.aerolinea.persitencia.DAO.SocioFacadeLocal;
import com.aerolinea.persitencia.DAO.ViajeFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "pasajeMBean")
@SessionScoped
public class PasajeMBean implements Serializable{

    @EJB
    private ViajeFacadeLocal viajeFacade;

    @EJB
    private SocioFacadeLocal socioFacade;

    @EJB
    private PasajeFacadeLocal pasajeFacade;
    
    private Pasaje pasaje;

    public PasajeMBean() {
    }
    
    public Pasaje getPasaje(){
        return pasaje;
    }
    
    public String comprarPasaje(String codigoPasaje, String medioPago, Date fecha, String silla, String codigoViaje, String tipoIdSocio, String idSocio, Agente agente, String numeroTarjeta){
        if(socioFacade.checkSocio(tipoIdSocio, idSocio)){
            boolean error = false;
            Viaje v = viajeFacade.getViaje(codigoViaje);
            Socio s = socioFacade.getSocio(tipoIdSocio, idSocio);
            Pasaje p = new Pasaje();
            p.setCodigoPasaje(codigoPasaje);
            p.setMedioPago(medioPago);
            p.setFecha(fecha);
            p.setCodigoViaje(v);
            p.setSocio(s);
            p.setAgente(agente);
            
            if("Crédito".equalsIgnoreCase(medioPago)){
                if(pasajeFacade.autorizarTarjeta(numeroTarjeta, Integer.parseInt(v.getPrecio()))){
                    System.out.println("La tarjeta con número " + numeroTarjeta + " autorizada.");
                }else{
                    System.out.println("La tarjeta con número " + numeroTarjeta + " no fue autorizada.");
                    error = true;
                }
            }else if("Millas".equalsIgnoreCase(medioPago)){
                int millas = p.getSocio().getMillas();
                if(millas >= 50000){                    
                    String tipoVuelo = v.getTipoVuelo();
                    int mes = fecha.getMonth();
                    String temporada;
                    int precioEnMillas = 0;
                    
                    if((mes >= 2 && mes <= 4) || (mes >= 8 && mes <= 10)){
                        temporada = "baja";
                    }else{
                        temporada = "alta";
                    }
                    
                    if("Nacional".equalsIgnoreCase(tipoVuelo)){
                        if("baja".equals(temporada)){
                            precioEnMillas = 50000;
                        }else{
                            precioEnMillas = 80000;
                        }
                    }else if("Internacional".equalsIgnoreCase(tipoVuelo)){
                        String destino = v.getDestino();
                        if("América".equalsIgnoreCase(destino)){
                            if("baja".equals(temporada)){
                                precioEnMillas = 70000;
                            }else{
                                precioEnMillas = 90000;
                            }
                        }else if("Europa".equalsIgnoreCase(destino)){
                            if("baja".equals(temporada)){
                                precioEnMillas = 90000;
                            }else{
                                precioEnMillas = 140000;
                            }
                        }
                    }
                    
                    if(millas >= precioEnMillas){
                        millas -= precioEnMillas;
                        s.setMillas(millas);
                    }else{
                        System.out.println("No cumple con la cantidad de millas: el viaje cuesta " + precioEnMillas + " millas, y el socio cuenta con " + millas + " millas");
                        error = true;
                    }
                }else{
                    System.out.println("Se debe tener un mínimo de 50.000 millas");
                    error = true;
                }
            }
            
            silla = pasajeFacade.asignarSilla(p);
            
            if("No hay sillas disponibles".equalsIgnoreCase(silla)){
                System.out.println("Las sillas para este vuelo ya están agotadas");                
                error = true;
            }else if("error".equalsIgnoreCase(silla)){      
                System.out.println("Ha ocurrido un error con la aplicación");                
                error = true;
            }else{
                System.out.println("Silla asignada: " + silla);                
                p.setSilla(silla);
            }
            
            if(!error){
                if(pasajeFacade.newPasaje(p)){
                    if(!"Millas".equalsIgnoreCase(medioPago)){
                        s.setMillas(s.getMillas() + s.getCategoria().getMillasPorTrayecto());
                    }
                    socioFacade.updateSocio(s);
                    System.out.println("Pasaje registrado");
                }else{
                    System.out.println("No se pudo registrar el pasaje, por favor inténtelo nuevamente");
                }
            }else{
                System.out.println("Ha ocurrido un error");
            }           
        }else{
            System.out.println("El socio no se encuentra registrado");
        }
        return "inicio";
    }
    
}
