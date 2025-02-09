package com.app.modelo;

public class Cliente extends Persona {
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

}
