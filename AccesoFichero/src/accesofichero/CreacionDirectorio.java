package accesofichero;

import java.io.File;

/**
 *
 * @author martin
 */
public class CreacionDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1.Crear un directorio/carpeta dentro de AD que se llame logs.
        
        File f = new File("C:\\AD/logs");

        if (f.mkdir()) {
            System.out.println("DIRECTORIO CREADO");
        } else {
            System.out.println("No se ha podido crar dicho directorio");
        }
    }

}
