package modelo;

import java.util.ArrayList;

public class Venta {
    private ArrayList<Producto> productosVendidos;
    private double total;
    private Usuario cajero;  // Usuario que realizó la venta

    public Venta(Usuario cajero) {
        this.productosVendidos = new ArrayList<>();
        this.total = 0;
        this.cajero = cajero;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productosVendidos.add(producto);
        total += producto.getPrecio() * cantidad;
    }

    public void mostrarVenta() {
        if (cajero != null) {
            System.out.println("---- Venta realizada por: " + cajero.getNombre() + " ----");
            for (Producto producto : productosVendidos) {
                System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
            }
            System.out.println("Total de la venta: $" + total);
        }
    }

    public double getTotal() {
        return total;
    }
}
