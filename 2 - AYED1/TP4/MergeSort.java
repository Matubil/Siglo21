public class MergeSort {
    // Nombre: Matias Biloni
    // Legajo: LN VINF017151
    // DNI: 38605444


    // Mezcla 2 subarrays arr[].
    // El primer subarray es arr[l..m]
    // El segundo seb subarray esarr[m+1..r]
    void mezclar(int arr[], int l, int m, int r) {

        // Obtiene el tamaño de los 2 subarrays a ser mezclados
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Crea arrays temporales */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copia los datos a los arrays temporales*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Mezcla los Arrays temporales */
        // ïndices iniciales del primer y segundo subarray
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copiar los elementos pendientes de L[] si hay */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copiar los elementos pendientes de R[] si hay */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void ordenar(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            // Ordenar la primera y segunda mitad
            ordenar(arr, l, m);
            ordenar(arr, m + 1, r);

            // Mezclar las mitades ordenadas
            mezclar(arr, l, m, r);
        }
    }
}
