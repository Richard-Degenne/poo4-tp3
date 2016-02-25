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

/**
 *
 * @author richou
 */
public class Test2 {
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
            Doctor med1 = new Doctor("Trancen", "Jean", 2135.23);
            Doctor med2 = new Doctor("Gator", "Coralie", 3156.00);
            Doctor med3 = new Doctor("Gator", "Magalie", 2545.3723);
            em.persist(med1);
            em.persist(med2);
            em.persist(med3);
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
