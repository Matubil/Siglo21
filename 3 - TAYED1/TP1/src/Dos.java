
//Clase de la Consigna 3


public class Dos {

    // Método para contar la frecuencia de letras en una palabra
    public int[] contarFrecuencia(String palabra) {

        int[] conteos = new int[27]; // Cambiamos a 27 para poder incluir la "Ñ"
        palabra = palabra.toUpperCase(); // Convertir todo a mayúsculas para contar sin importar el caso

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            int index;

            if (letra == 'Ñ') {
                index = 26; // Usamos el índice 26 para la "Ñ"
            } else if (letra >= 'A' && letra <= 'Z') {
                index = letra - 'A'; // Calculamos el índice para las letras A-Z
            } else {
                System.out.println("El carácter: '" + letra + "' no es una letra.");
                continue; // Si no es una letra válida, la ignoramos
            }

            conteos[index]++;
        }

        System.out.println(); // Espaciado para mejor comprensión en la consola

        return conteos;
    }

    // Método para imprimir la frecuencia de letras
    public void imprimirFrecuencia(int[] conteos) {

        for (int i = 0; i < conteos.length; i++) {
            if (conteos[i] != 0) {
                if (i < 26) {
                    System.out.println((char)(i + 'A') + ": " + conteos[i]);
                } else {
                    System.out.println("Ñ: " + conteos[i]); // Imprimimos la "Ñ"
                }
            }
        }
    }
}
