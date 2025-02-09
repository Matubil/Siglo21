package Banco;

import Entidades.Cliente;
import Entidades.CuentaBancaria;
import Entidades.Trabajador;

import Interface.InformacionMostrable;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Exceptions.BankOperationException;


public class Banco {
    private ArrayList<Cliente> clientes;
    private ArrayList<CuentaBancaria> cuentas;
    private ArrayList<Trabajador> empleados;


    public Banco() {
        clientes = new ArrayList<>();
        cuentas = new ArrayList<>();
        empleados = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public Cliente buscarClientePorDni(String dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    //Función para convertir la primera letra de apellido y nombre en mayuscula
    private String toCamelCase(String input) {
        String[] words = input.split(" ");
        StringBuilder camelCaseString = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                camelCaseString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return camelCaseString.toString().trim();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) { // Bucle para mostrar el menú continuamente
            System.out.println("░░░░▄███▄░░░░==========================================================================================░░░░▄███▄░░░░");
            System.out.println("░░▄██|$|██▄░░   ************************** Bienvenido al Banco MatBank *****************************   ░░▄██|$|██▄░░");
            System.out.println("░▀███┼█┼███▀░==========================================================================================░▀███┼█┼███▀░");
            System.out.println("▄▄█████████▄▄==========================================================================================▄▄█████████▄▄");
            System.out.println();
            System.out.println("                                           Seleccione su rol: ");
            System.out.println("                                         ---------------------------");
            System.out.println("                                           1. Cliente");
            System.out.println("                                           2. Empleado");
            System.out.println("                                           3. Administrador");
            System.out.println("                                           0. Salir");
            System.out.println("                                         ---------------------------");
            System.out.print("Por favor, ingrese una opción: ");

            try{

                int rol = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                System.out.println(); // Espacio para mejorar la legibilidad

                switch (rol) {
                    case 1:
                        System.out.println("                                         ====================================");
                        System.out.println("                                                Bienvenido, Cliente");
                        System.out.println("                                         ====================================");
                        menuCliente();
                        break;
                    case 2:
                        System.out.println("                                         ====================================");
                        System.out.println("                                                Bienvenido, Empleado");
                        System.out.println("                                         ====================================");
                        menuEmpleado();
                        break;
                    case 3:
                        System.out.println("                                         ====================================");
                        System.out.println("                                                Bienvenido, Administrador");
                        System.out.println("                                         ====================================");
                        ingresarComoAdmin(scanner);
                        break;
                    case 0:
                        System.out.println("                                         ====================================");
                        System.out.println("                                         Saliendo... Gracias por usar MatBank!");
                        System.out.println("                                               Que tenga un buen día.");
                        System.out.println("                                         ====================================");
                        return; // Salir del programa
                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }catch(Exception e){
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
            }

            System.out.println(); // Espacio después del menú
        }
    }

    private void ingresarComoAdmin(Scanner scanner) {
        String usuario, clave;
        boolean accesoConcedido = false;

        while (!accesoConcedido) {
            System.out.print("Ingrese usuario: ");
            usuario = scanner.nextLine().toLowerCase();
            System.out.print("Ingrese clave: ");
            clave = scanner.nextLine().toLowerCase();

            // Verificar credenciales
            if (usuario.equals("admin") && clave.equals("admin")) {
                accesoConcedido = true;
                System.out.println("                                         ====================================");
                System.out.println("                                         ========   Acceso concedido  =======");
                System.out.println("                                         ====================================");

                while (true) {
                    System.out.println();
                    System.out.println("                               Puede crear un empleado o ver la lista de empleados.");
                    System.out.println("                                         ---------------------------------");
                    System.out.println("                                            1. Crear empleado");
                    System.out.println("                                            2. Mostrar empleados");
                    System.out.println("                                            0. Volver");
                    System.out.println("                                         ---------------------------------");
                    System.out.print("Seleccione una opción: ");

                    try {
                        int opcion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        switch (opcion) {
                            case 1:
                                crearEmpleado();
                                break;
                            case 2:
                                mostrarEmpleados(); // Llamar al método para mostrar empleados
                                break;
                            case 0:
                                return; // Salir del menú de administrador
                            default:
                                System.out.println("Opción no válida.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Debe ingresar un número válido.");
                        scanner.nextLine(); // Limpiar el buffer de entrada para evitar bucles infinitos
                    }
                }
            } else {
                System.out.println("Credenciales incorrectas. ¿Desea intentar de nuevo? (s/n)");
                String respuesta = scanner.nextLine().toLowerCase();
                if (!respuesta.equals("s")) {
                    System.out.println("Volviendo al menú anterior...");
                    return;
                }
            }
        }
    }

    private void crearEmpleado() {
        Scanner scanner = new Scanner(System.in);
        String nombre, apellido, dni, sector, puesto;

        // Validar nombre
        do {
            System.out.print("Ingrese nombre (solo letras, no puede estar vacío): ");
            nombre = scanner.nextLine();
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                System.out.println("Error: El nombre solo puede contener letras y espacios.");
            }
        } while (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
        nombre = toCamelCase(nombre); // Convertir a camel case

        // Validar apellido
        do {
            System.out.print("Ingrese apellido (solo letras, no puede estar vacío): ");
            apellido = scanner.nextLine();
            if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                System.out.println("Error: El apellido solo puede contener letras y espacios.");
            }
        } while (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
        apellido = toCamelCase(apellido); // Convertir a camel case

        // Validar DNI
        do {
            System.out.print("Ingrese DNI (7-8 dígitos): ");
            dni = scanner.nextLine();
            if (!dni.matches("\\d{7,8}")) {
                System.out.println("Error: El DNI debe contener entre 7 y 8 dígitos numéricos.");
            }
        } while (!dni.matches("\\d{7,8}"));

        System.out.print("Ingrese sector del empleado: ");
        sector = scanner.nextLine();
        sector = toCamelCase(sector); // Convertir a camel case
        System.out.print("Ingrese puesto del empleado: ");
        puesto = scanner.nextLine();
        puesto = toCamelCase(puesto);

        Trabajador nuevoEmpleado = new Trabajador(nombre, apellido, dni, sector, puesto);
        empleados.add(nuevoEmpleado); // Agregar el empleado a la lista
        System.out.println("Empleado creado exitosamente: " + nuevoEmpleado);
    }

    private void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("Lista de Empleados:");
        int index = 1; // Contador para enumerar empleados

        // Muestra la lista básica de empleados
        for (InformacionMostrable empleado : empleados) {
            System.out.println(index + ". " + ((Trabajador) empleado).getNombre() + " " + ((Trabajador) empleado).getApellido());
            index++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el número del empleado para más información (o 0 para volver): ");

        try {
            int opcion = scanner.nextInt();
            if (opcion == 0) {
                return; // Salir si el usuario elige 0
            } else if (opcion > 0 && opcion <= empleados.size()) {
                // Mostrar información detallada del empleado seleccionado
                InformacionMostrable empleadoSeleccionado = empleados.get(opcion - 1);
                empleadoSeleccionado.mostrarInformacion(); // Llamada polimórfica
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        } catch (Exception e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.nextLine(); // Limpiar el buffer de entrada
        }
    }



    public void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("                                                  Menú del Cliente:");
            System.out.println("                                         ---------------------------------");
            System.out.println("                                           1. Consultar saldo");
            System.out.println("                                           2. Realizar depósito");
            System.out.println("                                           3. Realizar retiro");
            System.out.println("                                           4. Actualizar Datos de Contacto");
            System.out.println("                                           0. Salir");
            System.out.println("                                         ---------------------------------");
            System.out.print("Seleccione una opción: ");

            try{
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        consultarSaldo(scanner);
                        break;
                    case 2:
                        realizarDeposito(scanner);
                        break;
                    case 3:
                        realizarRetiro(scanner);
                        break;
                    case 4:
                        actualizarDatosContacto(scanner);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe de ngresar un número válido y que esté en el menú.");
                scanner.nextLine(); // Limpiar el buffer de entrada para evitar bucles infinitos
                opcion = -1; // Asignar un valor que no salga del bucle
            }

        } while (opcion != 0);
    }

    private void consultarSaldo(Scanner scanner) {

        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.next();
        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            System.out.println("Cuentas del cliente:");
            ArrayList<CuentaBancaria> cuentas = cliente.getCuentas();
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + ". " + cuentas.get(i));
            }

            System.out.print("Seleccione la cuenta para consultar saldo (número): ");
            int index = scanner.nextInt();
            if (index > 0 && index <= cuentas.size()) {

                CuentaBancaria cuentaSeleccionada = cuentas.get(index - 1);
                System.out.println("Saldo de la cuenta: " + cuentaSeleccionada.consultarSaldo());

            } else {
                System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void realizarDeposito(Scanner scanner) {
        try {
            System.out.print("Ingrese el DNI del cliente: ");
            String dni = scanner.next();
            Cliente cliente = buscarClientePorDni(dni);

            if (cliente != null) {
                System.out.println("Cuentas del cliente:");
                ArrayList<CuentaBancaria> cuentas = cliente.getCuentas();

                for (int i = 0; i < cuentas.size(); i++) {
                    System.out.println((i + 1) + ". " + cuentas.get(i).obtenerInfoCuenta());
                }
                System.out.print("Seleccione la cuenta para depositar (número): ");
                int index = scanner.nextInt() - 1; // Suponiendo que la cuenta es válida

                CuentaBancaria cuentaSeleccionada = cliente.getCuentas().get(index);
                System.out.print("Ingrese el monto a depositar: ");
                double monto = scanner.nextDouble();

                // Validación de monto positivo
                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor que 0.");
                    return;
                }

                cuentaSeleccionada.depositar(monto);
                System.out.println("Depósito realizado. Nuevo saldo: " + cuentaSeleccionada.consultarSaldo());
                // Mostrar detalles de la transacción
                System.out.println("Transacción: " + cuentaSeleccionada.getTransacciones().get(cuentaSeleccionada.getTransacciones().size() - 1).mostrarDetalle() + " realizada con éxito");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error en la operación: " + e.getMessage());
        }
    }

    private void realizarRetiro(Scanner scanner) {
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.next();
        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            ArrayList<CuentaBancaria> cuentas = cliente.getCuentas();

            // Verificar si el cliente tiene cuentas
            if (cuentas.isEmpty()) {
                System.out.println("El cliente no tiene cuentas disponibles.");
                return;
            }

            System.out.println("Cuentas del cliente:");
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + ". " + cuentas.get(i).obtenerInfoCuenta());
            }

            System.out.print("Seleccione la cuenta para retirar (número): ");
            int index = scanner.nextInt() - 1; // Restar 1 para coincidir con el índice de la lista

            // Verificar si el índice es válido
            if (index < 0 || index >= cuentas.size()) {
                System.out.println("Índice de cuenta inválido.");
                return;
            }

            CuentaBancaria cuentaSeleccionada = cuentas.get(index);
            System.out.print("Ingrese el monto a retirar: ");
            double monto = scanner.nextDouble();

            // Validación de saldo insuficiente
            if (cuentaSeleccionada.consultarSaldo() < monto) {
                System.out.println("Saldo insuficiente para realizar el retiro.");
                return;
            }

            cuentaSeleccionada.retirar(monto);
            System.out.println("Retiro realizado. Nuevo saldo: " + cuentaSeleccionada.consultarSaldo());
            System.out.println("Transacción: " + cuentaSeleccionada.getTransacciones().get(cuentaSeleccionada.getTransacciones().size() - 1).mostrarDetalle() + " realizado con éxito");

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }


    private void actualizarDatosContacto(Scanner scanner) {
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.next();
        scanner.nextLine(); // Limpiar el buffer después de leer el DNI
        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            // Mostrar información actual
            System.out.println("Información actual del cliente:");
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Correo electrónico: " + cliente.getEmail()); // Asumiendo que tienes un método getEmail()

            String nuevoTelefono = cliente.getTelefono(); // Mantener el teléfono actual por defecto
            String nuevaDireccion = cliente.getDireccion(); // Mantener la dirección actual por defecto
            String nuevoEmail = cliente.getEmail(); // Mantener el email actual por defecto

            // Preguntar si desea cambiar el teléfono
            System.out.print("¿Desea cambiar el teléfono? (1: sí / 2: no): ");
            String respuestaTelefono = scanner.nextLine();
            if (respuestaTelefono.equals("1")) {
                try {
                    do {
                        System.out.print("Ingrese nuevo teléfono (solo números, mínimo 8 dígitos): ");
                        nuevoTelefono = scanner.nextLine();
                        if (!nuevoTelefono.matches("\\d{8,}")) {
                            throw new BankOperationException("Error: El teléfono debe contener al menos 8 dígitos numéricos.");
                        }
                    } while (!nuevoTelefono.matches("\\d{8,}"));
                } catch (BankOperationException e) {
                    System.out.println(e.getMessage());
                    return; // Salir si hay error
                }
            }

            // Preguntar si desea cambiar la dirección
            System.out.print("¿Desea cambiar la dirección? (1: sí / 2: no): ");
            String respuestaDireccion = scanner.nextLine();
            if (respuestaDireccion.equals("1")) {
                try {
                    do {
                        System.out.print("Ingrese nueva dirección: ");
                        nuevaDireccion = scanner.nextLine();
                        if (nuevaDireccion.isEmpty()) {
                            throw new BankOperationException("Error: La dirección no puede estar vacía.");
                        }
                    } while (nuevaDireccion.isEmpty());
                } catch (BankOperationException e) {
                    System.out.println(e.getMessage());
                    return; // Salir si hay error
                }
            }

            // Preguntar si desea cambiar el email
            System.out.print("¿Desea cambiar el correo electrónico? (1: sí / 2: no): ");
            String respuestaEmail = scanner.nextLine();
            if (respuestaEmail.equals("1")) {
                try {
                    do {
                        System.out.print("Ingrese nuevo correo electrónico: ");
                        nuevoEmail = scanner.nextLine();
                        if (!nuevoEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Validación básica de email
                            throw new BankOperationException("Error: El correo electrónico no es válido.");
                        }
                    } while (!nuevoEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
                } catch (BankOperationException e) {
                    System.out.println(e.getMessage());
                    return; // Salir si hay error
                }
            }

            // Llamar al método para actualizar datos
            cliente.actualizarDatos(nuevoTelefono, nuevaDireccion, nuevoEmail);

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }



    public void menuEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("                                                Menú del Empleado:");
            System.out.println("                                         ---------------------------------");
            System.out.println("                                            1. Crear cliente");
            System.out.println("                                            2. Crear cuenta bancaria");
            System.out.println("                                            3. Consultar clientes");
            System.out.println("                                            4. Volver atrás");
            System.out.println("                                            0. Salir");
            System.out.println("                                         ---------------------------------");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        crearCliente();
                        break;
                    case 2:
                        crearCuentaBancaria();
                        break;
                    case 3:
                        consultarClientes();
                        break;
                    case 4:
                        mostrarMenu(); // Volver al menú principal
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
                opcion = -1; // Asignar un valor no válido para que el bucle continúe
            }
        } while (opcion != 0);
    }

    private void crearCliente() {
        Scanner scanner = new Scanner(System.in);
        String nombre, apellido, dni, direccion, telefono, email;

        try {
            // Validar nombre (no vacío y solo letras)
            do {
                System.out.print("Ingrese nombre (solo letras, no puede estar vacío): ");
                nombre = toCamelCase(scanner.nextLine());
                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                    throw new BankOperationException("Error: El nombre solo puede contener letras y espacios.");
                }
            } while (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));

            // Validar apellido (no vacío y solo letras)
            do {
                System.out.print("Ingrese apellido (solo letras, no puede estar vacío): ");
                apellido = toCamelCase(scanner.nextLine());
                if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                    throw new BankOperationException("Error: El apellido solo puede contener letras y espacios.");
                }
            } while (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));

            // Validar DNI
            do {
                System.out.print("Ingrese DNI (7-8 dígitos): ");
                dni = scanner.nextLine();
                if (!dni.matches("\\d{7,8}")) {
                    throw new BankOperationException("Error: El DNI debe contener entre 7 y 8 dígitos numéricos.");
                }
            } while (!dni.matches("\\d{7,8}"));

            // Ingresar dirección (sin validación especial)
            System.out.print("Ingrese dirección: ");
            direccion = scanner.nextLine();

            // Validar teléfono
            do {
                System.out.print("Ingrese teléfono (solo números, mínimo 8 dígitos): ");
                telefono = scanner.nextLine();
                if (!telefono.matches("\\d{8,}")) {
                    throw new BankOperationException("Error: El teléfono debe contener al menos 8 dígitos numéricos.");
                }
            } while (!telefono.matches("\\d{8,}"));

            // Validar email
            do {
                System.out.print("Ingrese email (formato válido): ");
                email = scanner.nextLine();
                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    throw new BankOperationException("Error: El email ingresado no tiene un formato válido.");
                }
            } while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));

            // Si todas las validaciones son correctas, se crea el cliente
            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, direccion, telefono, email);
            agregarCliente(nuevoCliente);

            System.out.println("Cliente agregado exitosamente:");
            System.out.println(nuevoCliente); // Imprimir datos del cliente
        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }
    }


    private void crearCuentaBancaria() {
        Scanner scanner = new Scanner(System.in);

        String dni;
        try {
            do {
                System.out.print("Ingrese el DNI del cliente (solo números): ");
                dni = scanner.nextLine();
                if (!dni.matches("\\d{7,8}")) {
                    throw new BankOperationException("Error: El DNI debe contener entre 7 y 8 dígitos numéricos.");
                }
            } while (!dni.matches("\\d{7,8}"));

            Cliente cliente = buscarClientePorDni(dni);
            if (cliente != null) {
                double saldoInicial;
                do {
                    System.out.print("Ingrese saldo inicial (solo números): ");
                    String saldoInput = scanner.nextLine();
                    if (!saldoInput.matches("\\d+(\\.\\d{1,2})?")) {
                        throw new BankOperationException("Error: El saldo debe ser un número válido.");
                    } else {
                        saldoInicial = Double.parseDouble(saldoInput);
                    }
                } while (saldoInicial < 0); // Repetir hasta que se ingrese un saldo válido

                // Generar número de cuenta aleatorio de 16 dígitos
                String numeroCuenta = generarNumeroDeCuenta();
                CuentaBancaria nuevaCuenta = new CuentaBancaria(numeroCuenta, cliente, saldoInicial);

                agregarCuenta(nuevaCuenta);
                cliente.agregarCuenta(nuevaCuenta); // Añadimos la cuenta al cliente

                System.out.println("Cuenta creada exitosamente:");
                System.out.println(nuevaCuenta); // Imprimir datos de la cuenta
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void consultarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Mostrar la lista básica de clientes
        System.out.println("Lista de Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNombre() + " " + cliente.getApellido() + " - DNI: " + cliente.getDni());
        }

        // Solicitar al empleado que seleccione un cliente
        System.out.println();
        System.out.println("Seleccione un cliente para ver más información (número): ");
        int index = scanner.nextInt() - 1; // Restar 1 para que coincida con el índice de la lista

        // Validar el índice
        if (index < 0 || index >= clientes.size()) {
            System.out.println("Índice de cliente inválido.");
            return;
        }

        // Mostrar la información detallada del cliente seleccionado
        Cliente clienteSeleccionado = clientes.get(index);
        clienteSeleccionado.mostrarInformacion();
    }


    // Método para generar número de cuenta aleatorio de 16 dígitos
    private String generarNumeroDeCuenta() {
        Random random = new Random();
        StringBuilder numeroCuenta = new StringBuilder();

        // Generar 16 dígitos aleatorios
        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(10); // Genera un número entre 0 y 9
            numeroCuenta.append(digito);
        }

        return numeroCuenta.toString();
    }
}
