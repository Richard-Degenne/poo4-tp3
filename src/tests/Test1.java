/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Doctor;
import model.Service;

/**
 *
 * @author richou
 */
public class Test1 {
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
            Service serv1 = new Service("Cardiologie", "Bat A, 1er étage");
            Service serv2 = new Service("Pneumologie", "Bat B, 1er étage");
            Service serv3 = new Service("Urgence", "Bat C, 1er étage");
            em.persist(serv1);
            em.persist(serv2);
            serv1.setLocalization("Bat D, 2ème étage");
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
