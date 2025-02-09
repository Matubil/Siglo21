public class Main {
    public static void main(String[] args) {
        Edificio edificio = new Edificio();

        // Obtener la cantidad de oficinas activas
        int activas = edificio.cantidadOficinasActivas();
        System.out.println("Cantidad de oficinas activas: " + activas);

        // Encontrar la primera oficina activa
        Oficina primeraActiva = edificio.buscarPrimeraOficinaActiva();
        if (primeraActiva != null) {
            System.out.println("Primera oficina activa: " + primeraActiva);
        } else {
            System.out.println("No se encontraron oficinas activas.");
        }
    }
}
