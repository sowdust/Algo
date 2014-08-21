package vinci.esercizio5;

public class SelectionSort implements SortingAlgorithm {

    /**
     *
     * INVARIANTE P(i): a[0..i-1] è ordinato && Ɐx ∈ a[0..i-1], Ɐy ∈ a[i..n-1] :
     * x ≤ y
     *
     * PASSO: scambia a[i] con il minimo in a[i..n-1]
     *
     * CONTROLLO: da i=0 a i = n-2 (ultimo elemento già ordinato)
     *
     * PROPRIETA': In place; non stabile; T(n) ∈ Θ(n^2); S(n) ∈ Θ(1)
     *
     * @param array
     */
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            scambia(array, i, indiceMinimoDa(array, i));
        }
    }

    int indiceMinimoDa(int a[], int i) {
        int iMin = i;
        for (++i; i < a.length; ++i) {
            if (a[i] < a[iMin]) {
                iMin = i;
            }
        }
        return iMin;
    }

    void scambia(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
