package algo.esercizio5;

import java.util.Random;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        Random generatore = new Random();
        qsortH(array, 0, array.length - 1, generatore);
    }

    /**
     *
     * QuickSort versione "alla Hoare"
     *
     * INVARIANTE: x = [k] pivot => fst ≤ = k ≤ lst fst ≤ i; j ≤ lst; ( parte da
     * esaminare al centro)
     *
     * INIZIALIZZAZIONE: i = fst; j = lst; (tutto da esaminare)
     *
     * TERMINE CICLO: finchè i ≤ j; poi termina: parte da esaminare vuota
     *
     * PASSO: scambia due elementi solo se dopo lo scambio sono dalla parte
     * giusta; gli elementi uguali al pivot vengono sempre spostati
     *
     * PROPRIETA': In place; non stabile;
     *
     *
     */
    void qsortH(int[] a, int fst, int lst, Random generatore) {
        //  CASO BASE
        if (fst > lst) {
            return;
        }
        // INIZIALIZZAZIONE
        int i = fst, j = lst;
        // scelta del pivot casuale
        int pivot = fst + generatore.nextInt(lst - fst + 1);
        int x = a[pivot];
        do {
            // troviamo il primo elemento che deve o può stare a dx
            while (a[i] < x) {
                ++i;
            }
            // troviamo il primo elemento che deve o può stare a sx
            while (a[j] > x) {
                --j;
            }
            // a questo punto sicuramente a[i] ≥ x e a[j] ≤ x
            // ma gli indici possono già essersi incrociati
            if (i <= j) {
                scambia(a, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        qsortH(a, fst, j, generatore);
        qsortH(a, i, lst, generatore);

    }

    /**
     * quickSort versione libro di testo
     *
     * @param a
     * @param inf
     * @param sup
     * @param randomGen
     */
    void qsortL(int[] a, int inf, int sup, Random randomGen) {
        if (inf < sup) {
            int randompivot = inf + randomGen.nextInt(sup - inf);
            scambia(a, sup, randompivot);
            int x = a[sup];
            int i = inf;
            int j = sup - 1;
            while (i <= j) {
                while (i <= j && a[i] <= x) {
                    i++;
                }
                while (i <= j && a[j] >= x) {
                    j--;
                }
                if (i < j) {
                    scambia(a, i, j);
                }
            }
            scambia(a, i, sup);
            qsortL(a, inf, i - 1, randomGen);
            qsortL(a, i + 1, sup, randomGen);
        }
    }

    void scambia(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
