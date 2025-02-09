package com.app.modelo.atencionalpublico;

public class Cliente extends Persona implements InfoContacto{
    // Constructor
    public Cliente(String nombre, String apellido, String numeroTelefono) {
    	super(nombre, apellido, numeroTelefono);
    }
    
    // Métodos
    public String obtenerInformacion() {
		StringBuilder infoCliente = new StringBuilder();
		infoCliente.append("\nCliente:           ").append(super.getApellido()).append(", ").append(super.getNombre())
		.append("\nTeléfono Cliente:  ").append(super.getNumeroTelefono());
		return infoCliente.toString();
    }

    public String getTelefono() {
        return getNumeroTelefono();
    }

}
