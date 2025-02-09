package com.app.gestor;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.app.modelo.atencionalpublico.Cliente;
import com.app.modelo.Mesa;
import com.app.modelo.Reserva;
import com.app.modelo.Restaurante;

public class GestorReservas implements MenuOpciones {
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
            opcion = verificarIngresoEntero(obtenerMenuOpciones());
            System.out.println("------------------------------------------------------");
            procesarOpcion(opcion);
        } while (opcion != 0);
        
        scanner.close();
    }

    // Método para mostrar las opciones del menú
    public String obtenerMenuOpciones() {
        return "\n------------------------------------------------------" +
				"\n----              Menú de Opciones                ----" +
                "\n------------------------------------------------------" +
                "\n1. Hacer una reserva" +
                "\n2. Consultar detalles reserva" +
                "\n3. Ver todas mesas disponibles" +
                "\n4. Modificar info reserva" +
                "\n5. Cancelar una reserva" +
                "\n0. Salir" +
                "\nSeleccione una opción: ";
    }

    // Método para procesar opción seleccionada
    public void procesarOpcion(int opcion) {
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
        String nombre = validarAtributoString("\nIngrese nombre del cliente: ", "nombre");
		String apellido = validarAtributoString("\nIngrese apellido del cliente: ", "apellido");
		String telefono = validarTelefono("\nIngrese teléfono del cliente: ");
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
	        		int eleccionMesa = verificarIngresoEntero("\nIngrese la mesa que quiere reservar: ");
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
    		int opcionElegida = verificarIngresoEntero("1. ID de Reserva\n2. Apellido Cliente\n");
    		String buscar;
    		if(opcionElegida == 1) {
    			System.out.print("\nIngrese el ID de la reserva a consultar: ");
    			buscar = scanner.next();
    			System.out.println(restaurante.getInfoReserva(buscar, opcionElegida));
    			opcionValida = true;
    		} else if (opcionElegida == 2) {
				buscar = validarAtributoString("\nIngrese el apellido de la reserva a consultar: ", "apellido");
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
        		int opcionElegida = verificarIngresoEntero("1. Nombre Cliente\n2. Apellido Cliente\n3. Telefono Cliente\n4. Cantidad Comensales\n5. Cancelar Reserva\n");
        		String modificar;
        		switch (opcionElegida) {
                case 1: // Nombre
					modificar = validarAtributoString("\nIngrese el nombre correcto para la reserva: ", "nombre");
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                	break;
                case 2: // Apellido
					modificar = validarAtributoString("\nIngrese el apellido correcto para la reserva: ", "apellido");
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                    break;
                case 3: // Telefono
					modificar = validarTelefono("\nIngrese el teléfono correcto para la reserva: ");
                	opcionValida = true;
                	System.out.println(restaurante.modificarReserva(idReserva, opcionElegida, modificar));
                    break;
                case 4: // Cantidad comensales
					int i = verificarIngresoEntero("\nIngrese la cantidad de comensales para la reserva: ");
            		modificar = Integer.toString(i);
                	opcionValida = true;
                	boolean found = false;
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
			numeroPersonas = verificarIngresoEntero("\nIngrese el número de personas (capacidad máxima: " + CAPAC_MAX + " personas): ");
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
			hora = verificarIngresoEntero("\nIngrese la hora de la reserva (" + HORA_MIN + "-" + HORA_MAX + "): ");
    		if (hora < HORA_MIN || hora > HORA_MAX) {
    			System.out.println("El horario ingresado es inválido.");
    		}
    	} while (hora < HORA_MIN || hora > HORA_MAX);
    	return hora;
    }

	// Método para validar ingreso de número entero
	private int verificarIngresoEntero(String mensaje) {
		int valor = -1;
		boolean valido = false;
		while (!valido) {
			try {
				System.out.print(mensaje);
				valor = scanner.nextInt();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Por favor, ingrese un número válido.");
				scanner.next();
			}
		}
		return valor;
	}

	// Método para validad ingreso nombre o apellido
	private String validarAtributoString(String mensaje, String atributo) {
		String valor = "";
		boolean valido = false;
		while (!valido){
			try {
				System.out.print(mensaje);
				valor = scanner.next();
				// Se analiza con una regular expression valores válidos para nombre/s
				if (valor == null || !valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
					// Se lanza excepción si cumple la condición
					throw new IllegalArgumentException("El " + atributo + " contiene caracteres no válidos.");
				} else {
					valido = true;
				}
			} catch (IllegalArgumentException e) {
				// Captura la excepción si es lanzada y muestra su mensaje
				System.out.println(e.getMessage());
			}
		}
		return valor;
	}

	// Método para validar ingreso teléfono (solo números, longitud entre 7 y 15)
	private String validarTelefono(String mensaje) {
		String telefono = "";
		boolean valido = false;
		while (!valido) {
			try {
				System.out.print(mensaje);
				telefono = scanner.next();
				// Se analiza con una regular expression valores válidos para nombre/s
				if (telefono == null || !telefono.matches("\\d{7,15}")) {
					// Se lanza excepción si cumple la condición
					throw new IllegalArgumentException("El número de teléfono debe contener entre 7 y 15 dígitos. Utilizar únicamente valores numéricos.");
				} else { valido = true; }
			} catch (IllegalArgumentException e) {
				// Captura la excepción si es lanzada y muestra su mensaje
				System.out.println(e.getMessage());
			}
		}
		return telefono;
	}
}


