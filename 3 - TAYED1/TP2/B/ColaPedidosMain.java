
// Clase Nodo para la lista enlazada
class Nodo {
    Pedido pedido;
    Nodo siguiente;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.siguiente = null;
    }
}

// Clase Pedido que representa un pedido de la fábrica
class Pedido {
    int codigo;
    String cliente;
    int cantidadLadrillos;

    public Pedido(int codigo, String cliente, int cantidadLadrillos) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.cantidadLadrillos = cantidadLadrillos;
    }

    public void mostrarPedido() {
        System.out.println("🧱 Pedido Nro: " + codigo + " | Cliente: " + cliente + " | Cantidad: " + cantidadLadrillos);
    }
}

// Clase Cola implementada con listas enlazadas
class Cola {
    private Nodo frente;
    private Nodo finalCola;

    public Cola() {
        this.frente = this.finalCola = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(Pedido pedido) {
        Nodo nuevoNodo = new Nodo(pedido);
        if (estaVacia()) {
            frente = finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
        System.out.println("📥 El pedido con código: " + pedido.codigo + " fue agregado con éxito" );
    }

    public Pedido desencolar() {
        if (estaVacia()) {
            System.out.println("⚠️ La cola está vacía. No hay pedidos para procesar.");
            return null;
        }
        Pedido pedido = frente.pedido;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null; // Si la cola queda vacía
        }
        System.out.println("📤 Procesando pedido: #" + pedido.codigo);
        return pedido;
    }

    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("⚠️ No hay pedidos en espera.");
            return;
        }
        System.out.println("\n📜 Listado de los pedidos en cola:");
        Nodo actual = frente;
        while (actual != null) {
            actual.pedido.mostrarPedido();
            actual = actual.siguiente;
        }
    }
}

// Clase principal para probar la cola de pedidos
public class ColaPedidosMain {
    public static void main(String[] args) {
        Cola colaPedidos = new Cola();

        // Creación de pedidos de prueba
        Pedido pedido1 = new Pedido(101, "Cliente A", 500);
        Pedido pedido2 = new Pedido(102, "Cliente B", 300);
        Pedido pedido3 = new Pedido(103, "Cliente C", 700);
        Pedido pedido4 = new Pedido(104, "Cliente D", 450);

        // Agregando pedidos a la cola
        colaPedidos.encolar(pedido1);
        colaPedidos.encolar(pedido2);
        colaPedidos.encolar(pedido3);
        colaPedidos.encolar(pedido4);

        // Mostrando el contenido de la cola
        colaPedidos.mostrarCola();

        // Procesando los pedidos en orden de llegada
        System.out.println("\n🔄 Procesando pedidos...");
        colaPedidos.desencolar();
        colaPedidos.desencolar();

        // Mostrando la cola después de procesar algunos pedidos
        colaPedidos.mostrarCola();
    }
}
