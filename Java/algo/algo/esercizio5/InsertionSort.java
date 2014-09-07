package algo.esercizio5;

public class InsertionSort implements SortingAlgorithm {

    /**
     *
     * INVARIANTE P(i): a[0..i-1] è ordinato
     *
     * PASSO: inserisci a[i] all'interno di a[0..i-1] mantenendo l'ordinamento
     *
     * CONTROLLO: da i=1 a i = n-1
     *
     * PROPRIETA': In place; stabile; T(n) ∈ O(n^2); S(n) ∈ Θ(1); Tbest(n) ∈
     * Θ(n^2)
     *
     * @param array
     */
    @Override
    public void sort(int[] array) {
        sortWhile(array);
    }

    /**
     * Versione che utilizza due for annidati e il costrutto "break" per
     * fermarsi al posto giusto nello scorrimento a sx degli elementi
     *
     * @param array
     */
    public void sortFor(int[] array) {
        int x, i, j;
        for (i = 1; i < array.length; ++i) {
            x = array[i];
            for (j = i; j > 0; --j) {
                if (x >= array[j - 1]) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = x;
        }
    }

    /**
     * versione che utilizza un ciclo while all'interno del for
     *
     * @param array
     */
    public void sortWhile(int[] array) {
        int x, i, j;
        for (i = 1; i < array.length; ++i) {
            x = array[i];
            j = i;
            while (j > 0 && x < array[j - 1]) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = x;
        }
    }

}
