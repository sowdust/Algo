package vinci.esercizio8;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mat
 */
public class PriorityQueueTest {

    public PriorityQueueTest() {
    }

    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PriorityQueue instance = new HeapPriorityQueue();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        instance.insert("ciao", 0);
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

    }

    @Test
    public void testFirst() {
        System.out.println("first");
        PriorityQueue instance = new HeapPriorityQueue();
        String expResult = null;
        String result = instance.first();
        assertEquals(expResult, result);
        instance.insert("primo", 10);
        expResult = "primo";
        result = instance.first();
        assertEquals(expResult, result);
        instance.insert("secondo", 15);
        expResult = "primo";
        result = instance.first();
        assertEquals(expResult, result);
        instance.insert("primissimo", 1);
        expResult = "primissimo";
        result = instance.first();
        assertEquals(expResult, result);
    }

    @Test
    public void testPop() {
        System.out.println("pop");
        PriorityQueue instance = new HeapPriorityQueue();
        String expResult = null;
        String result = instance.pop();
        assertEquals(expResult, result);
        instance.insert("primo", 0);
        instance.insert("secondo", 1);
        instance.insert("terzo", 2);
        instance.insert("quart", 3);
        System.out.println("inseriti tre elementi");
        expResult = "primo";
        System.out.println("poppiamo il primo:" + instance.first());
        result = instance.pop();
        assertEquals(expResult, result);
        assertTrue(!instance.isEmpty());
        System.out.println("poppiamo il secondo:" + instance.first());
        expResult = "secondo";
        result = instance.pop();
        assertEquals(expResult, result);
        assertTrue(!instance.isEmpty());
        expResult = "terzo";
        System.out.println("poppiamo il terzo: " + instance.first());
        result = instance.pop();
        assertEquals(expResult, result);
        assertTrue(!instance.isEmpty());
        expResult = "quart";
        System.out.println("poppiamo il quarto: " + instance.first());
        result = instance.pop();
        assertEquals(expResult, result);
        assertTrue(instance.isEmpty());

    }

    @Test
    public void testInsert() {
        System.out.println("insert testato insieme a metodi di sopra");
        PriorityQueue instance = new HeapPriorityQueue();
        instance.insert("primo", 0);
        instance.insert("prim", 0.1);
        instance.insert("pri", 0.2);
        instance.insert("rimo", 0.3);
        instance.insert("pimo", 0.4);
        instance.insert("prmo", 0.5);
        instance.insert("praimo", 0.6);
        instance.insert("priaamo", 0.7);
        instance.insert("primsso", 0.8);
        instance.insert("primooo", 0.9);
        instance.insert("primaso", 1);
        assertEquals(instance.pop(), "primo");
        assertEquals(instance.pop(), "prim");

    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        String element = "";
        PriorityQueue instance = new HeapPriorityQueue();
        instance.insert("primo", 0);
        instance.insert("secondo", 1);
        instance.insert("terzo", 2);
        instance.insert("quart", 3);
        boolean expResult = false;
        boolean result = instance.remove("inesistente");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.remove("secondo");
        assertEquals(expResult, result);
        assertEquals(instance.first(), "primo");
        result = instance.remove("secondo");
        assertEquals(false, result);
        assertEquals(instance.first(), "primo");
        result = instance.remove("primo");
        assertEquals(true, result);
        assertEquals(instance.first(), "terzo");

        result = instance.remove("quart");
        assertEquals(true, result);
        assertEquals(instance.first(), "terzo");
        result = instance.remove("terzo");
        assertEquals(true, result);
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testDecreasePriority() {
        System.out.println("decreasePriority");
        String element = "";
        double newPriority = 0.0;
        PriorityQueue instance = new HeapPriorityQueue();
        instance.insert("primo", 0.20);
        instance.insert("secondo", 1);
        instance.insert("terzo", 2);
        instance.insert("quart", 3);
        boolean result = instance.decreasePriority("non", 0);
        boolean expResult = false;
        assertEquals(expResult, result);
        result = instance.decreasePriority("terzo", 0.18);
        assertTrue(result);
        assertEquals(instance.first(), "terzo");
        result = instance.decreasePriority("quart", 0.15);
        assertTrue(result);
        assertEquals(instance.first(), "quart");
        result = instance.decreasePriority("primo", 0.1);
        assertTrue(result);
        assertEquals(instance.first(), "primo");
        result = instance.decreasePriority("terzo", 0.03);
        assertTrue(result);
        assertEquals(instance.first(), "terzo");

    }

}
