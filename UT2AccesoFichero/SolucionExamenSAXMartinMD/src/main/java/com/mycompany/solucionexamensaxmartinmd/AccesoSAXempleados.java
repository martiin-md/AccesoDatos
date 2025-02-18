/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solucionexamensaxmartinmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author martin
 */
public class AccesoSAXempleados {

    public void mostrarEmpleados(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EmpleadoSAXhandler handler = new EmpleadoSAXhandler();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarEmpleado(File file, File tempFile) {
        try {
            // Verificar si el archivo original existe
            if (!file.exists()) {
                System.out.println("El archivo no existe: " + file.getAbsolutePath());
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            Scanner scanner = new Scanner(System.in);

            // Recolectar datos del nuevo empleado
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Puesto: ");
            String puesto = scanner.nextLine();
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            System.out.print("Salario: ");
            String salario = scanner.nextLine();
            System.out.print("Fecha de Contratación (YYYY-MM-DD): ");
            String fechaContratacion = scanner.nextLine(); // Asegúrate de ingresar el formato correcto
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Supervisor: ");
            String supervisor = scanner.nextLine();

            // Crear la nueva entrada de empleado
            String newEmployee = String.format(
                    "<empleado email=\"%s\" fecha_contratacion=\"%s\" supervisor=\"%s\" telefono=\"%s\" departamento=\"%s\" salario=\"%s\">\n"
                    + "    <nombre>%s</nombre>\n"
                    + "    <puesto>%s</puesto>\n"
                    + "</empleado>\n",
                    email, fechaContratacion, supervisor, telefono, departamento, salario, nombre, puesto
            );

            String line;
            boolean isWritten = false; // Bandera para indicar si hemos escrito el nuevo empleado
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n"); // Escribir línea actual

                // Insertar el nuevo empleado antes del cierre de la etiqueta raíz
                if (line.trim().equals("</empleados>")) {
                    writer.write(newEmployee); // Insertar nuevo empleado
                    isWritten = true; // Marcar que hemos escrito
                }
            }

            reader.close();
            writer.close();

            // Comprobar si el empleado fue insertado
            if (isWritten) {
                // Eliminar el archivo original y renombrar el temporal
                if (file.delete()) {
                    if (tempFile.renameTo(file)) {
                        System.out.println("Empleado insertado correctamente.");
                    } else {
                        System.out.println("Error al renombrar el archivo temporal.");
                    }
                } else {
                    System.out.println("Error al eliminar el archivo original.");
                }
            } else {
                System.out.println("No se pudo insertar el nuevo empleado.");
                // Si no se escribió el nuevo empleado, eliminamos el archivo temporal
                tempFile.delete();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // Método para borrar un empleado por nombre
    public void borrarEmpleado(File file, File tempFile, String nombreABorrar) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean empleadoEncontrado = false;
            boolean dentroEmpleado = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().contains("<empleado")) {
                    dentroEmpleado = true;
                }
                if (dentroEmpleado && line.trim().contains("<nombre>" + nombreABorrar + "</nombre>")) {
                    empleadoEncontrado = true;
                    // Saltar líneas hasta encontrar el cierre del elemento empleado
                    while (line != null && !line.trim().contains("</empleado>")) {
                        line = reader.readLine();
                    }
                    dentroEmpleado = false; // Fin del bloque de empleado
                    continue; // Saltar línea de cierre
                }
                // Escribir todas las líneas que no pertenecen al empleado a eliminar
                writer.write(line + "\n");
            }

            reader.close();
            writer.close();
            if (file.delete() && tempFile.renameTo(file)) {
                if (empleadoEncontrado) {
                    System.out.println("Empleado borrado correctamente.");
                } else {
                    System.out.println("Empleado no encontrado.");
                }
            } else {
                System.out.println("Error al actualizar el archivo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal
    public static void main(String[] args) {
        File xmlFile = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\SolucionExamenSAXMartinMD\\empleados.xml");
        File tempFile = new File("empleados_temp.xml");
        AccesoSAXempleados acceso = new AccesoSAXempleados();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Mostrar empleados");
            System.out.println("2. Insertar empleado");
            System.out.println("3. Borrar empleado");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    acceso.mostrarEmpleados(xmlFile);
                    break;
                case 2:
                    acceso.insertarEmpleado(xmlFile, tempFile);
                    break;
                case 3:
                    System.out.print("Nombre del empleado a borrar: ");
                    String nombre = scanner.nextLine();
                    acceso.borrarEmpleado(xmlFile, tempFile, nombre);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
