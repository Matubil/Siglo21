import java.util.Random;

//Clase que genera cadenas aleatorias
class Generador {
    private Random rnd;

    public Generador() {
        this.rnd = new Random();
    }

    public int[] generarCadena() {
        int[] cadena = new int[AlgoritmoGeneticoTP4.LONGITUD];
        for (int i = 0; i < cadena.length; i++) {
            cadena[i] = rnd.nextInt(2); // 0 o 1
        }
        return cadena;
    }
}