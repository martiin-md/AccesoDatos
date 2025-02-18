package listadodirectorio;

import java.io.*;

/**
 *
 * @author martin
 */
public class ListadoDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta = "C:\\AD"; //".";

        if (args.length >= 1) {
            ruta = args[0];
        }
        //Si llamé el programa con parametros (ruta) uso sea.

        File fich = new File(ruta);
        //fich apunta a la ruta que le hemos pasado

        //Si la ruta no es accesible,error
        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ").");

        } else {
            //Si la ruta es un archivo
            if (fich.isFile()) {
                System.out.println(ruta + "es un fichero");
            } //Si la ruta es una carpeta
            else {
                System.out.println("Es una carpeta. Contenedor: ");

                //Array de Files que recorremos comprobando si es archivo o carpeta
                File[] fichero = fich.listFiles();
                for (File f : fichero) {
                    String textoDescr = f.isDirectory() ? "/"
                            : f.isFile() ? "_" : "?"; //Operador ternario, es carpeta?
                    /*
                       //Alternativa sin operador ternario
                            String textoDescr;
                            
                            if(f.isDirectory())
                            {
                            textoDescr = "/";
                            }
                            else if (f.isFile())
                            {
                            textoDescr = "_";
                            }
                            else{
                            textoDescr = "?";
                            }
                            
                     */
                    System.out.println("(" + textoDescr + ")" + f.getName());

                }
            }

        }

    }
}
