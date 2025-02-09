package com.app.modelo;

import java.util.HashMap;

public class Mesa {
	// Atributos
    private int numeroMesa;
    private int capacidad;
    private int idMozo;
    private HashMap<Integer, Boolean> disponibilidad;
    private static final int HORA_MIN = 19;
    private static final int HORA_MAX = 23;
    
    // Constructor
    public Mesa(int numeroMesa, int capacidad, int idMozo) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.idMozo = idMozo;
        this.disponibilidad = new HashMap<Integer, Boolean>();
        horariosPredeterminados();
    }

    // Métodos
    // Precargar horarios disponibles
    private void horariosPredeterminados() {
    	for (int h = HORA_MIN; h <= HORA_MAX; h++) {
    		disponibilidad.put(h, true);
    	}
    }
    
    // Getters 
	public int getNumeroMesa() {
		return numeroMesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getIdMozo() {
		return idMozo;
	}

	public HashMap<Integer, Boolean> getDisponibilidad() {
		return disponibilidad;
	}

	public boolean getDisponibilidadHora(int hora) {
		return disponibilidad.get(hora);
	}
	
	private boolean horaValida(int hora) {
		return hora >= HORA_MIN && hora <= HORA_MAX;
	}
	
	// Setters
	public void asignarMesa(int hora) {
		if (horaValida(hora)) {
			if (getDisponibilidadHora(hora)) {
				this.disponibilidad.put(hora, false);
				System.out.println("La mesa N° " + getNumeroMesa() + " ha sido reservada exitosamente para las " + hora + " hs.");
			} else {
				System.out.println("La mesa N° " + getNumeroMesa() + " no se encuentra disponible en este horario.");
			}
		} else {
			System.out.println("La hora ingresada no es válida.");
		}
	}
	
	public void liberarMesa(int hora) {
		if (horaValida(hora)) {
			this.disponibilidad.put(hora, true);
			System.out.println("La mesa N° " + getNumeroMesa() + " ha sido liberada exitosamente para las " + hora + " hs.");
		} else {
			System.out.println("La hora ingresada no es válida.");
		}
	}
}
