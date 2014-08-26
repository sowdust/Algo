package vinci.esercizio12;

/**
 * Abstract Data Type Union Find implemented as an array. The array represents
 * the forest of trees. Given n, elements are the members of the set [0, n-1].
 * Each node is represented by the index of the array.
 *
 * II: The value of each member in the array represents 1) the parent node, if
 * node is not root; 2) the opposite of the size of the tree if the node is
 * root. => if value is negative, it is a root; otherwise a "child"
 *
 */
public class UnionFind {

    /**
     * forest
     */
    private int[] a;

    /**
     * Creates the data structure. INIT: all members are set to -1 (all
     * singletons : one-size sets)
     *
     * @param n capacity of newly created Union Find
     */
    public UnionFind(int n) {
        a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = -1;
        }
    }

    /**
     * @return capacity of Union Find
     */
    public int getCapacity() {
        return a.length;
    }

    /**
     * Sets a new capacity. If new capacity is lower than older, throws
     * exception.
     *
     * @param n new capacity
     * @throws IllegalArgumentException
     */
    public void setCapacity(int n) throws IllegalArgumentException {
        if (n < a.length) {
            throw new IllegalArgumentException();
        }
        int[] b = new int[n];
        int i;
        // copies old array in new
        for (i = 0; i < a.length; ++i) {
            b[i] = a[i];
        }
        // sets new elements to singletons (-1)
        for (; i < n; ++i) {
            b[i] = -1;
        }
        //  replace pointers to arrays
        a = b;
    }

    /**
     * Returns the "guard" of the set to which element e belongs. If e is not a
     * valid element (ie: in [0..capacity]) throws an exception.
     *
     * @param e Element for which
     * @return guard for element e
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("empty-statement")
    public int find(int e) throws IllegalArgumentException {
        if (e < 0 || e >= a.length) {
            throw new IllegalArgumentException();
        }
        int sizeORparent = a[e];
        while (sizeORparent >= 0) {
            e = sizeORparent;
            sizeORparent = a[e];
        }
        return e;
    }

    /**
     * Unites set containing a and b.
     *
     * @param a
     * @param b
     * @return true if a and b belonging to different sets. false otherwise.
     */
    public boolean union(int a, int b) {
        int ea = find(a);
        int eb = find(b);
        // if they belong to same set
        if (ea == eb) {
            return false;
        }
        // update size of union
        this.a[ea] += this.a[eb];
        // update ref to root of "united" member
        this.a[eb] = ea;
        return true;
    }

}
