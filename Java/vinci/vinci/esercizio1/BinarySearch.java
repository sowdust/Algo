package vinci.esercizio1;

public class BinarySearch {

    /**
     * Basic iterative version of binary search
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

}
