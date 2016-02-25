/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author richou
 */
@Entity
public class Service implements Serializable {

    /*
    Attributes
    */
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SERVNO")
    private Long id;
    
    @Column(unique=true,
            nullable=false,
            length=50)
    private String name;
    
    @Column(nullable=false,
            length=50)
    private String localization;

    
    /*
    Constructors
    */

    public Service() {
    }

    public Service(String name, String localization) {
        this.name = name.toUpperCase();
        this.localization = localization.toUpperCase();
    }
    
    
    
    /*
    Methods
    */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Service\n" + 
                "  id= " + id + "\n" +
                "  name= " + name +"\n" +
                "  localization= " + localization +"\n";
    }
    
}
