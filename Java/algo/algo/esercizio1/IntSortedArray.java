package algo.esercizio1;

/**
 * Sorted Arrays
 */
public class IntSortedArray {

    private int[] elements;
    private int numElements;

    /**
     * Creates a new instance setting the initial capacity
     *
     * @param n initial array capacity
     */
    public IntSortedArray(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        numElements = 0;
        elements = new int[n];
    }

    /**
     * @return # elements in array
     */
    public int size() {
        return numElements;
    }

    /**
     * Performs binary search over the array content
     *
     * @param x element to be searched for
     * @return index of element if found; negative index of position in which it
     * should be inserted otherwise - 1
     */
    public int binarySearch(int x) {

        if (numElements == 0 || x < elements[0]) {
            return -1;
        }
        if (x > elements[numElements - 1]) {
            return -(numElements + 1);
        }
        int inf = 0;
        int sup = numElements - 1;
        //  LOOP INVARIANT: if x in array -> x in array[inf..sup]
        while (inf <= sup) {
            //  avoids int overflow. Actually not useful since heap space is less than Integer.MAX_INT / 2
            int m = (inf + sup) >>> 1;
            if (elements[m] < x) {
                sup = m - 1;
            } else if (elements[m] > x) {
                inf = m + 1;
            } else {
                return m;
            }
        }
        return -(inf + 1);
    }

    /**
     * Adds a new element to the array if it is not present and mantaining the
     * order
     *
     * @param x new element to be added
     * @return index of new element if added; -1 otherwise
     */
    public int insert(int x) {
        int i = binarySearch(x);
        int j;
        if (i >= 0) {
            return -1;
        } else {
            if (numElements == elements.length) {
                reallocate();
            }
            numElements++;
            for (j = numElements - 1; j > (-i - 1); j--) {
                elements[j] = elements[j - 1];
            }
            elements[(-i - 1)] = x;

            return (-i - 1);
        }
    }

    /**
     * Returns element at given index
     *
     * @param i index to lookup
     * @return element of index i
     */
    public int get(int i) {
        if (i >= numElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[i];
    }

    /**
     * Prints the ordered array together with its indices
     */
    public void print() {
        System.out.println("Array with #" + numElements);
        for (int i = 0; i < numElements; ++i) {
            System.out.print("[" + i + " ] " + elements[i] + "; ");
            System.out.println();
        }
    }

    /**
     * Internal function that duplicates the array space when full There is a
     * limit of java heap space based on implementation
     *
     * @throws java.lang.OutOfMemoryError
     */
    private void reallocate() {
        int[] t = new int[numElements * 2];
        for (int i = 0; i < elements.length; ++i) {
            t[i] = elements[i];
        }
        elements = t;
    }

    /**
     * @return Current array capacity (not its size, nor its limit!)
     */
    public int getCapacity() {
        return elements.length;
    }

}
