package modelo;

public class Producto extends Entidad {
    private String categoria;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String categoria, double precio, int stock) {
        super(id, nombre);
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Categoría: " + categoria + ", Precio: " + precio + ", Stock: " + stock);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void actualizarStock(int nuevaCantidad) {
        this.stock = nuevaCantidad;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
