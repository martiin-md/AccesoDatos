/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto1xmljavadom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author martin
 */
public class Proyecto1XMLJavaDom {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        ProyectoAccesoDom acD = new ProyectoAccesoDom();

        File f = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\Proyecto1XMLJavaDom\\books.xml");

        acD.generarDOM(f);

        acD.añadir("Martin", "Acceso Datos", "DAM", "31.00€", "15/10/2024", "Libro para el curso de Segundo DAM de Acceso Datos");
        acD.añadir("Martin", "Moviles y Multimedia", "DAM", "21.00€", "15/10/2024", "Libro para el curso de Segundo DAM de Moviles y Multimedia");

        acD.eliminar("Acceso Datos");
//        acD.eliminar("Moviles y Multimedia");

        acD.guardarXML("C:\\Users\\marti\\OneDrive\\Escritorio\\Proyecto1XMLJavaDom\\books.xml");

    }
}
