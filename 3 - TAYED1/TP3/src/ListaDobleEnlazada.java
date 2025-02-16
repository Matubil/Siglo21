
// Clase Producto que representa cada ítem en la lista
class Producto {

    int codigo;
    String descripcion;
    double precio;

    public Producto(int codigo, String descripcion, double precio)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}

// Clase Nodo para la lista doblemente enlazada
class Nodo {
    Producto prod;
    Nodo sig;
    Nodo pre;

    public Nodo(Producto prod) {
        this.prod = prod;
    }
}

// Clase ListaDobleEnlazada para manejar los productos ordenados por código
public class ListaDobleEnlazada {
    private Nodo cabeza;

    // Método para insertar un producto ordenado por código
    public void insertarOrdenado(int codigo, String descripcion, double precio)
    {
        Producto nuevoProducto = new Producto(codigo, descripcion, precio);
        Nodo nuevo = new Nodo(nuevoProducto);

        if (cabeza == null)
        {
            cabeza = nuevo;
            return;
        }

        Nodo actual = cabeza;
        Nodo previo = null;

        while (actual != null && actual.prod.codigo < codigo)
        {
            previo = actual;
            actual = actual.sig;
        }

        // Verificar si el código ya existe
        if (actual != null && actual.prod.codigo == codigo)
        {
            System.out.println("❌ Error: El código " + codigo + " ya existe en la lista.");
            return;
        }

        // Insertar en la posición correcta
        if (previo == null) {       // Insertar al inicio

            nuevo.sig = cabeza;
            cabeza.pre = nuevo;
            cabeza = nuevo;

        }
        else {                      // Insertar en el medio o al final

            nuevo.sig = actual;
            nuevo.pre = previo;
            previo.sig = nuevo;

            if (actual != null)
                actual.pre = nuevo;
        }
    }

    // Método para mostrar los productos en la lista
    public void mostrarLista() {
        if (cabeza == null) {
            System.out.println("⚠️ La lista de productos está vacía.");
            return;
        }

        Nodo actual = cabeza;
        System.out.println("📦 Lista de productos: \n");

        while (actual != null) {

            System.out.println("✅ Código: " + actual.prod.codigo + " | Descripción: " + actual.prod.descripcion + " | Precio: $" + actual.prod.precio);
            actual = actual.sig;
        }
    }
}