package martinmd.martinmd;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateEnterprise {

    private static SessionFactory sf;

    // Constructor que inicializa la SessionFactory
    HibernateEnterprise() {
        sf = new Configuration().configure().buildSessionFactory(); // Creación de la SessionFactory
    }

    public void close() {
        sf.close(); // Cierre de la SessionFactory
    }

    // Método para mostrar todos los productos
    public void showProducts() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Productos> allproducts = session.createQuery("FROM Productos", Productos.class).list();

            System.out.println("======================================");
            System.out.println("Buscando Productos...");
            System.out.println("======================================");

            for (Productos p : allproducts) {
                System.out.println("======================================");
                System.out.println("Id: " + p.getId());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("======================================");
            }
            tx.commit();
            System.out.println("======================================");
            System.out.println("Finalizada la Busqueda...");
            System.out.println("======================================");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para mostrar productos por nombre
    public void mostrarPorNombre(String texto) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Productos> productosPorNombre = session.createQuery("FROM Productos WHERE nombre LIKE :nombre", Productos.class)
                    .setParameter("nombre", "%" + texto + "%").list();

            if (!productosPorNombre.isEmpty()) {
                System.out.println("======================================");
                System.out.println("Buscando productos con el nombre: " + texto);
                System.out.println("======================================");

                for (Productos p : productosPorNombre) {
                    System.out.println("Id: " + p.getId());
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Precio: " + p.getPrecio());
                    System.out.println("======================================");
                }
            } else {
                System.out.println("No se encontraron productos con el nombre: " + texto);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para mostrar productos ordenados por precio (ascendente)
    public void productosOrdenadosPorPrecio() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Productos> productosOrdenados = session.createQuery("FROM Productos ORDER BY precio ASC", Productos.class).list();

            if (!productosOrdenados.isEmpty()) {
                System.out.println("======================================");
                System.out.println("Productos ordenados por precio:");
                System.out.println("======================================");

                for (Productos p : productosOrdenados) {
                    System.out.println("Id: " + p.getId());
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Precio: " + p.getPrecio());
                    System.out.println("======================================");
                }
            } else {
                System.out.println("No se encontraron productos.");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para mostrar el precio de productos con un nombre específico
    public void precioDe(String nombre) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Productos> productosConNombre = session.createQuery("FROM Productos WHERE nombre = :nombre", Productos.class)
                    .setParameter("nombre", nombre).list();

            if (!productosConNombre.isEmpty()) {
                System.out.println("======================================");
                System.out.println("Buscando el precio de los productos con nombre: " + nombre);
                System.out.println("======================================");

                for (Productos p : productosConNombre) {
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Precio: " + p.getPrecio());
                    System.out.println("======================================");
                }
            } else {
                System.out.println("No se encontraron productos con el nombre: " + nombre);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para buscar un producto por ID usando uniqueResult
    public void buscaProducto(int id) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Productos p = session.createQuery("FROM Productos WHERE id = :id", Productos.class)
                    .setParameter("id", id).uniqueResult();

            if (p != null) {
                System.out.println("======================================");
                System.out.println("Producto encontrado con ID: " + id);
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("======================================");
            } else {
                System.out.println("No se encontró el producto con ID: " + id);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
