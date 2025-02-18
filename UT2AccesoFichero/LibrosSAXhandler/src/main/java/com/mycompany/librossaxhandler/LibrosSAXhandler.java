/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.librossaxhandler;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author martin
 */
public class LibrosSAXhandler extends DefaultHandler {

    private int catidadLibros = 1;
    String titulo = "";
    String autor = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("LISTADO LIBROS");
        System.out.println("==============");
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) throws SAXException {

        if (qName.equals("Libro")) {

            System.out.println("Publicado en: " + atts.getValue(atts.getQName(0)));//extrae el primer atributo 
            System.out.println("LIBRO Nº" + catidadLibros);
            catidadLibros++;
        } else if (qName.equals("Titulo")) {

            System.out.print("El título es: " + titulo);//aún no sabemos cúal es el título, eso lo sabremos en el evento characters			 

        } else if (qName.equals("Autor")) {

            System.out.print("Autor: "+autor);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Libro")) {

            System.out.println("\n-----------------------");

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String car = new String(ch, start, length);

        car = car.replaceAll("\t", "");//quita todos los tabuladores 

        car = car.replaceAll("\n", "");

        System.out.print(car);

    }

}
