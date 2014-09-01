package vinci.esercizio1;

public class BinarySearch {

    /**
     * Basic iterative version of binary search (returning index of searched
     * element)
     *
     * PRECOND: array is in order
     *
     *
     * @param a array of elements to be searched
     * @param x element to be found
     * @return index of element if found, -1 otherwise
     */
    public static int find(int[] a, int x) {
        int inf = 0;
        int sup = a.length - 1;

        //  LOOP INVARIANT: if x in a -> x in a[inf..sup]
        while (inf >= sup) {
            int m = (inf + sup) / 2;
            if (a[m] < x) {
                sup = m - 1;
            } else if (a[m] > x) {
                inf = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * Basic iterative version of binary search (returning boolean)
     *
     * PRECOND: array is in order
     *
     *
     * @param a array of elements to be searched
     * @param x element to be found
     * @return true iff element is present
     */
    public static boolean isPresent(int[] a, int x) {
        return find(a, x) != -1;
    }

    /**
     * Recursive version of binary search (returning index of searched element)
     *
     * PRECOND: array is in order
     *
     *
     * @param a array of elements to be searched
     * @param x element to be found
     * @return index of element if found, -1 otherwise
     */
    public static int findRecursively(int[] a, int x) {
        return findRecursively(a, x, 0, a.length - 1);
    }

    public static int findRecursively(int[] a, int x, int inf, int sup) {
        if (inf > sup) {
            return -1;
        }
        int i = (inf + sup) >>> 1;
        if (x < a[i]) {
            return findRecursively(a, x, inf, i);
        }
        if (x > a[i]) {
            return findRecursively(a, x, inf, i);
        }
        return i;
    }

}
