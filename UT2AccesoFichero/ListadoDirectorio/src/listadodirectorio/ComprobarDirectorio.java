package listadodirectorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author martin
 */
public class ComprobarDirectorio {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("ENTER namePath: ");
        String namePath = br.readLine();

        System.out.println("ENTER DIRname");
        String nameDir = br.readLine();

        File f = new File(namePath, nameDir);

        if (f.exists()) {
            String arr[] = f.list();

            int n = arr.length;

            for (int i = 0; i < n; i++) {
                System.out.println(arr[i]);
                File f1 = new File(arr[i]);
                if (f1.isFile()) {
                    System.out.println("Es un Archivo");
                }

                if (f1.isDirectory()) {
                    System.out.println("Es un directorio");
                }

            }

            System.out.println("Nº de Entrdas que tiene este directorio: " + n);
        } else {
            System.out.println("Directory not found");
        }
    }

}

// MetodoS de la clase File
// canExecute()
// canReade() Devuelve true puedo escribir, miestras se gestiona los permisos
// canWrite() Permite la escritura 
// exists() Comprueba si el fichero o directorio existe
// length() Devuelve la longitud del fichero
// createNewFile() Devuelve un array con los directorios y ficheros para poder trabajar con él
// delete() Borra fichero o directorio
//renameTo() Renombrea fichero o directorio
        
//mkdir() crea fichero o directorio
