package com.app.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Restaurante {
	// Atributos
    private ArrayList<Reserva> listaReservas;
    private ArrayList<Mesa> listaMesas;
    private ArrayList<Mozo> listaMozos;
    
    // Constructor
    public Restaurante() {
        this.listaReservas = new ArrayList<>();
        this.listaMesas = new ArrayList<>();
        this.listaMozos = new ArrayList<>();
    }

    // Métodos
    // Método para precargar Mozos
    public void agregarMozosPredeterminados() {
    	listaMozos.add(new Mozo("Juan", "Perez", "123-4567", 1));
    	listaMozos.add(new Mozo("Pablo", "Rodriguez", "321-7654", 2));
    }

    // Método para precargar Mesas
    public void agregarMesasPredeterminadas() {
    	listaMesas.add(new Mesa(1, 2, listaMozos.get(0).getIdMozo()));
    	listaMesas.add(new Mesa(2, 2, listaMozos.get(1).getIdMozo()));
    	listaMesas.add(new Mesa(3, 4, listaMozos.get(0).getIdMozo()));
    	listaMesas.add(new Mesa(4, 4, listaMozos.get(1).getIdMozo()));
    	listaMesas.add(new Mesa(5, 6, listaMozos.get(0).getIdMozo()));
    	listaMesas.add(new Mesa(6, 6, listaMozos.get(1).getIdMozo()));
    }
    
    // Método para crear una Reserva
    public String agregarReserva(Cliente cliente, int numeroPersonas, int hora, int mesaElegida) {
    	Reserva nuevaReserva = new Reserva("R-"+mesaElegida+"-"+hora, cliente, numeroPersonas, hora, mesaElegida);
    	listaReservas.add(nuevaReserva);
    	listaMesas.get(mesaElegida-1).asignarMesa(hora);
    	if(existeReserva(nuevaReserva.getId())) {
    		return "\n-> Reserva " + nuevaReserva.getId() + " creada con éxito. <-";
    	}
    	return "\nNo ha podido agregarse la Reserva.";
    }

    // Método para eliminar una Reserva
    public String cancelarReserva(String idReserva) {
    	StringBuilder cancelacion = new StringBuilder();
    	for(int i=0; i < listaReservas.size(); i++) {
    		Reserva r = listaReservas.get(i);
    		if (r.getId().equalsIgnoreCase(idReserva)) {
    			cancelacion.append("\nSe está eliminando la siguiente reserva:\n");
    			cancelacion.append(r.getTodaInfo(this));
    			listaMesas.get(r.getMesaAsignada()-1).liberarMesa(r.getHoraReserva());
    			listaReservas.remove(i);
    			cancelacion.append("\n-> Reserva ").append(idReserva).append(" cancelada con éxito. <-");
    		}
    	}
    	return cancelacion.toString();
    }
    
    // Chequear si existe una Reserva por Id
    public boolean existeReserva(String id) {
    	for(Reserva r : listaReservas) {
    		if (r.getId().equalsIgnoreCase(id)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // Método para obtener todas las Mesas, con sus horarios disponibles
    public ArrayList<String> getTodasMesasDisponibles() { 
    	ArrayList<String> mesasDisponibles = new ArrayList<>();
    	for (Mesa mesa : listaMesas) {
    		StringBuilder mesaInfo = new StringBuilder();
    		mesaInfo.append("\n+ Mesa N° ").append(mesa.getNumeroMesa()).append(": Capacidad para ").append(mesa.getCapacidad()).append(" personas.");
    		HashMap<Integer, Boolean> disponibilidad = mesa.getDisponibilidad();
    		int cantHorasDisp = 0;
    		StringBuilder horasDisponibles = new StringBuilder();
    		for (int hora : disponibilidad.keySet()) {
        		if (disponibilidad.get(hora)) {
        			horasDisponibles.append("\n  - ").append(hora).append("hs");
        			cantHorasDisp++;
        		}
        	}
        	if (cantHorasDisp == 0) {
        		mesaInfo.append("\nEsta mesa no posee disponibilidad horaria.");
        	} else {
        		mesaInfo.append(horasDisponibles.toString());
        	}
        	mesasDisponibles.add(mesaInfo.toString());
        }
    	return mesasDisponibles;
    }

    // Método para obtener todas las Mesas disponibles según hora y nro personas
    public ArrayList<Mesa> getMesasDisponibles(int hora, int numeroPersonas) {
        ArrayList<Mesa> mesasDisponibles = new ArrayList<>();
        for (Mesa mesa : listaMesas) {
        	if (mesa.getDisponibilidadHora(hora) && mesa.getCapacidad() >= numeroPersonas) {
        		mesasDisponibles.add(mesa);
        	}
        }
        // Ordenar las mesas de forma ascendente
        Collections.sort(mesasDisponibles, Comparator.comparingInt(Mesa::getCapacidad));
        // Buscar mesas con la capacidad exacta, o con la capacidad más cercana al nro personas
        ArrayList<Mesa> mesasAdecuadas = new ArrayList<>();
        int capacidadMasCercana = Integer.MAX_VALUE;
        for (Mesa mesa : mesasDisponibles) {
        	if (mesa.getCapacidad() == numeroPersonas) {
                mesasAdecuadas.add(mesa); // Si capacidad igual a personas
            } else if (mesa.getCapacidad() > numeroPersonas) {
                if (mesasAdecuadas.isEmpty() && mesa.getCapacidad() < capacidadMasCercana) {
                    capacidadMasCercana = mesa.getCapacidad();
                    mesasAdecuadas.add(mesa); // Primera capacidad más cercana para primera mesa
                } else if (mesa.getCapacidad() == capacidadMasCercana) {
                    mesasAdecuadas.add(mesa); // Primera capacidad más cercana para mesas restantes
                }
            }
        }
    	return mesasAdecuadas;
    }
    
    // Método para obtener información de Reserva
    public String getInfoReserva(String buscar, int opcion) {
    	ArrayList<Reserva> reservas = new ArrayList<>();
    	StringBuilder infoReserva = new StringBuilder();
    	if(opcion == 1) {
    		for(Reserva r : listaReservas) {
    			if(r.getId().equalsIgnoreCase(buscar)) {
    				reservas.add(r);
    				break;
    			}
    		}
    	} else if (opcion == 2) {
    		for(Reserva r : listaReservas) {
    			if(r.getClienteApellido().equalsIgnoreCase(buscar)) {
    				reservas.add(r);
    			}
    		}
    	}		
    	if(!reservas.isEmpty()) {
    		for(Reserva r : reservas) {
    			infoReserva.append(r.getTodaInfo(this));
    		}
    	} else if (reservas.isEmpty()) {
    		infoReserva.append("\nNo se ha encontrado la reserva.");
    	}
    	return infoReserva.toString();
    }
    
    // Método para modificar la información de una Reserva
    public String modificarReserva(String idReserva, int opcionElegida, String modificar) {
    	StringBuilder modificacion = new StringBuilder();
    	for(Reserva r : listaReservas) {
    		if (r.getId().equalsIgnoreCase(idReserva)) {
    			Cliente cliente = r.getCliente();
    			modificacion.append("\nSe está modificando la reserva:\n");
    			switch(opcionElegida) {
    				case 1: // Nombre
    					r.setCliente(new Cliente(modificar, cliente.getApellido(), cliente.getNumeroTelefono()));
    					break;
    				case 2: // Apellido
    					r.setCliente(new Cliente(cliente.getNombre(), modificar, cliente.getNumeroTelefono()));
    					break;
    				case 3: // Telefono
    					r.setCliente(new Cliente(cliente.getNombre(), cliente.getApellido(), modificar));
    					break;
    				case 4: // Nro Personas
    					r.setNumeroPersonas(Integer.parseInt(modificar));
    					break;
    			}
    			modificacion.append(r.getTodaInfo(this));
    			modificacion.append("\n-> Reserva ").append(idReserva).append(" modificada con éxito. <-");
    			}
    		}
    	return modificacion.toString();
    }
    
    // Método para buscar Mozo existente por Id
    public Mozo buscarMozoPorId(int idMozo) {
        for (Mozo mozo : listaMozos) {
            if (mozo.getIdMozo() == idMozo) {
                return mozo;
            }
        }
        return null;
    }
    
    // Getters
    public ArrayList<Reserva> getListaReservas() {
    	return listaReservas;
    }
    
    public ArrayList<Mesa> getListaMesas() {
    	return listaMesas;
    }
    
    public ArrayList<Mozo> getListaMozos() {
    	return listaMozos;
    }   
}

