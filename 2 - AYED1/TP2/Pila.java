public class Pila {

    class Nodo {
        int info; // Valor almacenado en el nodo
        Nodo sig; // Referencia al siguiente nodo
    }

    private Nodo raiz; // Nodo raíz de la pila

    public Pila() {
        raiz = null; // Inicialmente la pila está vacía
    }

    // Método para apilar un elemento en la pila (LIFO)
    public void apilar(int x) {

        Nodo nuevo = new Nodo(); // Se crea un nuevo nodo
        nuevo.info = x; // Se asigna el valor al nodo

        if (raiz == null) {
            nuevo.sig = null; // Si la pila está vacía, no tiene siguiente nodo
        }
        else
        {
            nuevo.sig = raiz; // El nuevo nodo apunta al nodo anterior
        }

        raiz = nuevo; // Se actualiza la raíz de la pila
    }

    // Método para desapilar un elemento (eliminar y devolver el último ingresado)
    public int desapilar() {

        if (raiz != null) {

            int valor = raiz.info; // Se obtiene el valor del nodo superior
            raiz = raiz.sig; // Se elimina el nodo superior actualizando la raíz

            return valor; // Se devuelve el valor desapilado
        }

        System.out.println("Error! La pila está vacía, no se puede desapilar");
        return -1; // Retorno un valor de error
    }

    // Método para mostrar el contenido de la pila
    public void verContenido() {

        if (raiz != null) {

            Nodo actual = raiz;
            System.out.println("\nEl contenido de la pila es:");

            while (actual != null) {
                System.out.println(actual.info); // Se imprime el valor del nodo actual
                actual = actual.sig; // Se avanza al siguiente nodo
            }
        }

        System.out.println("No hay contenido dentro de la pila, ya que está vacía"); // Si la pila está vacía, se muestra un mensaje

    }

    public static void main(String[] args) {
        Pila pila1 = new Pila();
        pila1.apilar(10);
        pila1.apilar(40);
        pila1.apilar(3);

        pila1.verContenido(); // Muestro el contenido antes de desapilar

        System.out.println("\nCima de la pila: " + pila1.desapilar()); // Desapilar y mostrar el valor eliminado

        pila1.verContenido(); // Muestro el contenido después de desapilar
    }
}
