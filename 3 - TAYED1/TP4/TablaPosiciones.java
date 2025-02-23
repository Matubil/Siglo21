// Clase que maneja la tabla de posiciones y su ordenamiento con QuickSort
public class TablaPosiciones {
    private Equipo[] equipos;
    private int cantidad;

    public TablaPosiciones(int capacidad) {
        this.equipos = new Equipo[capacidad];
        this.cantidad = 0;
    }

    public void agregarEquipo(String nombre, int puntos) {
        if (cantidad < equipos.length) {
            equipos[cantidad++] = new Equipo(nombre, puntos);
        } else {
            System.out.println("🚫 No se pueden agregar más equipos. Máximo alcanzado.");
        }
    }

    public void ordenarEquipos() {
        quickSort(0, cantidad - 1);
    }

    private void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(inicio, fin);
            quickSort(inicio, indicePivote - 1);
            quickSort(indicePivote + 1, fin);
        }
    }

    private int particion(int inicio, int fin) {
        int pivote = equipos[fin].puntos;
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (equipos[j].puntos >= pivote) { // Orden de mayor a menor
                i++;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, fin);
        return i + 1;
    }

    private void intercambiar(int i, int j) {
        Equipo temp = equipos[i];
        equipos[i] = equipos[j];
        equipos[j] = temp;
    }

    public void mostrarTabla() {
        System.out.println("\n⚜️🌟⚜️ Tabla de Posiciones del Torneo ⚜️🌟⚜️");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + equipos[i].nombre + " 🏆 - " + equipos[i].puntos + " puntos");
        }
    }
}
