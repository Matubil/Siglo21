//Nombre: Nombre del alumno
//Legajo: INFXXXXX
//DNI: XXXXXXXX


// Clase de Arbol Binario de Búsquada ABB
public class ABB {


    //Clase Nodo de un ABB
    class Nodo {
        int clave;
        Nodo izquierda, derecha;

        public Nodo(int data) {
            clave = data;
            izquierda = derecha = null;
        }
    }

    // Nodo raiz, dato referencial
    Nodo raiz;

    // Constructor de ABB => se inicializa como arbol vacío
    ABB() {
        raiz = null;
    }

    //Borrar un Nodo de un árbol ABB
    void borrarClave(int clave) {
        raiz = borrar_Recursivo(raiz, clave);
    }

    //Borrado recursivo
    Nodo borrar_Recursivo(Nodo raiz, int clave) {
        //Si el Arbol está vacio
        if (raiz == null) return raiz;

        //Recorrer el árbol
        if (clave < raiz.clave) //Recorrer el sub árbol izquierdo
            raiz.izquierda = borrar_Recursivo(raiz.izquierda, clave);
        else if (clave > raiz.clave) //Recorrer el sub árbol derecho
            raiz.derecha = borrar_Recursivo(raiz.derecha, clave);
        else {

            // El nodo contiene solo un hijo
            if (raiz.izquierda == null)
                return raiz.derecha;
            else if (raiz.derecha == null)
                return raiz.izquierda;

            // El Nodo Tiene 2 hijos;
            //Obtener el sucesor inorder (Valor mínimo del subarbol derecho)
            raiz.clave = ValorMinimo(raiz.derecha);

            // Borrar el sucesor inorder
            raiz.derecha = borrar_Recursivo(raiz.derecha, raiz.clave);
        }
        return raiz;
    }

    int ValorMinimo(Nodo raiz) {

        // Guardo la clave del nodo actual como valor mínimo inicial
        int minVal = raiz.clave;

        // Mientras exista un hijo izquierdo
        while (raiz.izquierda != null) {
            // Actualiza el valor mínimo al valor del hijo izquierdo
            minVal = raiz.izquierda.clave;

            // Avanza al hijo izquierdo
            raiz = raiz.izquierda;
        }

        // Cuando ya no haya más hijos izquierdos, devuelvo el valor mínimo encontrado
        return minVal;
    }

        // Insertar Nodo en ABB
    void insertar(int clave) {
        raiz = insertar_Recursivo(raiz, clave);
    }

    //Funcion recursiva de inserción
    Nodo insertar_Recursivo(Nodo raiz, int clave) {
        // Si el árbol está vacío, crear un nuevo nodo
        if (raiz == null) {
            raiz = new Nodo(clave);
            return raiz;
        }

        // Si la clave es menor, ir a la izquierda
        if (clave < raiz.clave)
            raiz.izquierda = insertar_Recursivo(raiz.izquierda, clave);

        // Si la clave es mayor, ir a la derecha
        else if (clave > raiz.clave)
            raiz.derecha = insertar_Recursivo(raiz.derecha, clave);

        // Devolver el nodo (sin cambios)
        return raiz;
    }


    // Método de recorrido inroder para ABB
    void inorder() {
        inorder_Recursive(raiz);
    }

    // recorrido inorder recursivo ABB
    void inorder_Recursive(Nodo raiz) {

        if (raiz != null) {
            inorder_Recursive(raiz.izquierda);
            System.out.print(raiz.clave + " ");
            inorder_Recursive(raiz.derecha);
        }
    }

    boolean buscar(int clave) {
        raiz = buscar_Recursivo(raiz, clave);

        if (raiz != null)
            return true;
        else
            return false;
    }

    //busqueda recursiva
    Nodo buscar_Recursivo(Nodo raiz, int clave) {

        // Caso Base raiz es null o la clave esta presente como raiz
        if (raiz == null || raiz.clave == clave)
            return raiz;

        // val is greater than raiz's clave
        if (raiz.clave > clave)
            return buscar_Recursivo(raiz.izquierda, clave);

        // val is less than raiz's clave
        return buscar_Recursivo(raiz.derecha, clave);
    }

    public static void main(String[] args) {

        //Crear el objeto ABB
        ABB ABB = new ABB();
        /* ABB Ejemplo
                   45
                /      \
              10          90
            /    \      /
          7      12   50          */

        //Agregar datos al ABB
        ABB.insertar(45);
        ABB.insertar(10);
        ABB.insertar(7);
        ABB.insertar(12);
        ABB.insertar(90);
        ABB.insertar(50);

        //Mostrar el arbol ABB
        System.out.println("El árbol ABB ha sido creado (izquierda-raiz-derecha):");
        ABB.inorder();

        //Borrar nodo hoja
        System.out.println("\n\nEl árbol ABB después de borrar 12(Nodo hoja):");
        ABB.borrarClave(12);
        ABB.inorder();

        //Borrar el nodo con un solo hijo
        System.out.println("\n\nEl árbol The ABB después de borrar 90 (Nodo con 1 hijo):");
        ABB.borrarClave(90);
        ABB.inorder();

        //Borrar nodo con 2 hijos
        System.out.println("\n\nEl árbol ABB después de borrar 45 (Nodo con 2 hijos):");
        ABB.borrarClave(45);
        ABB.inorder();

        //Buscar clave en el ABB
        boolean ret_val = ABB.buscar(50);
        System.out.println("\n\nClave 50 en el ABB:" + ret_val);

        ret_val = ABB.buscar(12);
        System.out.println("\nClave 12 en el ABB:" + ret_val);
    }
}