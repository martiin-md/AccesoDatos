package accesofichero;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author martin
 */
public class CreacionArchivo {
    public static void main(String[] args) throws IOException {
        
        //2.Crear un archivo nuevo que se llame listado dentro de logs.
                File archivo = new File("C:\\AD\\logs\\listado.txt");

        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            archivo.createNewFile();
            System.out.println("Archivo creado: " + archivo.getAbsolutePath());
        } else {
            System.out.println("El archivo ya existe.");
        }
        
        File directorio = archivo.getParentFile();
        if (directorio.exists() && directorio.isDirectory()) {
            String[] arr = directorio.list();
            int n = arr.length;

            for (int i = 0; i < n; i++) {
                System.out.println(arr[i]);
                File f1 = new File(directorio, arr[i]);

                if (f1.isFile()) {
                    System.out.println(arr[i] + " es un archivo.");
                } else if (f1.isDirectory()) {
                    System.out.println(arr[i] + " es un directorio.");
                }
            }
        }
    }
}
