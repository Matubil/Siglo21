package com.app.modelo.atencionalpublico;

public class Mozo extends Persona implements InfoContacto{
	private int idMozo;
	
    // Constructor
    public Mozo(String nombre, String apellido, String numeroTelefono, int idMozo) {
    	super(nombre, apellido, numeroTelefono);
    	idValido(idMozo);
    	this.idMozo = idMozo;
    }
    
    // Métodos
    public String obtenerInformacion() {
		StringBuilder infoMozo = new StringBuilder();
		infoMozo.append("\nMozo Asignado:     ").append(super.getNombre());
		return infoMozo.toString();
    }

	private void idValido(int id) {
    	if (id <= 0 || id > 100) {
            throw new IllegalArgumentException("El Id no se encuentra dentro del rango permitido (1 a 100).");
        }
    }

	public int getIdMozo() {
		return idMozo;
	}

	public String getTelefono() {
		return getNumeroTelefono();
	}
}
