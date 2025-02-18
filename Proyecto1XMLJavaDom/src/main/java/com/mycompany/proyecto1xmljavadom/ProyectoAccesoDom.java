/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1xmljavadom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author martin
 */
public class ProyectoAccesoDom {

    Document doc;

    public int generarDOM(File f) throws ParserConfigurationException {
        System.out.println("Se está abriendo el archivo mientras se genera también el DOM");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);  // Ignoramos comentarios
            factory.setIgnoringElementContentWhitespace(true);  // Ignoramos espacios en blanco

            DocumentBuilder builder = factory.newDocumentBuilder();

            // Aquí inicializamos el documento a partir del archivo proporcionado
            doc = builder.parse(f);
            doc.getDocumentElement().normalize();  // Normalizar el documento

            System.out.println("Se ha creado el DOM correctamente!!!!");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int añadir(String autor, String titulo, String genero, String precio, String fecha_Publi, String descripcion) {
        try {
            Element elLibro = doc.createElement("Libro");  // Creamos el nodo <Libro>
            elLibro.setAttribute("Genero", genero);
            elLibro.setAttribute("Publicado", fecha_Publi);
            elLibro.setAttribute("Descripcion", descripcion);

            // Creamos los nodos para cada uno de los atributos del libro
            Element elAutor = doc.createElement("Autor");
            elAutor.setTextContent(autor);

            Element elTitulo = doc.createElement("Titulo");
            elTitulo.setTextContent(titulo);

            Element elPrecio = doc.createElement("Precio");
            elPrecio.setTextContent(precio);

            Element elDescripcion = doc.createElement("Descripcion");
            elDescripcion.setTextContent(descripcion);

            // Añadimos los elementos al nodo <Libro>
            elLibro.appendChild(elTitulo);
            elLibro.appendChild(elAutor);
            elLibro.appendChild(elPrecio);
            elLibro.appendChild(elDescripcion);

            // Agregamos el nuevo libro a la raíz del documento
            Node raiz = doc.getDocumentElement();
            raiz.appendChild(elLibro);

            System.out.println("Se ha ingresado correctamente el siguiente libro: " + titulo + " de " + autor);

            return 0;

        } catch (Exception e) {
            System.out.println("AH OCURRIDO ALGUN ERROR AL INGRESAR EL LIBRO " + e.getMessage());
            return -1;
        }
    }

    public int eliminar(String titulo) {
        try {
            Node raiz = doc.getDocumentElement();
            NodeList listadoLibro = doc.getElementsByTagName("Libro");  // Usamos "Libro" en lugar de "book"

            for (int i = 0; i < listadoLibro.getLength(); i++) {
                Node nodoLibro = listadoLibro.item(i);

                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList hijos = nodoLibro.getChildNodes();

                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node nodoHijo = hijos.item(j);

                        // Busca el nodo <Titulo> dentro del nodo <Libro>
                        if (nodoHijo.getNodeType() == Node.ELEMENT_NODE && nodoHijo.getNodeName().equals("Titulo")) {
                            String textoTitulo = nodoHijo.getTextContent().trim();  // Elimina espacios en blanco

                            if (textoTitulo.equals(titulo)) {
                                System.out.println("Eliminando el nodo <Libro> con el título: " + titulo);

                                // Elimina el nodo <Libro> completo
                                nodoLibro.getParentNode().removeChild(nodoLibro);

                                System.out.println("Nodo Eliminado <Libro>");
                                return 0;  // Éxito
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("No se ha podido eliminar el nodo <Libro>: " + e.getMessage());
        }
        return -1;  // Fallo
    }

    public int guardarXML(String ruta) {

        int retorno = -1;
        if (ruta != null && doc != null) {
            File archivoDestino = new File(ruta);
            try {

                if (!archivoDestino.isDirectory() && (archivoDestino.createNewFile() || archivoDestino.canWrite())) {
                    try {
                        Source src = new DOMSource(doc);
                        StreamResult srt = new StreamResult(new File(ruta));

                        // Creamos el transformador
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Configura la indentación

                        // Realizamos la transformación para guardar el archivo
                        transformer.transform(src, srt);
                        retorno = 0;  // Éxito
                        System.out.println("Archivo guardado correctamente.");
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }

}
