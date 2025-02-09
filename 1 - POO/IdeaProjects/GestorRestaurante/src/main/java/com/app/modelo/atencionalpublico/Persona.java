package com.app.modelo.atencionalpublico;

// Clase abstracta para una Persona
public abstract class Persona {
    private String nombre;
    private String apellido;
    private String numeroTelefono;

    public Persona(String nombre, String apellido, String numeroTelefono) {
        nullOEspacios(nombre, "nombre");
        nullOEspacios(apellido, "apellido");
        nullOEspacios(numeroTelefono, "número de teléfono");
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
    }
    
    // Métodos
    public abstract String obtenerInformacion();

    // Método para chequear valor igual a null o espacios
    private void nullOEspacios(String chequear, String atributo) {
    	if (chequear == null || chequear.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + atributo + " no puede estar vacío.");
        }
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}
