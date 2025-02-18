/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.accesodomconmaeven;

import java.io.File;

/**
 *
 * @author martin
 */
public class AccesoDOMconMaeven {

    public static void main(String[] args) {
        
        AccesoDOM a = new AccesoDOM();
        
        File f = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\AccesoDOMconMaeven\\Libros.xml");
        
        a.abriXMLaDOM(f);
        a.mostrarXML();

    }
}
