package accesofichero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author martin
 */
public class CompletarInfoList {

    public static void main(String[] args) throws IOException {

        //4. Completar la informaci�n de listado con el tama�o de los archivos y permisos.
                File listadoArchivo = new File("C:\\AD\\logs\\listado.txt");

        File adDirectorio = new File("C:\\AD");

        if (adDirectorio.exists() && adDirectorio.isDirectory()) {
            FileWriter escrituraAlArchivo = new FileWriter(listadoArchivo);

            File[] archivosYCarpetas = adDirectorio.listFiles();
            if (archivosYCarpetas != null) {
                for (File f : archivosYCarpetas) {
                    if (f.isDirectory()) {
                        escrituraAlArchivo.write("Directorio: " + f.getAbsolutePath());

                        File[] subArchivosYCarpetas = f.listFiles();
                        if (subArchivosYCarpetas != null) {
                            for (File subF : subArchivosYCarpetas) {
                                if (subF.isDirectory()) {
                                    escrituraAlArchivo.write("Subdirectorio: " + subF.getAbsolutePath());
                                    escrituraAlArchivo.write("Tama�o: " + subF.length());
                                } else {
                                    escrituraAlArchivo.write("Archivo: " + subF.getAbsolutePath());
                                }
                            }
                        }
                    } else {
                        escrituraAlArchivo.write("Archivo: " + f.getAbsolutePath() + "\n");
                        escrituraAlArchivo.write("Tama�o: " + f.length() + "\n");
                    }
                }
            }

            escrituraAlArchivo.close();
            System.out.println("Informaci�n guardada en: " + listadoArchivo.getAbsolutePath());
        } else {
            System.out.println("No existe el directorio");
        }
    }
}
