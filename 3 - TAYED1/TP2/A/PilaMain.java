import java.util.Scanner;

class Pila {
    private int[] arreglo;
    private int top; // Indica la posición del último elemento

    // Constructor: inicializa la pila con un tamaño específico
    public Pila(int capacidad) {
        arreglo = new int[capacidad];
        top = -1; // Indica que la pila está vacía
    }

    // Método para agregar un número a la pila (push)
    public void apilar(int numero) {
        if (top == arreglo.length - 1) {
            System.out.println("❌ La pila está llena. No se puede apilar más elementos.");
        }
        else {
            top++;
            arreglo[top] = numero;
            System.out.println("✅ El número " + numero + " apilado correctamente.");
        }
    }

    // Método para retirar un elemento de la pila (pop)
    public int desapilar() {
        if (estaVacia()) {
            System.out.println("⚠️ La pila está vacía. No hay elementos para desapilar.");
            return -1; // Valor de error
        }
        else {
            int elemento = arreglo[top];
            top--; // Eliminamos el último elemento
            System.out.println("🗑️ Elemento " + elemento + " desapilado.");
            return elemento;
        }
    }

    // Método para verificar si la pila está vacía
    public boolean estaVacia() {
        return top == -1;
    }

    // Método para mostrar los elementos de la pila
    public void mostrarPila() {
        if (estaVacia()) {
            System.out.println("⚠️ La pila está vacía.");
        }
        else {
            System.out.print("📦 Contenido de la pila: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(arreglo[i] + " ");
            }
            System.out.println();
        }
    }
}

// Clase principal con el menú de opciones
public class PilaMain {
    private static Scanner scanner = new Scanner(System.in);

    // Función para leer un número entero válido
    public static int leerEntero(String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                break;
            }
            else {
                System.out.println("⚠️ Entrada inválida. Debes ingresar un número entero.\n");
                scanner.next(); // Limpia la entrada incorrecta
            }
        }
        return numero;
    }

    public static void main(String[] args) {

        int capacidad;
        do {
            capacidad = leerEntero("🔢 Ingresa el tamaño de la pila: ");
            if (capacidad <= 0) {
                System.out.println("⚠️ El tamaño debe ser mayor a 0.");
            }
        } while (capacidad <= 0);

        Pila pila = new Pila(capacidad);

        int opcion;
        do {
            System.out.println("\n📌 Menú de Pila:");
            System.out.println("1️⃣ Apilar un elemento");
            System.out.println("2️⃣ Desapilar un elemento");
            System.out.println("3️⃣ Ver si la pila está vacía");
            System.out.println("4️⃣ Mostrar pila");
            System.out.println("0️⃣ Salir");

            opcion = leerEntero("👉 Ingresa una opción: ");

            switch (opcion) {
                case 1:
                    int elemento = leerEntero("🔹 Ingresa un número para apilar: ");
                    pila.apilar(elemento);
                    break;

                case 2:
                    pila.desapilar();
                    break;

                case 3:
                    if (pila.estaVacia()) {
                        System.out.println("✅ La pila está vacía.");
                    } else {
                        System.out.println("❌ La pila NO está vacía.");
                    }
                    break;

                case 4:
                    pila.mostrarPila();
                    break;

                case 0:
                    System.out.println("👋 Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida. Intenta nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
