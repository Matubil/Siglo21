public class Hash {
    // Atributos
    int dato;
    int estado; // 0 = Vacío, 1 = Eliminado, 2 = Ocupado

    // Método de dispersión (hash function)
    static int FuncionHash(int n, int m) {

        return n % m; // Devuelve la posición en la tabla aplicando módulo
    }

    // Método para buscar un elemento en la tabla hash
    static int buscaHash(Hash[] h, int m, int n) {

        int j = FuncionHash(n, m); // Calculamos la posición inicial con la función hash

        while (j < m) { // Recorremos desde la posición obtenida hasta el final

            if (h[j].estado == 0) {
                return -1; // Si encontramos un espacio vacío, el elemento no está
            }

            if (h[j].estado == 2 && h[j].dato == n) {
                return j; // Si encontramos el elemento, devolvemos su índice
            }

            j++; // Probamos la siguiente posición
        }
        return -1; // Si no se encuentra, retornamos -1
    }

    // Método para insertar un elemento en la tabla hash
    static void insertaHash(Hash[] h, int m, int n) {

        boolean insertado = false;
        int j = FuncionHash(n, m);

        while (j < m) {

            if (h[j].estado == 0 || h[j].estado == 1) {

                h[j].dato = n;
                h[j].estado = 2;
                insertado = true;

                break;
            }

            j++;
        }

        if (insertado) {
            System.out.println("Elemento insertado con éxito!");
        }
        else {
            System.out.println("Tabla llena!");
        }
    }

    // Método para eliminar un elemento de la tabla hash
    static int eliminaHash(Hash[] h, int m, int n) {

        int i = buscaHash(h, m, n);

        if (i == -1) {
            return -1; // Elemento no encontrado
        } else {
            h[i].estado = 1; // Marcamos la posición como eliminada
            System.out.println("Elemento Borrado!");
            return 1;
        }
    }
}

class Principal {
    public static void main(String[] args) {
        int i, n, elemento;

        // Tabla Definida de 15
        int m = 15;
        Hash[] h = new Hash[m];
        for (i = 0; i < m; i++) {
            h[i] = new Hash();
            h[i].estado = 0;
        }

        // Insertar elemento
        Hash.insertaHash(h, m, 15);
        Hash.insertaHash(h, m, 130);
        Hash.insertaHash(h, m, 7);
        Hash.insertaHash(h, m, 32);

        //Buscando un elemento
        elemento = 7;
        i = Hash.buscaHash(h, m, elemento);
        i = Hash.eliminaHash(h, m, 130);
    }
}
