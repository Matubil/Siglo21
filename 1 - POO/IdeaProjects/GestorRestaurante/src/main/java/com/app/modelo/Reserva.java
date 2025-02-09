package com.app.modelo;

import com.app.modelo.atencionalpublico.Cliente;
import com.app.modelo.atencionalpublico.Mozo;

public class Reserva {
	// Atributos
    private String id;
    private Cliente cliente;
    private int numeroPersonas;
    private int horaReserva;
    private int mesaAsignada;

    // Constructor
    public Reserva(String id, Cliente cliente, int numeroPersonas, int horaReserva, int mesaAsignada) {
        this.id = id;
        this.cliente = cliente;
        if(numeroPersonas > 0) {
        	this.numeroPersonas = numeroPersonas;
        } else {
        	throw new IllegalArgumentException("El número de personas debe ser mayor a 0.");
        }
        this.horaReserva = horaReserva;
        this.mesaAsignada = mesaAsignada;
    }

    // Getters
	public String getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public String getClienteApellido() {
		return cliente.getApellido();
	}
	
	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public int getHoraReserva() {
		return horaReserva;
	}

	public int getMesaAsignada() {
		return mesaAsignada;
	}
	
	public String getTodaInfo(Restaurante r) {
		Mozo mozo = r.buscarMozoPorId(r.getListaMesas().get(mesaAsignada).getIdMozo());
		StringBuilder infoReserva = new StringBuilder();
		infoReserva.append("\n-> Reserva ").append(id)
		.append(cliente.obtenerInformacion())
		.append("\nN° Comensales:     ").append(numeroPersonas)
		.append("\nHora Reserva:      ").append(horaReserva)
		.append("hs\nMesa Asignada:     Mesa N°").append(mesaAsignada)
		.append((mozo != null) ? mozo.obtenerInformacion() : "\\nMozo Asignado:     Información no disponible");
		return infoReserva.toString();
	}
	
	// Setters
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

}
