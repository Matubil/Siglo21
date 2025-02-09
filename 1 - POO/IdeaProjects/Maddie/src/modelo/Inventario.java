package modelo;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public void consultarProductos() {
        for (Producto producto : listaProductos) {
            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Stock: " + producto.getStock() + ", Precio: " + producto.getPrecio());
        }
    }

    public void actualizarStock(int id, int nuevaCantidad) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                producto.actualizarStock(nuevaCantidad);
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }
}
