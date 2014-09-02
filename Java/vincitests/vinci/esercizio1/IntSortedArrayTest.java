package vinci.esercizio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntSortedArrayTest {

    @Test
    public void testConstructor() {
        System.out.println("constructor");
        IntSortedArray instance = null;
        try {
            instance = new IntSortedArray(0);
        } catch (Exception e) {
            System.out.println("eccezione lanciata correttamente");
        }
        assertTrue(instance == null);
        instance = new IntSortedArray(1);
        assertEquals(1, instance.getCapacity());
    }

    @Test
    public void testSize() {
        System.out.println("size");
        IntSortedArray instance = new IntSortedArray(5);
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        assertEquals(5, instance.getCapacity());

    }

    @Test
    public void testBinarySearch() {
        System.out.println("binarySearch");
        int x = 0;
        IntSortedArray instance = new IntSortedArray(1);
        int expResult = -1;
        int result = instance.binarySearch(x);
        assertEquals(expResult, result);
    }

    @Test
    public void testRest() {
        System.out.println("test insert, get & print");
        int x;
        int result;
        int expResult;
        IntSortedArray instance = new IntSortedArray(1);

        expResult = 0;
        x = 5;
        result = instance.insert(x);
        instance.print();
        assertEquals(1, instance.getCapacity());
        assertEquals(expResult, result);

        expResult = -1;
        x = 5;
        result = instance.insert(x);
        instance.print();
        assertEquals(1, instance.getCapacity());
        assertEquals(expResult, result);

        expResult = 0;
        x = 2;
        result = instance.insert(x);
        instance.print();
        assertEquals(2, instance.getCapacity());
        assertEquals(2, instance.size());
        assertEquals(expResult, result);

        expResult = 2;
        x = 8;
        result = instance.insert(x);
        instance.print();
        assertEquals(4, instance.getCapacity());
        assertEquals(3, instance.size());
        assertEquals(expResult, result);

        expResult = 0;
        x = -5;
        result = instance.insert(x);
        instance.print();
        assertEquals(4, instance.getCapacity());
        assertEquals(4, instance.size());
        assertEquals(expResult, result);

        expResult = 4;
        x = 1000000;
        result = instance.insert(x);
        instance.print();
        assertEquals(8, instance.getCapacity());
        assertEquals(5, instance.size());
        assertEquals(expResult, result);
    }

}
