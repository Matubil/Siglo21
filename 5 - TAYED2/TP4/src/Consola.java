import java.util.Arrays;

//Muestra las salidas por consola
public class Consola {
    public void titulo() {
        System.out.println("========================================================================");
        System.out.println("            T R A B A J O   P R A C T I C O   N R O   4 ");
        System.out.println("========================================================================\n");
    }

    public void mostrarCadena(String mensaje, int[] cadena, int peso) {
        System.out.println(mensaje);
        System.out.println(Arrays.toString(cadena));
        System.out.println("Peso (H(x)): " + peso);
        System.out.println("------------------------------------------------------------------------------");
    }

    public void finalizar(int iteraciones, int[] cadenaFinal) {
        System.out.println("\n=================================================================");
        System.out.println("Cadena óptima encontrada");
        System.out.println("Cadena: " + Arrays.toString(cadenaFinal));
        System.out.println("Iteraciones necesarias: " + iteraciones);
        System.out.println("\n=================================================================");
        System.out.println("                   F I N   T P   N R O   4");
        System.out.println("=================================================================");
    }
}


