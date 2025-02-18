package martinmd.martinmd;

public class App2 {
public static void main(String[] args) {
    HibernateEnterprise he = new HibernateEnterprise();
    // Mostrar productos ordenados por precio
    he.productosOrdenadosPorPrecio();

    // Mostrar productos por nombre
    he.mostrarPorNombre("iphone 16");

    // Mostrar precio de un producto con nombre específico
    he.precioDe("iphone 16");

    // Buscar producto por ID
    he.buscaProducto(1);
}

}
