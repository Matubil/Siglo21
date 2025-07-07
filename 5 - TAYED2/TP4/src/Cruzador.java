public class Cruzador {

    public int[] cruzar(int[] c1, int[] c2) {
        int[] resultado = new int[AlgoritmoGeneticoTP4.LONGITUD];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = (c1[i] == c2[i]) ? 1 : 0;
        }
        return resultado;
    }
}
