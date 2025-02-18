package com.mycompany.existbdjava;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import org.exist.xmldb.EXistResource;

public class ExistbdJava {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLLECTION_PATH = "/db/peliculas";
    private static final String DOCUMENT_NAME = "peliculas.xml";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        Collection col = null;
        XMLResource res = null;

        try {
            // Cargar el driver de la base de datos
            Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            // Obtener la colección con credenciales
            col = DatabaseManager.getCollection(URI + COLLECTION_PATH, USER, PASSWORD);
            if (col == null) {
                System.out.println("¡La colección no existe!");
                return;
            }
            col.setProperty(OutputKeys.INDENT, "yes");

            // Obtener el documento XML
            res = (XMLResource) col.getResource(DOCUMENT_NAME);

            if (res == null) {
                System.out.println("El documento no existe. Creándolo...");

                // Crear nuevo contenido XML
                String nuevoXML = "<peliculas>\n"
                                + "  <pelicula>\n"
                                + "    <titulo>SR MITH</titulo>\n"
                                + "    <director>mARTIIN</director>\n"
                                + "    <año>2025</año>\n"
                                + "  </pelicula>\n"
                                + "</peliculas>";

                // Crear nuevo recurso y guardarlo en la base de datos
                res = (XMLResource) col.createResource(DOCUMENT_NAME, "XMLResource");
                res.setContent(nuevoXML);
                col.storeResource(res);
                System.out.println("Documento creado con éxito.");
            } else {
                System.out.println("Contenido actual del documento:");
                System.out.println(res.getContent());

                // Modificar el XML: agregar una nueva película
                String nuevoContenido = "<peliculas>\n"
                                      + "  <pelicula>\n"
                                      + "    <titulo>Inception</titulo>\n"
                                      + "    <director>Christopher Nolan</director>\n"
                                      + "    <año>2010</año>\n"
                                      + "  </pelicula>\n"
                                      + "</peliculas>";

                res.setContent(nuevoContenido);
                col.storeResource(res);
                System.out.println("\nDocumento modificado y guardado con éxito.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Liberar recursos
            try {
                if (res != null) {
                    ((EXistResource) res).freeResources();
                }
                if (col != null) {
                    col.close();
                }
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
