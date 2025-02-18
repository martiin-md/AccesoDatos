package AccesoDOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.*;
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
import org.w3c.dom.Text;

/**
 *
 * @author martin
 */
public class AccesoDOM {

    Document doc;

    public int abrirXMLaDOM(File f) {
        try {
            System.out.println("Se esta abriendo el XML file y generando el DOM ");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();

            factory.setIgnoringComments(true); //Ignoramos los comentarios 
            factory.setIgnoringElementContentWhitespace(true); //Ignoramos los espacios blancos

            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = (Document) builder.parse(f);

            System.out.println("DOM CREADO CON EXISTO!!");

            return 0;

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");

        } catch (IOException e) {
            System.out.println("");

        } catch (Exception e) {

            System.out.println(e);

            return -1;

        }
        return 0;

    }

    public void mostarXML() {
        System.out.println(doc.toString());
    }

    public void recorreDOM() {

        String[] datos = new String[5]; // Array para la info de cada libro

        Node nodo = null;

        Node root = doc.getFirstChild();

        NodeList nodoLista = root.getChildNodes();

        for (int i = 0; i < nodoLista.getLength(); i++) {
            nodo = nodoLista.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Node ntemp = null;

                int contador = 1;

                datos[0] = nodo.getAttributes().item(0).getNodeValue();
                NodeList n12 = nodo.getChildNodes();

                for (int j = 0; j < n12.getLength(); j++) {
                    ntemp = n12.item(j);

                    if (ntemp.getNodeType() == Node.ELEMENT_NODE) {

                        datos[contador] = ntemp.getTextContent();

                        contador++;
                    }
                }
                System.out.println(datos[0] + "--" + datos[2] + "--" + datos[1]);
            }

        }

    }

    public int insertarLibros(String titulo, String autor, String fechaPubli) {

        try {
            Element elTitulo = doc.createElement("Titulo");
            Text textoTitulo = doc.createTextNode(titulo);
            elTitulo.appendChild(textoTitulo);

            Element elAutor = doc.createElement("Autor");
            Text autorTexto = doc.createTextNode(titulo);
            elAutor.appendChild(autorTexto);

            Element libro = doc.createElement("Libro");
            libro.setAttribute("Publicado", fechaPubli);
            libro.appendChild(elTitulo);
            libro.appendChild(elAutor);

            Node raizHijo = doc.getFirstChild();
            raizHijo.appendChild(elTitulo);

            System.out.println("Se ha insertado correctamente en DOM el LIBRO:" + titulo + " / " + autor + " / " + fechaPubli);

            return 0;

        } catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
            return -1;
        }

    }

    public int guardarXML(String ruta) {
        int retorno = -1;  // Valor inicial en caso de error
        if (ruta != null && doc != null) {
            File archivoDestino = new File(ruta);
            try {
                // Verifica si el archivo no es un directorio y si se puede crear o escribir en él
                if (!archivoDestino.isDirectory() && (archivoDestino.createNewFile() || archivoDestino.canWrite())) {
                    try {
                        // Fuente de la transformación: el documento DOM
                        Source src = new DOMSource(doc);
                        // Destino de la transformación: archivo en la ruta especificada
                        StreamResult srt = new StreamResult(new File(ruta));

                        // Crear el transformador
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Configura la indentación

                        // Realizar la transformación
                        transformer.transform(src, srt);
                        retorno = 0;  // Éxito
                        System.out.println("Archivo creado con éxito.");
                    } catch (TransformerException e) {
                        e.printStackTrace();  // Muestra más detalles del error
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();  // Manejo de excepciones generales
            }
        }
        return retorno;
    }

    public int borrarNodo(String titulo) {
        System.out.println("Ingrese el Titulo del Libro: " + titulo + ";Se Borrara");

        try {
            Node raiz = doc.getDocumentElement();

            NodeList lista = doc.getElementsByTagName("Titulo");
            Node nodo1;

            for (int i = 0; i < lista.getLength(); i++) {
                nodo1 = lista.item(i);

                if (nodo1.getNodeType() == Node.ELEMENT_NODE) {

                    if (nodo1.getTextContent().equals(titulo)) {
                        System.out.println("Borrando dicho 'nodo' <Libro>" + titulo);

                        nodo1.getParentNode().getParentNode().removeChild(nodo1.getParentNode());

                        System.out.println("NODO BORRADO <LIBRO>");

                        break;
                    }
                }
            }

            return 0;
        } catch (Exception e) {
            System.out.println("Error en " + e.getMessage());
            e.printStackTrace();
            return -1;

        }

    }

}
