package martinmd.martinmd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Scanner;
@Entity
@Table(name = "clientes")
public class Clientes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asegúrate de que el id se genere correctamente

    private static final long serialVersionUID = 1L;

    private SessionFactory sessionFactory;

    private Integer id;
    private String nombre;
    private String pais;
    
    public Clientes(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    // Constructores
    public Clientes() {
    }


    public Clientes(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

 
    @Override
    public String toString() {
        return "Clientes [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
    }
    


    public void mostrarTodosLosClientes() {
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> clientes = session.createQuery("FROM Clientes", Object[].class).list();
            if (clientes.isEmpty()) {
                System.out.println("No hay clientes registrados.");
            } else {
                for (Object[] cliente : clientes) {
                    System.out.println(cliente);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar los clientes: " + e.getMessage());
        }
    }

    public void anadirCliente(String nombre, String pais) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Clientes cliente = new Clientes();
            cliente.setNombre(nombre);
            cliente.setPais(pais);
            session.save(cliente);
            tx.commit();
            System.out.println("Cliente añadido correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al añadir el cliente: " + e.getMessage());
        }
    }

    public void borrarCliente(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Clientes cliente = session.get(Clientes.class, id);
            if (cliente != null) {
                session.delete(cliente);
                tx.commit();
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("Cliente con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al borrar el cliente: " + e.getMessage());
        }
    }

    public void actualizarCliente(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession(); Scanner scanner = new Scanner(System.in)) {
            Clientes cliente = session.get(Clientes.class, id);
            if (cliente == null) {
                System.out.println("Cliente con ID " + id + " no encontrado.");
                return;
            }

            System.out.println("Cliente actual: " + cliente);
            tx = session.beginTransaction();

            System.out.print("¿Desea cambiar el nombre? (si/no): ");
            if (scanner.nextLine().equalsIgnoreCase("si")) {
                System.out.print("Nuevo nombre: ");
                cliente.setNombre(scanner.nextLine());
            }

            System.out.print("¿Desea cambiar el país? (si/no): ");
            if (scanner.nextLine().equalsIgnoreCase("si")) {
                System.out.print("Nuevo país: ");
                cliente.setPais(scanner.nextLine());
            }

            session.update(cliente);
            tx.commit();
            System.out.println("Cliente actualizado correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void borrarCliente(String nombre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query<Clientes> query = session.createQuery("FROM Clientes WHERE nombre = :nombre", Clientes.class);
            query.setParameter("nombre", nombre);
            List<Clientes> clientes = query.list();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes con el nombre: " + nombre);
            } else {
                for (Clientes cliente : clientes) {
                    session.delete(cliente);
                }
                tx.commit();
                System.out.println("Clientes con el nombre " + nombre + " eliminados correctamente.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al borrar los clientes: " + e.getMessage());
        }
    }

    public void mostrarPorPais(String pais) {
        try (Session session = sessionFactory.openSession()) {
            Query<Clientes> query = session.createQuery("FROM Clientes WHERE pais = :pais",Clientes.class);
            query.setParameter("pais", pais);
            List<Clientes> clientes = query.list();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes en el país: " + pais);
            } else {
                System.out.println("Número de clientes en " + pais + ": " + clientes.size());
                for (Clientes cliente : clientes) {
                    System.out.println(cliente);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar clientes por país: " + e.getMessage());
        }
    }

    public void buscarPaisDe(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            Query<Clientes> query = session.createQuery("FROM Clientes WHERE nombre = :nombre", Clientes.class);
            query.setParameter("nombre", nombre);
            Clientes cliente = query.uniqueResult();

            if (cliente == null) {
                System.out.println("No se encontró un cliente con el nombre: " + nombre);
            } else {
                System.out.println("El cliente " + nombre + " es de: " + cliente.getPais());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el país del cliente: " + e.getMessage());
        }
    }
}

