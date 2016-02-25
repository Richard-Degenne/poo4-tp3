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
@Table(uniqueConstraints = {
        @UniqueConstraint (
            columnNames={"FIRSTNAME", "LASTNAME"}
        )
})

public class Doctor implements Serializable {
    
    /*
    Attributes
    */
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    
    private String lastName;
    
    @Column(precision=5,scale=2)
    private double wage;
    
    @ManyToOne
    private Service service;
    
    @OneToMany(mappedBy="manager")
    private Collection<Service> managedServices;
    
    
    /*
    Constructors
    */

    public Doctor() {
        managedServices = new HashSet<>();
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

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.firstName == null && other.firstName != null) || (this.firstName != null && !this.firstName.equals(other.firstName))) {
            return false;
        }
        if ((this.lastName == null && other.lastName != null) || (this.lastName != null && !this.lastName.equals(other.lastName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Doctor\n" + 
                "  id= " + id + "\n" +
                "  name= " + firstName+" "+lastName +"\n" +
                "  wage= " + wage +"\n" +
                "  manager of= ";
        
        for(Service s : managedServices) {
            result += s.getName()+", ";
        }
        return result;
    }
    
}
