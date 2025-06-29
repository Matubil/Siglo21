 import java.util.*;

//Clase que contruye el arbol de huffman y codifica el texto
 public class CompresorHuffman {
    private Map<Character, String> codigos = new HashMap<>();
    private NodoHuffman raiz;

    //Contruye el árbol de Huffman a partir del texto dado
    public void construirArbol(String texto) {
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        //Crea lista de nodos a partir de las frecuencias
        List<NodoHuffman> nodos = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            nodos.add(new NodoHuffman(entry.getKey(), entry.getValue()));
        }

        //Construye el árbol combinando los dos nodos con menor frecuencia hasta quedar uno solo
        while (nodos.size() > 1) {

            nodos.sort(Comparator.comparingInt(n -> n.frecuencia)); //Ordena los nodos por frecuencia

            NodoHuffman n1 = nodos.remove(0);
            NodoHuffman n2 = nodos.remove(0);

            NodoHuffman padre = new NodoHuffman('\0', n1.frecuencia + n2.frecuencia); //Nodo interno
            padre.izquierda = n1;
            padre.derecha = n2;

            nodos.add(padre);
        }


        raiz = nodos.get(0); //Raíz del árbol
        generarCodigos(raiz, "");
    }

    //Genera los códigos de Huffman de forma recursiva
    private void generarCodigos(NodoHuffman nodo, String codigo) {
        if (nodo == null) return;

        if (nodo.esHoja()) {
            codigos.put(nodo.caracter, codigo);
        }

        generarCodigos(nodo.izquierda, codigo + "0");
        generarCodigos(nodo.derecha, codigo + "1");
    }

    //Codifica el texto original utilizando los códigos generados
    public String codificar(String texto) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            sb.append(codigos.get(c));
        }
        return sb.toString();
    }

    //Muestra por consola los códigos Huffman generados
    public void mostrarCodigos() {
        System.out.println("Códigos de Huffman:");
        for (Map.Entry<Character, String> entry : codigos.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
    }
}
