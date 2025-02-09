package modelo;

public class Usuario extends Entidad {
    private String contrasena;

    public Usuario(int id, String nombre, String contrasena) {
        super(id, nombre); // Inicializa los atributos de la clase base
        this.contrasena = contrasena; // Establece la contraseña
    }

    public String getContrasena() {
        return contrasena; // Getter para la contraseña
    }

    public boolean autenticar(String contrasena) {
        return this.contrasena.equals(contrasena); // Compara la contraseña ingresada
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("ID: " + getId() + ", Nombre: " + getNombre()); // Usa los métodos de la clase base
    }
}
