
//Nodo del árbol de Huffman
public class NodoHuffman {
    char caracter;  //Caracter que representa el nodo
    int frecuencia; //Frecuencia del caracter
    NodoHuffman izquierda, derecha; //Hijos izquierdo y derecho del nodo

    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = null;
        this.derecha = null;
    }

    //Metodo para verificar si el nodo es una hoja
    public boolean esHoja() {
        return (izquierda == null && derecha == null);
    }
}
