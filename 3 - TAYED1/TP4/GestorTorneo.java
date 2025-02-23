import java.util.Scanner;

// Clase principal que maneja la entrada de datos y ejecución del programa
public class GestorTorneo {
    static Scanner scanner = new Scanner(System.in);

    // Función para leer un número entero válido
    public static int leerEntero(String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                break;
            } else {
                System.out.println("⚠️ Entrada inválida. Debes ingresar un número entero.\n");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        }
        return numero;
    }

    // Función para leer un nombre de equipo válido (no vacío ni solo espacios)
    public static String leerNombreEquipo(String mensaje) {
        String nombre;
        while (true) {
            System.out.print(mensaje);
            nombre = scanner.nextLine().trim(); // Eliminar espacios en blanco al principio y al final
            if (!nombre.isEmpty()) {
                break;
            } else {
                System.out.println("⚠️ El nombre del equipo no puede estar vacío.");
            }
        }
        return nombre;
    }

    public static void main(String[] args) {
        TablaPosiciones tabla = new TablaPosiciones(8);

        System.out.println("⚽ Ingrese los equipos y sus puntos ⚽");

        // Validar que el número de equipos esté entre 5 y 8
        int numEquipos;
        do {
            numEquipos = leerEntero("¿Cuántos equipos desea ingresar? (Entre 5 y 8): ");
            if (numEquipos < 5 || numEquipos > 8) {
                System.out.println("❌ ¡El número de equipos debe estar entre 5 y 8!\n");
            }
        } while (numEquipos < 5 || numEquipos > 8);

        // Ingresar los equipos y sus puntos
        for (int i = 0; i < numEquipos; i++) { // Se ingresan entre 5 y 8 equipos
            String nombre = leerNombreEquipo("\nIngrese el nombre del equipo #" + (i + 1) + ": ");

            int puntos;
            do {
                puntos = leerEntero("Ingrese los puntos para " + nombre + ": ");
                if (puntos < 0) {
                    System.out.println("❌ Los puntos deben ser positivos.");
                }
            } while (puntos < 0);

            tabla.agregarEquipo(nombre, puntos);
        }

        tabla.ordenarEquipos();
        tabla.mostrarTabla();

        scanner.close();
    }
}
