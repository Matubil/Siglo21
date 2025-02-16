
// Clase principal para probar la lista doblemente enlazada
public class GestionProductos {

    public static void main(String[] args) {

        ListaDobleEnlazada lista = new ListaDobleEnlazada();

        // Insertar productos de prueba
        lista.insertarOrdenado(102, "Ladrillo Naranja", 500.0);
        lista.insertarOrdenado(101, "Cemento", 1200.5);
        lista.insertarOrdenado(104, "Arena Fina", 300.75);
        lista.insertarOrdenado(103, "Cal Hidratada", 850.0);
        lista.insertarOrdenado(101, "Cemento", 1200.5); // Intento de duplicado

        // Mostrar lista de productos
        lista.mostrarLista();
    }
}