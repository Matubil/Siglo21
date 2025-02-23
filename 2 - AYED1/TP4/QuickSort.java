public class QuickSort {
    // Nombre: Matias Biloni
    // Legajo: LN VINF017151
    // DNI: 38605444

    /* Método para particionar el array y devolver el índice del pivote */
    int particion(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // índice del elemento mas chico

        for (int j = low; j < high; j++) {

            // Si el elemento actual es mas chico o igual que el pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Intercambia arr[i+1] y arr[high] (o pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /* Método principal que implementa quicksort
        arr[] --> Array a ser ordenado,
        low --> Comienzo de indice,
        high --> Fin de indice */
    void ordenar(int arr[], int low, int high) {
        if (low < high) {
            int pi = particion(arr, low, high);

            // Ordenar los elementos antes y después del pivote
            ordenar(arr, low, pi - 1);
            ordenar(arr, pi + 1, high);
        }
    }
}
