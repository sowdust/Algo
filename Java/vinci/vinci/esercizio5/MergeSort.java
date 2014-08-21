package vinci.esercizio5;

public class MergeSort implements SortingAlgorithm {

    /**
     * MergeSort versione "ecologica": crea un array ausiliario che viene man
     * mano passato nelle procedure ricorsive sottostanti in modo da evitare la
     * creazione e distruzione di array ausiliari ad ogni chiamata
     *
     * @param array
     */
    @Override
    public void sort(int[] array) {
        int[] aux = new int[array.length];
        msort(array, 0, array.length - 1, aux);
    }

    void msort(int[] a, int first, int last, int[] aux) {
        if (first < last) {
            int m = (first + last) / 2;
            msort(a, first, m, aux);
            msort(a, m + 1, last, aux);
            mergeInPlace(a, first, m, last, aux);
        }

    }

    /**
     * Merge "classico"
     *
     * PRECONDIZIONE: a e b sono array di interi ordinati
     *
     * INVARIANTE P(i): c[0..i+j-1] è la fuzione di a[0..i-1] e b[0..j-1] a e b
     * sono ordinati (non vengono modificati) Ɐx ∈ a[i..m-1] U b[j..n-1] :
     * c[i+j-1] ≤ x
     *
     * PASSO: copio in c il minore tra a[i] e b [j]
     *
     * CONTROLLO: continuo finchè ENTRAMBI a e b hanno elementi da esaminare. Al
     * termine del ciclo copio in c la parte ancora da esaminare (di a O b)
     *
     *
     * @param a array ORDINATO di interi
     * @param b array ORDINATO di interi
     * @return fusione ordinata di a e b
     */
    int[] merge(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int i = 0, j = 0, k = 0;
        int[] c = new int[m + n];

        while (i < m && j < n) {
            // PASSO
            if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        //USCITA DAL CICLO
        while (i < m) {
            c[k++] = a[i++];
        }
        while (j < n) {
            c[k++] = a[j++];
        }
        return c;
    }

    /**
     * Merge ottimizzato e che fa uso dell'array ausiliario
     *
     * Se i due array sono già ordinati (il primo elemento del secondo è
     * maggiore o uguale all'ultimo del primo - ritorna immediatamente)
     *
     * @param a
     * @param first
     * @param m
     * @param last
     * @param aux
     */
    void mergeInPlace(int[] a, int first, int m, int last, int[] aux) {
        int i = first, j = m + 1, k = first;
        // se già ordinati, ritorna
        if (a[m] <= a[m + 1]) {
            return;
        }
        //  copiamo nell'array ausiliare invece che crearne di nuovi
        while (i <= m && j <= last) {
            //  PASSO come al solito
            if (a[i] <= a[j]) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        // se la parte rimasta da esaminare è nella prima metà
        // è necessario copiarla alla fine dell'array
        // se invece è nella seconda metà, è già al suo posto
        int h = m, l = last;
        // i è il limite superiore della parte esaminata della prima metà:
        // se non coincide con m significa che una parte non è esaminata
        while (h >= i) {
            a[l--] = a[h--];
        }
        // infine si ricopia in a la parte fusa nell'array ausiliario
        // (k è il limite superiore dell'array aux utilizzato)
        System.arraycopy(aux, first, a, first, k - first);
        //  Ovvero:
        //  for (int r = first; r < k; r++) {
        //      a[r] = aux[r];
        //  }

    }

}
