public class Ordenar {
    // Nombre: Matias Biloni
    // Legajo: LN VINF017151
    // DNI: 38605444

    /* Una función que sirve para mostrar un array de tamaño n */
    static void mostrarArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Main
    public static void main(String args[]) {

        int arr[] = {12, 11, 13, 5, 6, 7};
        System.out.println("\nArray original antes de MergeSort");

        mostrarArray(arr);
        MergeSort obMS = new MergeSort();
        obMS.ordenar(arr, 0, arr.length - 1);

        System.out.println("\nArray ordenado por Merge Sort");
        mostrarArray(arr);

        int arr2[] = {12, 11, 13, 5, 6, 7};
        System.out.println("\nArray original antes de QuickSort");

        mostrarArray(arr2);
        QuickSort obQS = new QuickSort();
        obQS.ordenar(arr2, 0, arr2.length - 1);

        System.out.println("\nArray ordenado por Quick Sort");
        mostrarArray(arr2);
    }
}
