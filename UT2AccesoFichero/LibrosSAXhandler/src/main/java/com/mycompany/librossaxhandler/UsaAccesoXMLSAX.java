/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librossaxhandler;

import java.io.File;

/**
 *
 * @author martin
 */
public class UsaAccesoXMLSAX {

    public static void main(String[] args) {

        File f = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\Libros.xml");

        AccesoXMLSAX a = new AccesoXMLSAX();

        a.parsearXMLconLibrosSAXhandler(f);
        
        a.parsearXMLconTitulosSAXhandler(f);
        
        System.out.println("CANTIDAD: " + a.getCantidadLibros());
        
    }

}
