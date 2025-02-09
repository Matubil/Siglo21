package com.app.gestor;

import java.util.ArrayList;
import java.util.Scanner;

import com.app.modelo.Cliente;
import com.app.modelo.Mesa;
import com.app.modelo.Reserva;
import com.app.modelo.Restaurante;

public class GestorReservas {
	// Atributos
    private Restaurante restaurante;
    private Scanner scanner;
    private static final int HORA_MIN = 19;
    private static final int HORA_MAX = 23;
    private static final int CAPAC_MAX = 6;

    // Constructor
    public GestorReservas() {
        this.restaurante = new Restaurante();
        this.scanner = new Scanner(System.in);
    }

    // Métodos
    // Método para iniciar flujo del programa
    public void iniciar() {
        System.out.println("------------------------------------------------------");	
    	System.out.println("-- Bienvenido al Gestor de Reservas de Lobos Restó  --");
        System.out.println("------------------------------------------------------");	
        int opcion;
        restaurante.agregarMozosPredeterminados();
        restaurante.agregarMesasPredeterminadas();
        
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            System.out.println("------------------------------------------------------");
            procesarOpcion(opcion);
        } while (opcion != 0);
        
        scanner.close();
    }

    // Método para mostrar las opciones del menú
    private void mostrarMenu() {
    	System.out.println("\n------------------------------------------------------");
    	System.out.println("----              Menú de Opciones                ----");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Hacer una reserva");
        System.out.println("2. Consultar detalles reserva");
        System.out.println("3. Ver todas mesas disponibles");
        System.out.println("4. Modificar info reserva");
        System.out.println("5. Cancelar una reserva");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    // Método para procesar opción seleccionada
    private void procesarOpcion(int opcion) {
        switch (opcion) {
	        case 0:
	        	System.out.println("\n------------------------------------------------------");
	        	System.out.println("--    Gracias por utilizar el Gestor de Reservas    --");
	            System.out.println("------------------------------------------------------");
	            break;
            case 1:
                hacerReserva();
                break;
            case 2:
            	consultarReserva();
                break;
            case 3:
                verTodasMesasDisponibles();
                break;
            case 4:
                modificarReserva();
                break;
            case 5:
                cancelarReserva();
                break;
            default:
                System.out.println("Opción no válida. Vuelva a intentar.");
                System.out.println("------------------------------------------------------");
                break;
        }
    }

    // 1- Método para crear una Reserva
    private void hacerReserva() {
    	// Ingreso datos Cliente para Reserva
        System.out.println("\n--- Hacer una reserva ---");
        System.out.print("\nIngrese nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("\nIngrese apellido del cliente: ");
        String apellido = scanner.next();
        System.out.print("\nIngrese teléfono del cliente: ");
        String telefono = scanner.next();
        Cliente cliente = new Cliente(nombre, apellido, telefono);
        // Ingreso cantidad válida personas para Reserva
        int numeroPersonas = obtenerCantidadPersonasValida();

        boolean decisionHora = false;
        int mesaElegida = 0;
        int hora = 0;
        // Chequeo validez horario
        while (!decisionHora) {
        	// Ingreso horario válido para Reserva
        	hora = obtenerHoraValida();
	        // Chequeo disponibilidad de horario para la Mesa
	        ArrayList<Mesa> mesasDisponibles = getMesasDisponibles(hora, numeroPersonas);
	        if (mesasDisponibles.size() == 1) {
	        	mesaElegida = mesasDisponibles.get(0).getNumeroMesa();
	        	decisionHora = true;
	        } else if (mesasDisponibles.size() > 1 ) {
	        	boolean mesaValida = false;
	        	while (!mesaValida) {
	        		System.out.println("\nIngrese la mesa que quiere reservar: ");
	        		int eleccionMesa = scanner.nextInt();
	        		for(Mesa m : mesasDisponibles) {
	        			if(m.getNumeroMesa() == eleccionMesa) {
	        				mesaElegida = eleccionMesa;
	        				mesaValida=true;
							break;
	        			}
	        		}
	        	}
	        	decisionHora = true;
	        } else {
	        	System.out.println("\n¿Desea reservar en otro horario? (S/N)");
	        	if (!scanner.next().equalsIgnoreCase("s")) {
	        		decisionHora = true;
	        	}
	        }
        }
        // Creación Reserva tras chequear validez de datos
        if (mesaElegida != 0 && hora != 0) {
        	System.out.println(restaurante.agregarReserva(cliente, numeroPersonas, hora, mesaElegida));
        }
    }
    
    // 2- Método para consultar una Reserva por id o apellido
    private void consultarReserva() {
    	System.out.println("\n--- Consultar una reserva ---");
    	System.out.println("\nIndique de qué forma desea consultar la reserva: ");
    	boolean opcionValida = false;
    	while (!opcionValida) {
    		System.out.println("1. ID de Reserva");
    		System.out.println("2. Apellido Cliente");
    		int opcionElegida = scanner.nextInt(); 
    		String buscar;
    		if(opcionElegida == 1) {
    			System.out.print("\nIngrese el ID de la reserva a consultar: ");
    			buscar = scanner.next();
    			System.out.println(restaurante.getInfoReserva(buscar, opcionElegida));
    			opcionValida = true;
    		} else if (opcionElegida == 2) {
    			System.out.print("\nIngrese el apellido de la reserva a consultar: ");
    			buscar = scanner.next();
    			System.out.println(restaurante.getInfoReserva(buscar, opcionElegida));
    			opcionValida = true;
    		} else {
    			System.out.println("Opción no válida. Vuelva a intentar.");
    			System.out.println("------------------------------------------------------");
    		}
    	}
    }    

    // 3- Método para ver todas las Mesas disponibles
    private void verTodasMesasDisponibles() {
    	System.out.println("\n--- Mesas disponibles ---");
    	ArrayList<String> mesasDisponibles = restaurante.getTodasMesasDisponibles();
      	for (String mesaInfo : mesasDisponibles) {
        	System.out.println(mesaInfo);
        }
    }
    
    // 4- Método para borrar una Reserva
    private void modificarReserva() {
    	System.out.println("\n--- Modificar una reserva ---");
    	System.out.print("\nIngrese el ID de la reserva a cancelar: ");
    	String idReserva = scanner.next();
    	if(restaurante.existeReserva(idReserva)) {
    		System.out.println("\nIndique qué infomación desea modificar de la reserva: ");
        	boolean opcionValida = false;
        	while (!opcionValida) {
        		System.out.println("1. Nombre Cliente");
        		System.out.println("2. Apellido Cliente");
        		System.out.println("3. Telefono Cliente");
        		System.out.println("4. Cantidad Comensales");
        		System.out.println("5. Cancelar Reserva");
        		int opcionElegida = scanner.nextInt(); 
        		String modificar;
        		switch (opcionElegida) {
                case 1: // Nombre
                	System.out.print("\nIngrese el nombre correcto para la reserva: ");
                	modificar = scanner.next();
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                	break;
                case 2: // Apellido
                	System.out.print("\nIngrese el apellido correcto para la reserva: ");
                	modificar = scanner.next();
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                    break;
                case 3: // Telefono
                	System.out.print("\nIngrese el teléfono correcto para la reserva: ");
                	modificar = scanner.next();
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                    break;
                case 4: // Cantidad comensales
            		System.out.print("\nIngrese la cantidad de comensales para la reserva: ");
            		modificar = scanner.next();
                	opcionValida = true;
                	boolean found = false;
                	//int indMesa = Integer.MAX_VALUE;
                	int capacMaxMesa = 1;
            		ArrayList<Reserva> reservas = restaurante.getListaReservas();
            		ArrayList<Mesa> mesas = restaurante.getListaMesas();
            		// Obtener capacidad máxima de Mesa de la Reserva
            		for(Reserva r : reservas) {
            			if(r.getId().equalsIgnoreCase(idReserva)) {
            				capacMaxMesa = mesas.get(r.getMesaAsignada()-1).getCapacidad();
            				found = true;
            			}
            		}
            		// Chequear si se encontró número de mesa y validez cantidad comensales para capacidad Mesa
            		if(found && Integer.parseInt(modificar) <= capacMaxMesa) {
            			System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
            		} else {
            			System.out.println("\nNo ha podido modificarse la Reserva con la cantidad de comensales ingresada.");
            		}
            		break;
                case 5: // Cancelar Reserva
                	opcionValida = true;
                	cancelarReserva();
                    break;
                default:
                    System.out.println("Opción no válida. Vuelva a intentar.");
                    System.out.println("------------------------------------------------------");
                    break;
        		}
        	}
    	} else {
    		System.out.println("No existe la reserva ingresada.");
    	}
    }
    
    // 5- Método para borrar una Reserva
    private void cancelarReserva() {
    	System.out.println("\n--- Cancelar una reserva ---");
    	System.out.print("\nIngrese el ID de la reserva a cancelar: ");
    	String idReserva = scanner.next();
        if(restaurante.existeReserva(idReserva)) {
        	System.out.println(restaurante.cancelarReserva(idReserva));
        } else {
        	System.out.println("\nLa reserva ingresada no existe.");
        }
    }
    
    // Método para ver Mesas disponibles según hora y cantidad de personas
    private ArrayList<Mesa> getMesasDisponibles(int hora, int numeroPersonas) {
    	ArrayList<Mesa> mesasDisponibles = restaurante.getMesasDisponibles(hora, numeroPersonas);
    	System.out.println("\n--- Mesas disponibles para las " + hora + "hs ---");
    	if (!mesasDisponibles.isEmpty()) {
    		for(Mesa mesa : mesasDisponibles) {
    			System.out.println("\n+ Mesa N° " + mesa.getNumeroMesa() + ": Capacidad para "
    					+ mesa.getCapacidad() + " personas.");
    		}
    	} else {
    		System.out.println("No hay mesas disponibles para las " + hora + "hs.");
    	}
    	return mesasDisponibles;
    }
    
    // Método para validar cantidad de personas
    private int obtenerCantidadPersonasValida() {
        int numeroPersonas;
        do {
            System.out.print("\nIngrese el número de personas (capacidad máxima: " + CAPAC_MAX + " personas): ");
            numeroPersonas = scanner.nextInt();
            if (numeroPersonas < 1 || numeroPersonas > CAPAC_MAX) {
                System.out.println("El número de personas ingresado es inválido.");
            }
        } while (numeroPersonas < 1 || numeroPersonas > CAPAC_MAX);
        return numeroPersonas;
    }
    
    // Método para validar hora de Reserva
    private int obtenerHoraValida() {
    	int hora;
    	do {
    		System.out.print("\nIngrese la hora de la reserva (" + HORA_MIN + "-" + HORA_MAX + "): ");
    		hora = scanner.nextInt();
    		if (hora < HORA_MIN || hora > HORA_MAX) {
    			System.out.println("El horario ingresado es inválido.");
    		}
    	} while (hora < HORA_MIN || hora > HORA_MAX);
    	return hora;
    }
}


