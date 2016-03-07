/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author richou
 */

@Entity
public class Patient extends Person implements Serializable {
    
    /*
    Attributes
    */
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Embedded
    private Address address;
    
    /*
    Constructors
    */
    
    public Patient() {
        
    }
    
    public Patient(String firstName, String lastName) {
        this();
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
    }
    
    public Patient(String firstName, String lastName, Address address) {
        this();
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.address = address;
    }
    
    
    /*
    Methods
    */

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAddress(int number, String street, String zipcode, String city) {
        this.address.setNumber(number);
        this.address.setStreet(street);
        this.address.setZipcode(zipcode);
        this.address.setCity(city);
    }    
    
    
    @Override
    public String toString() {
        String result = "Patient\n" + 
                "  id= " + id + "\n" +
                "  name= " + firstName+" "+lastName + "\n";
        return result;
    }
}
