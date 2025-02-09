

//Main de la Consigna 3

import java.util.Scanner;

public class Main {

    // Función validadora para leer un int
    public static int leerEnteroPositivo(Scanner scanner) {
        int cantidad = -1;
        while (cantidad <= 0) {
            if (scanner.hasNextInt()) {
                cantidad = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del salto de línea
                if (cantidad <= 0) {
                    System.out.println("El número debe ser mayor que 0. Intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        }
        return cantidad;
    }

    // Función para validar que la palabra no esté vacía
    public static String leerPalabraNoVacia(Scanner scanner, int posicion) {
        String palabra;
        while (true) {
            System.out.print("Ingrese la palabra " + (posicion + 1) + ": ");
            palabra = scanner.nextLine();

            // Verifica si la palabra está vacía
            if (!palabra.trim().isEmpty()) {
                break; // Sale del bucle si la palabra no está vacía
            } else {
                System.out.println("La palabra " + (posicion + 1) + " no puede estar vacía. Intente nuevamente.");
            }
        }
        return palabra;
    }

    // Función para leer la posición y validarla
    public static int leerPosicionValida(Scanner scanner, int cantidad) {
        int posicionPalabra = -1;
        while (true) {
            System.out.print("Ingrese la posición de la palabra para contar su frecuencia de letras (1 a " + cantidad + "): ");

            if (scanner.hasNextInt()) {
                posicionPalabra = scanner.nextInt() - 1;  // Convertir a índice (restar 1)
                scanner.nextLine(); // Limpiar el buffer

                if (posicionPalabra >= 0 && posicionPalabra < cantidad) {
                    break; // Salir del bucle si la posición es válida
                } else {
                    System.out.println("La posición debe estar entre 1 y " + cantidad + ". Intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        }
        System.out.println(); //Espaciado para mejor comprensión en la consola

        return posicionPalabra;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Llamada a la función validadora para leer la cantidad de palabras
        System.out.print("Bienvenido! Ingrese la cantidad de palabras que desea almacenar: ");
        int cantidad = leerEnteroPositivo(scanner);

        String[] arregloPalabras = new String[cantidad];

        // Solicitar palabras
        for (int i = 0; i < cantidad; i++) {
            arregloPalabras[i] = leerPalabraNoVacia(scanner, i);
        }

        System.out.println(); //Espaciado para mejor comprensión en la consola

        // Listar todas las palabras almacenadas
        System.out.println("Palabras almacenadas:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ": " + arregloPalabras[i]);
        }

        Dos contadorFrecuencia = new Dos();

        // Llama a la función para leer la posición válida
        int posicionPalabra = leerPosicionValida(scanner, cantidad);


        // Listar frecuencia o devuelve error
        int[] contadorLetras = contadorFrecuencia.contarFrecuencia(arregloPalabras[posicionPalabra]);
        System.out.println("A continuación se muestra la frecuencia de letras en la palabra '" + arregloPalabras[posicionPalabra] + "':");
        contadorFrecuencia.imprimirFrecuencia(contadorLetras);
    }
}