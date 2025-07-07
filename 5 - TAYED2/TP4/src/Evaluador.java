
//Clase que evalua una cadena
public class Evaluador {
    public int evaluar(int[] cadena) {
        int suma = 0;
        for (int bit : cadena) {
            suma += bit * 2;
        }
        return suma;
    }
}