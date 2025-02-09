import java.util.Scanner;

// Clase encargada de gestionar las palabras
class GestorPalabras {
    private String[] palabras;

    // Metodo para almacenar las palabras en el arreglo
    public void almacenarPalabras(String[] palabras) {
        this.palabras = palabras;
    }

    // Metodo para listar todas las palabras almacenadas
    public void listarPalabras() {
        System.out.println("Palabras almacenadas:");
        for (int i = 0; i < palabras.length; i++) {
            System.out.println((i + 1) + ". " + palabras[i]);
        }
    }

    // Metodo para contar la frecuencia de una palabra en el arreglo
    public int contarFrecuencia(String palabra) {
        int contador = 0;
        for (String p : palabras) {
            if (p.equalsIgnoreCase(palabra)) {
                contador++;
            }
        }
        return contador;
    }
}

// Clase principal con el metodo main
public class ProgramaPrincipal3 {

    // Función validadora para leer un int
    public static int leerEnteroPositivo(Scanner scanner) {
        int valor = -1;
        while (valor <= 0) {
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del salto de línea
                if (valor <= 0) {
                    System.out.println("El número debe ser mayor que 0. Intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        }
        return valor;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Llamada a la función validadora para leer la cantidad de palabras
        System.out.print("Ingrese la cantidad de palabras que desea almacenar: ");
        int cantidad = leerEnteroPositivo(scanner);

        String[] arregloPalabras = new String[cantidad];

        // Ingreso de palabras por teclado
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese la palabra " + (i + 1) + ": ");
            arregloPalabras[i] = scanner.nextLine();
        }

        // Crear instancia del GestorPalabras y almacenar las palabras
        GestorPalabras gestor = new GestorPalabras();
        gestor.almacenarPalabras(arregloPalabras);

        // Listar las palabras ingresadas
        gestor.listarPalabras();

        // Solicitar al usuario elegir una posicion para contar la frecuencia
        System.out.print("\nIngrese la posicion de la palabra para contar su frecuencia (1 a " + cantidad + "): ");
        int posicion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (posicion >= 1 && posicion <= cantidad) {
            String palabraSeleccionada = arregloPalabras[posicion - 1];
            int frecuencia = gestor.contarFrecuencia(palabraSeleccionada);

            System.out.println("La palabra '" + palabraSeleccionada + "' aparece " + frecuencia + " veces en el arreglo.");
        } else {
            System.out.println("Posicion invalida.");
        }

        scanner.close();
    }
}
