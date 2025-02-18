package lecturaescrituraarchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author martin
 */
public class LecturaEscrituraArchivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        try {
            BufferedReader lector = new BufferedReader(new FileReader("C:\\AD\\alumnosNotas.txt"));
            FileWriter escritor = new FileWriter("C:\\AD\\alumnosNotasMedia.txt");

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(":");
                String nombre = datos[0];
                double[] notas = new double[datos.length - 1];
                for (int i = 1; i < datos.length; i++) {
                    notas[i - 1] = Double.parseDouble(datos[i]);
                }

                double medias = 0;
                for (double nota : notas) {
                    medias += nota;
                }
                medias /= notas.length;

                escritor.write(nombre + ": " + medias + "\n");
            }

            lector.close();
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo: " + e.getMessage());
        }
    }
}
