import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CompresorHuffman huffman = new CompresorHuffman(); //Instancia del compresor

        System.out.println("Ingrese el mensaje a codificar:");
        String texto = sc.nextLine();

        huffman.construirArbol(texto); //Se construye el árbol
        String codificado = huffman.codificar(texto);

        System.out.println("\nMensaje original: " + texto);
        System.out.println("Mensaje codificado: " + codificado + "\n");
        huffman.mostrarCodigos(); //Muestra los códigos de cada carácter
    }
}
