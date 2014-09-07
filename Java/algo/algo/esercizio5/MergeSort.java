package algo.esercizio5;

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
        // usando la msort a passo alternato è necessario copiare gli array
        for (int i = 0; i < aux.length; ++i) {
            aux[i] = array[i];
        }
        msortAPassoAlternato(array, 0, array.length - 1, aux);
    }

    /**
     * Mergesort versione classica che fa uso di array ausiliario
     *
     * @param a
     * @param first
     * @param last
     * @param aux
     */
    void msort(int[] a, int first, int last, int[] aux) {
        if (first < last) {
            int m = (first + last) / 2;
            msort(a, first, m, aux);
            msort(a, m + 1, last, aux);
            mergeEcologico(a, first, m, last, aux);
        }
    }

    /**
     *
     * MergeSort a Passo alternato che evita la copiatura ad ogni chiamata
     * dell'intero array ausiliare
     *
     * PRECONDIZIONE: a[first..last] = aux[first..last]
     *
     * POSTCONDIZIONE: a[first..last] è il risultato dell'ordinamento di
     * a[first..last] e aux[first..last]
     *
     * aux[first..last] può essere modificata ma tutto ciè che sta fuori
     * dall'intervallo [first..last] rimane invariato
     *
     * @param a
     * @param first
     * @param last
     * @param aux
     */
    void msortAPassoAlternato(int[] a, int first, int last, int[] aux) {
        // CASO BASE
        if (first >= last) {
            return;
        }
        int m = (first + last) / 2;
        // PASSO: metto in aux l'ordinamento di a e aux
        msortAPassoAlternato(aux, first, m, a);
        msortAPassoAlternato(aux, m + 1, last, a);
        // fondo le due metà di aux in a:
        mergePassoAlternato(aux, first, m, last, a);
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
    void mergeEcologico(int[] a, int first, int m, int last, int[] aux) {
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

    /**
     * Merge a passo alternato
     *
     * fonde i due segmenti ordinati a[first..m] e a[m+1..last] nel segmento
     * aux[first .. last]
     */
    void mergePassoAlternato(int[] a, int first, int m, int last, int[] aux) {
        int i = first, j = m + 1, k = first;
        // PASSO come al solito
        while (i <= m && j <= last) {
            if (a[i] <= a[j]) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        //  copia della parte rimanente della metà di array non esaminata
        while (i <= m) {
            aux[k++] = a[i++];
        }
        while (j <= last) {
            aux[k++] = a[j++];
        }
        // FINE: manca la copiatura finale da aux ad a
    }

}
