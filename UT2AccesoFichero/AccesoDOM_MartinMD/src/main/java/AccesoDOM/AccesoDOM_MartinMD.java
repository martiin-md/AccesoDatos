package AccesoDOM;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author martin
 */
public class AccesoDOM_MartinMD {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        File f = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\AccesoDOM_MartinMD\\libros2.xml");
        AccesoDOM a = new AccesoDOM();

        a.abrirXMLaDOM(f);

        a.mostarXML();

//        a.recorreDOM();
        a.guardarXML("C:\\Users\\marti\\OneDrive\\Escritorio\\AccesoDOM_MartinMD\\libros2.xml");

        a.insertarLibros("Martin El Programador", "Martin", "09/10/2024");
        a.insertarLibros("Martin el Inversor", "Martin", "09/10/2024");
        a.insertarLibros("Martin el Programador", "Martin", "09/10/2024");
        a.insertarLibros("Martin en Ibiza", "Martin", "09/10/2024");
        a.insertarLibros("Martin el DJ", "Martin", "09/10/2024");

        a.borrarNodo("Martin El Programador");

    }
}
