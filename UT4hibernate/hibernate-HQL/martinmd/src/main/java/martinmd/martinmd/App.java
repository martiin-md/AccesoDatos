package martinmd.martinmd;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Instancia de la clase ClienteDAO
        Clientes clienteManager = new Clientes(sessionFactory);

        try {
            // 1. Mostrar todos los clientes
            System.out.println("Mostrando todos los clientes:");
            clienteManager.mostrarTodosLosClientes();

            // 2. Añadir un cliente
            System.out.println("\nAñadiendo un cliente:");
            clienteManager.anadirCliente("Andrea", "España");

            // 3. Borrar un cliente por ID
            System.out.println("\nBorrando cliente:");
            clienteManager.borrarCliente(17);

            // 4. Actualizar un cliente por ID
            System.out.println("\nActualizando cliente:");
            clienteManager.actualizarCliente(3);

            // 5. Mostrar clientes por país
            System.out.println("\nMostrando clientes de España:");
            clienteManager.mostrarPorPais("España");

            // 6. Buscar el país de un cliente por nombre
            clienteManager.buscarPaisDe("Nerea");
        } finally {
            // Cerrar la sesión de Hibernate
        	sessionFactory.close();
        }
    }
}
