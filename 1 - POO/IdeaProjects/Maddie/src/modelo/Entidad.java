package modelo;

public abstract class Entidad {
    protected int id;
    protected String nombre;

    public Entidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void mostrarDetalles();
}
