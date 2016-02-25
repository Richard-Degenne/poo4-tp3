/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Service;

/**
 *
 * @author richou
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger("");
        /*
        Persistence management
        */
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POO_TP1_PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        
        /*
        Main
        */
        
        try {
            et.begin();
            
            Service s = new Service("Radiologie", "Bâtiment A");
            em.persist(s);
            
            et.commit();
            log.log(Level.INFO, "Transaction committed.");
        }
        catch(Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            log.log(Level.SEVERE, "Transaction aborted.");
            et.rollback();
        }
    }
    
}
