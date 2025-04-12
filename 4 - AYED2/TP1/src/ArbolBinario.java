//Nombre: 
//Legajo: INFXXXXX
//DNI: XXXXXXXX

import java.io.*;

// Definición de la clase NodoBinario
class NodoBinario {
    int dato;
    NodoBinario Hizq, Hder;

    // Constructor
    NodoBinario(int Elem) {
        dato = Elem;
        Hizq = null;
        Hder = null;
    }
}

// Definición de la clase Arbol
class Arbol {
    public NodoBinario Raiz;

    // Constructor
    public Arbol() {
        Raiz = null;
    }

    //Se codificaron 2 métodos y en caso de que el nodo padre de referencia sea nulo se asume que es la raíz

    // Inserción de un hijo izquierdo
    public NodoBinario InsertaNodoHIzq(NodoBinario Nodo, int Elem) {

        NodoBinario result = null;

        if (Nodo == null) {
            NodoBinario NodoAux = new NodoBinario(Elem);
            result = NodoAux;
            Raiz = NodoAux;
        }
        else
        {
            if (Nodo.Hizq == null) {
                NodoBinario NodoAux = new NodoBinario(Elem);
                Nodo.Hizq = NodoAux;
                result = NodoAux;
            } else {
                System.out.println("ERR - Hijo izquierdo de " + Nodo.dato + " no es nulo");
            }
        }
        return result;
    }

    // Inserción de un hijo derecho
    public NodoBinario InsertaNodoHDer(NodoBinario Nodo, int Elem) {

        NodoBinario result = null;
        if (Nodo == null) {
            NodoBinario NodoAux = new NodoBinario(Elem);
            result = NodoAux;
            Raiz = NodoAux;
        }
        else
        {
            if (Nodo.Hder == null) {
                NodoBinario NodoAux = new NodoBinario(Elem);
                Nodo.Hder = NodoAux;
                result = NodoAux;
            }
            else {
                System.out.println("ERR - Hijo derecho de " + Nodo.dato + " no es nulo");
            }
        }
        return result;
    }

    //Inorden Recursivo del arbol recorrido (izquierda, raíz, derecha)
    public void Inorden(NodoBinario Nodo) {
        if (Nodo != null) {
            Inorden(Nodo.Hizq);
            System.out.print(Nodo.dato + " ");
            Inorden(Nodo.Hder);
        }
    }

    //Altura del arbol
    public int Altura(NodoBinario Nodo) {
        if (Nodo == null)
            return 0;
        
        int altIzq = Altura(Nodo.Hizq);
        int altDer = Altura(Nodo.Hder);
        return 1 + Math.max(altIzq, altDer);
    }
}

// Clase principal
public class ArbolBinario {
    public static void main(String[] ar) {
        Arbol A = new Arbol();
        System.out.print("Agregando la raíz 30\n");

        NodoBinario NodoAux = null, NodoAux2 = null, NodoAux3 = null, NodoAux4 = null;

        NodoAux2 = A.InsertaNodoHIzq(NodoAux, 30);  // inserta la raíz
        NodoAux = NodoAux2;
        NodoAux2 = A.InsertaNodoHIzq(NodoAux, 25);  // hijo izq de 30
        NodoAux3 = A.InsertaNodoHDer(NodoAux, 45);  // hijo der de 30
        NodoAux = NodoAux2;
        NodoAux2 = A.InsertaNodoHIzq(NodoAux, 20);  // hijo izq de 25
        NodoAux4 = A.InsertaNodoHDer(NodoAux, 27);  // hijo der de 25

        System.out.print("\nEl árbol binario en inorden es:\n");
        A.Inorden(A.Raiz);

        int altura = A.Altura(A.Raiz);
        System.out.print("\n\nLa altura del árbol es: " + altura + "\n");
    }
}
