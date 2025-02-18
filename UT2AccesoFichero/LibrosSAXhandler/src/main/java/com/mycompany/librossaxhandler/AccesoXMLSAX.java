/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librossaxhandler;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author martin
 */
public class AccesoXMLSAX {

    private int cantidadlibros = 1;
    SAXParser parser;

    public AccesoXMLSAX() {
        cantidadlibros++;
    }

    public int getCantidadLibros() {
        return cantidadlibros++;
    }

    public int parsearXMLconLibrosSAXhandler(File f) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();

            parser = factory.newSAXParser();

            LibrosSAXhandler sh = new LibrosSAXhandler();
            
            parser.parse(f, sh);
            cantidadlibros++;
            return 0;

        } catch (Exception e) {

            e.printStackTrace();

            return -1;

        }

    }

    public int parsearXMLconTitulosSAXhandler(File f) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();

            parser = factory.newSAXParser();

            TitulosSAXhanler sh = new TitulosSAXhanler();

            parser.parse(f, sh);

            return 0;

        } catch (Exception e) {

            e.printStackTrace();

            return -1;

        }

    }

}
