public class Edificio {

    private int numOficinas; // Total de oficinas por piso
    private double superficie;

    // Método simulado para obtener información del sensor
    public boolean obtenerSensor(int piso, int oficina) {
        // Supongamos que esta función consulta los sensores reales.
        // Aquí puedes implementar una simulación o enlace a datos reales.
        return false;
    }

    /*
      Método para calcular la cantidad de oficinas activas en el edificio.
      @return cantidad total de oficinas activas.
     */
    public int cantidadOficinasActivas() {
        int activas = 0;

        for (int piso = 1; piso <= 10; piso++) {                // Itera del piso 1 al piso 10
            for (int oficina = 1; oficina <= 9; oficina++) {    // Itera las 9 oficinas por piso
                if (oficina == 5) continue;                     // Ignora el hall de ascensores
                if (obtenerSensor(piso, oficina)) {
                    activas++;                                  // Incrementa si la oficina está activa
                }
            }
        }

        return activas;
    }


    /*
      Método para encontrar la primera oficina activa.
      @return un objeto de tipo oficina que contiene el número de oficina y el piso.
     */
    public Oficina buscarPrimeraOficinaActiva() {
        for (int piso = 1; piso <= 10; piso++) {                // Itera del piso 1 al piso 10
            for (int oficina = 1; oficina <= 9; oficina++) {    // Itera las 9 oficinas por piso
                if (oficina == 5) continue;                     // Ignora el hall de ascensores
                if (obtenerSensor(piso, oficina)) {
                    return new Oficina(oficina, piso);          // Retorna la oficina activa
                }
            }
        }

        return null; // Si no se encuentra una oficina activa
    }
}

/**
 * Clase Oficina
 */
class Oficina {
    public int nro; // Número de oficina
    public int piso; // Piso donde se encuentra la oficina

    public Oficina(int nro, int piso) {
        this.nro = nro;
        this.piso = piso;
    }

    @Override
    public String toString() {
        return "Oficina { nro = " + nro + ", piso = " + piso + " }";
    }
}
