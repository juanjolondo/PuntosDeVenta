package com.sistema.financiero.DAO;

import com.sistema.financiero.persistencia.Compra;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CompraDAO {
    
    public void ingresarCompra(Compra c){
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        
        try{            
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();
            session.close();            
        }catch(HibernateException e){
            tx.rollback();
            throw new RuntimeException("No se pudo guardar la compra");
        }
    }
    
}
