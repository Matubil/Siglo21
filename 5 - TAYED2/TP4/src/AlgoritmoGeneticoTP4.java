public class AlgoritmoGeneticoTP4 {

    public static final int LONGITUD = 20;
    public static final int OBJETIVO = 40;

    public static void main(String[] args) {
        Consola salidaConsola = new Consola();
        Generador generador = new Generador();
        Evaluador evaluador = new Evaluador();
        Cruzador cruzador = new Cruzador();

        int[] cadenaPrincipal = generador.generarCadena();
        int pesoActual = evaluador.evaluar(cadenaPrincipal);

        salidaConsola.titulo();
        salidaConsola.mostrarCadena("Cadena inicial:", cadenaPrincipal, pesoActual);

        int iteraciones = 0;

        while (pesoActual < OBJETIVO) {
            int[] nuevaCadena = generador.generarCadena();
            int[] cruzada = cruzador.cruzar(cadenaPrincipal, nuevaCadena);
            int pesoCruzada = evaluador.evaluar(cruzada);

            if (pesoCruzada > pesoActual) {
                cadenaPrincipal = cruzada;
                pesoActual = pesoCruzada;
                iteraciones++;
                salidaConsola.mostrarCadena("Cadena aceptada:", cadenaPrincipal, pesoActual);
            }
        }

        salidaConsola.finalizar(iteraciones, cadenaPrincipal);
    }
}




