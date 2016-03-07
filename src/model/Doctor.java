/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author richou
 */

@Entity
public class Doctor extends Person implements Serializable{
    
    /*
    Attributes
    */
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(precision=5,scale=2)
    private double wage;
    
    @ManyToOne
    private Service service;
    
    @OneToMany(mappedBy="manager")
    private Collection<Service> managedServices;
    
    @ManyToOne
    private Doctor manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Doctor> subordinates;
    
    /*
    Constructors
    */

    public Doctor() {
        managedServices = new HashSet<>();
        subordinates = new HashSet<>();
    }

    public Doctor(String firstName, String lastName, double wage) {
        this();
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.wage = (wage>0) ? wage : 0;
    }
    
    /*
    Methods
     */

    
     public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = (wage>0) ? wage : 0;
    } 

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    public boolean addManagedService(Service service) {
        Doctor old_d = service.getManager();
        
        if(managedServices.add(service)) {
            if(old_d != null) {
                old_d.managedServices.remove(service);
            }
            service.setManager(this);
        }
        return false;
    }
    
    public Collection<Service> getManagedServices() {
        return new HashSet<>(managedServices);
    }
    
    public Doctor getManager() {
        return manager;
    }
    
    public boolean setManager(Doctor doctor) {
        if(doctor == this)
            return false;
        if(doctor == manager)
            return true;
        
        if(manager != null) {
            manager.subordinates.remove(this);
        }
        doctor.subordinates.add(this);
        manager = doctor;
        return true;
    }


    @Override
    public String toString() {
        String result = "Doctor\n" + 
                "  id= " + id + "\n" +
                "  name= " + firstName+" "+lastName +"\n" + 
                "  manager= " + ((manager == null) ? "-" : (manager.firstName + " " + manager.lastName)) + "\n" +
                "  wage= " + wage +"\n" +
                "  managed services= ";
        
        for(Service s : managedServices) {
            result += s.getName()+", ";
        }
        result += "\n";
        
        result += "  subordinates= ";
        for(Doctor d : subordinates) {
            result += d.firstName + " " + d.lastName + ", ";
        }
        return result;
    }
}
