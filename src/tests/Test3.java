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
public class Test3 {
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
            Doctor med1 = new Doctor("Trancen", "Jean", 2135.23);
            Doctor med2 = new Doctor("Gator", "Coralie", 3156.00);
            Doctor med3 = new Doctor("Gator", "Magalie", 2545.37);
            Doctor med4 = new Doctor("Hitmieu", "Helmer", 1873.30);
            Doctor med5 = new Doctor("Cambronne", "Maude", 3765.20);
            Doctor med6 = new Doctor("Haybon", "Sylvain", 2980.00);
            serv1.addDoctor(med1);
            serv1.addDoctor(med2);
            serv1.addDoctor(med3);
            serv2.addDoctor(med4);
            serv3.addDoctor(med5);
            serv3.addDoctor(med6);
            med4.addManagedService(serv2);
            med5.addManagedService(serv1);
            med5.addManagedService(serv3);
            
            med1.setManager(med2);
            med3.setManager(med2);
            med5.setManager(med6);
            
            System.out.println(med1);
            System.out.println(med2);
            System.out.println(med3);
            System.out.println(med4);
            System.out.println(med5);
            System.out.println(med6);
            System.out.println(serv1);
            System.out.println(serv2);
            System.out.println(serv3);
            
            
            em.persist(serv1);
            em.persist(serv2);
            em.persist(serv3);
            
            et.commit();
            log.log(Level.INFO, "Transaction committed.");
        }
        catch(Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
            log.log(Level.SEVERE, "Transaction aborted.");
            et.rollback();
        }
    }
}
