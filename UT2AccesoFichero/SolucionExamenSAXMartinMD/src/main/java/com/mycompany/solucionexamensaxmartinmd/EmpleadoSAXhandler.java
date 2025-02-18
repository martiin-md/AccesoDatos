/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solucionexamensaxmartinmd;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author martin
 */
public class EmpleadoSAXhandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    private String currentElement = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("==================");
        System.out.println("LISTA DE EMPLEADOS");
        System.out.println("==================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("empleado".equalsIgnoreCase(qName)) {
            String fechaContratacion = attributes.getValue("fecha_contratacion");
            System.out.println("Fecha de contratación: " + fechaContratacion);
        }
        currentValue.setLength(0); // Limpiar el buffer
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(new String(ch, start, length).trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!currentValue.toString().isEmpty()) {
            switch (qName.toLowerCase()) {
                case "nombre":
                    System.out.println("Nombre: " + currentValue);
                    break;
                case "puesto":
                    System.out.println("Puesto: " + currentValue);
                    break;
                case "departamento":
                    System.out.println("Departamento: " + currentValue);
                    break;
                case "salario":
                    System.out.println("Salario: " + currentValue);
                    break;
                case "email":
                    System.out.println("Email: " + currentValue);
                    break;
                case "telefono":
                    System.out.println("Teléfono: " + currentValue);
                    break;
                case "supervisor":
                    System.out.println("Supervisor: " + currentValue);
                    break;
                case "empleado":
                    System.out.println("----------------------------");
                    break;
            }
        }
        currentElement = "";
    }
}
