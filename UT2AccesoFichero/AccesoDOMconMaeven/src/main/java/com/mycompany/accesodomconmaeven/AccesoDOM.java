package com.mycompany.accesodomconmaeven;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.*;//clase File 

/**
 *
 * @author martin
 */

public class AccesoDOM {

    Document doc;

    public int abriXMLaDOM(File f) {

        try {

            System.out.println("Abriendo archivo XML file y generando DOM ....");

            //creamos nuevo objeto DocumentBuilder al que apunta la variable factory 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //ignorar comentarios y espacios blancos 
            factory.setIgnoringComments(true);

            factory.setIgnoringElementContentWhitespace(true);

            //DocumentBuilder tiene el método parse que es el que genera DOM en memoria 
            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = builder.parse(f);

            // ahora doc apunta al arbol DOM y podemos recorrerlo 
            System.out.println("DOM creado con éxito.");

            return 0;//si el método funciona 

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");

        } catch (IOException e) {
            System.out.println("");

        } catch (Exception e) {

            System.out.println(e);

            return -1;//if the method aborta en alg�n punto 

        }
        return 0;

    }

    public void mostrarXML() {
        System.out.println(doc.toString());

    }
}

//fin clase 

