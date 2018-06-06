package com.sistema.financiero.DAO;

import com.sistema.financiero.persistencia.Tarjeta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TarjetaDAO {
    
    public Tarjeta consultarTarjeta(String numeroTarjeta){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Tarjeta t = (Tarjeta)session.get(Tarjeta.class, numeroTarjeta);
 
        return t;
    }
    
}
