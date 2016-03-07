/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.*;

/**
 *
 * @author richou
 */
public class TestJPQL1 {
    
    public static void main(String[] args) {
        /*
        Persistence management
        */
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POO_TP1_PU");
        EntityManager em = emf.createEntityManager();
        for(Doctor d : getDoctorsFromService(em, "CARDIOLOGIE")) {
            System.out.println(d);
        }
    }
    
    public static List<Doctor> getDoctorsFromService(EntityManager em, String serviceName) {
        Query query = em.createQuery("SELECT d FROM Doctor d WHERE d.service.name = :service");
        query.setParameter("service", serviceName);
        
        return query.getResultList();
    }
}
