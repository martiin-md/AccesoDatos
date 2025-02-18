///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//package com.mycompany.solucionexamensaxmartinmd;
//
//import java.io.File;
//import java.util.Scanner;
//
///**
// *
// * @author martin
// */
//public class Examen_8_DomSAXMartinMD {
//
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        File f = new File("C:\\Users\\marti\\OneDrive\\Escritorio\\Examen_8_DomSAXMartinMD\\empleados.xml");
//
//        AccesoDomSAX a = new AccesoDomSAX();
//        AccesoDomEmpleados d = new AccesoDomEmpleados();
//
//        boolean continuar = true;
//
//        while (continuar) {
//            d.abrirXMLaDOM(f);
//
//            a.parsearXMLconEmpleadosSAXhandler(f);
//            a.parsearXMLconTitulosSAXhandler(f);
//
//            System.out.println("OPCIONES:");
//            System.out.println("1. INSERTAR EMPLEADO");
//            System.out.println("2. BORRAR EMPLEADO");
//            System.out.println("3. SALIR");
//            
//            int opcion = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcion) {
//                case 1:
////                    d.insertarEmpleado_A("martin", "gerente", "administrativo", "5000", "22/10/2024", "martin@gmail,com",
////                            "644313652", "Jose");
//                    
//                    d.insertarEmpleado_B();
//                    d.guardarXML(f.getPath());
//                    break;
//                case 2:
////                    d.borrar_A("martin");
//                    
//                    d.borrar_B();
//                    d.guardarXML(f.getPath());
//                    break;
//                case 3:
//                    continuar = false;
//                    break;
//                default:
//                    System.out.println("Opción no válida. Intente de nuevo.");
//            }
//        }
//
//        scanner.close();
//    }
//}
