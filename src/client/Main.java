/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
        Service s = new Service("Urgences", "Bâtiment A");
        System.out.println(s);
    }
    
}
