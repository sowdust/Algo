package vinci.esercizio5;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Classe che testa tutti gli algoritmi di ordinamento
 *
 */
public class SortingAlgorithmTest {

    public SortingAlgorithmTest() {
    }

    private int[] arrayOrd;
    private int[] arrayDOrd;
    private int[] arrayOne;
    private int[] arrayEmpty;
    private int[] arrayReg;

    @Before
    public void setUp() {
        arrayOne = new int[1];
        arrayOne[0] = 1;

        arrayEmpty = new int[0];

        arrayReg = new int[7];
        arrayReg[0] = 5;
        arrayReg[1] = 3;
        arrayReg[2] = 8;
        arrayReg[3] = 10;
        arrayReg[4] = 0;
        arrayReg[5] = -1;
        arrayReg[6] = 4;

        arrayOrd = new int[5];
        arrayOrd[0] = 0;
        arrayOrd[1] = 1;
        arrayOrd[2] = 2;
        arrayOrd[3] = 3;
        arrayOrd[4] = 4;

        arrayDOrd = new int[5];
        arrayDOrd[0] = 4;
        arrayDOrd[1] = 3;
        arrayDOrd[2] = 2;
        arrayDOrd[3] = 1;
        arrayDOrd[4] = 0;

    }

    public boolean isOrdered(int a[]) {
        for (int i = 1; i < a.length; ++i) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    public void printArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.print("[" + i + "] " + a[i] + "; ");
        }
        System.out.println();
    }

    @Test
    public void testSSort() {
        System.out.println("Selection Sort");
        SortingAlgorithm instance = new SelectionSort();

        instance.sort(arrayOrd);
        instance.sort(arrayDOrd);
        instance.sort(arrayOne);
        instance.sort(arrayEmpty);
        instance.sort(arrayReg);
        printArray(arrayOrd);
        assertTrue(isOrdered(arrayOrd));
        printArray(arrayDOrd);
        assertTrue(isOrdered(arrayDOrd));
        printArray(arrayOne);
        assertTrue(isOrdered(arrayOne));
        printArray(arrayEmpty);
        assertTrue(isOrdered(arrayEmpty));
        printArray(arrayReg);
        assertTrue(isOrdered(arrayReg));
    }

    @Test
    public void testISort() {
        System.out.println("Insertion Sort");
        SortingAlgorithm instance = new InsertionSort();

        instance.sort(arrayOrd);
        instance.sort(arrayDOrd);
        instance.sort(arrayOne);
        instance.sort(arrayEmpty);
        instance.sort(arrayReg);
        printArray(arrayOrd);
        assertTrue(isOrdered(arrayOrd));
        printArray(arrayDOrd);
        assertTrue(isOrdered(arrayDOrd));
        printArray(arrayOne);
        assertTrue(isOrdered(arrayOne));
        printArray(arrayEmpty);
        assertTrue(isOrdered(arrayEmpty));
        printArray(arrayReg);
        assertTrue(isOrdered(arrayReg));
    }

    @Test
    public void testMSort() {
        System.out.println("Merge Sort");
        SortingAlgorithm instance = new MergeSort();

        instance.sort(arrayOrd);
        instance.sort(arrayDOrd);
        instance.sort(arrayOne);
        instance.sort(arrayEmpty);
        instance.sort(arrayReg);
        printArray(arrayOrd);
        assertTrue(isOrdered(arrayOrd));
        printArray(arrayDOrd);
        assertTrue(isOrdered(arrayDOrd));
        printArray(arrayOne);
        assertTrue(isOrdered(arrayOne));
        printArray(arrayEmpty);
        assertTrue(isOrdered(arrayEmpty));
        printArray(arrayReg);
        assertTrue(isOrdered(arrayReg));
    }

}
