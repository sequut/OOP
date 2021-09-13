public class Heapsort {
    /**
     * Heap sort
     * @param aa - array to sort
     */
    public static void sort(int aa[]) {
        int n = aa.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(aa, n, i);

        for (int i = n - 1; i >= 0; i--){
            int help = aa[0];
            aa[0] = aa[i];
            aa[i] = help;

            heapify(aa, i, 0);
        }
    }

    /**
     * make heap
     * @param aa array to sort
     * @param n length of array
     * @param i parent node
     */

    static  void heapify(int aa[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && aa[left] > aa[largest])
            largest = left;

        if (right < n && aa[right] > aa[largest])
            largest = right;

        if (largest != i){
            int help = aa[i];
            aa[i] = aa[largest];
            aa[largest] = help;

            heapify(aa, n, largest);
        }
    }
}
