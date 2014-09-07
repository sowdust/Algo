package algo.esercizio13;

import algo.esercizio13.SearchTreeWithIntKeys;
import algo.esercizio13.ObjectWithIntKey;
import algo.esercizio13.Element;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mat
 */
public class SearchTreeWithIntKeysTest {

    public SearchTreeWithIntKeysTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testAllTogether() {
        System.out.println("Testing BST");
        SearchTreeWithIntKeys instance = new SearchTreeWithIntKeys();
        assertEquals(instance.size(), 0);
        assertTrue(instance.isEmpty());
        assertEquals(instance.min(), null);

        ObjectWithIntKey e = new Element(1, "test");
        ObjectWithIntKey expResult = null;
        ObjectWithIntKey result = instance.put(e);
        assertEquals(expResult, result);
        assertEquals(instance.size(), 1);
        assertTrue(!instance.isEmpty());
        assertEquals(instance.min(), e);
        assertEquals(instance.max(), e);

        expResult = e;
        result = instance.get(1);
        assertEquals(expResult, result);

        ObjectWithIntKey b = new Element(1, "test2");
        expResult = e;
        result = instance.put(b);
        assertEquals(expResult, result);
        assertEquals(instance.size(), 1);

        expResult = b;
        result = instance.get(1);
        assertEquals(expResult, result);

        ObjectWithIntKey c = new Element(2, "test3");
        expResult = null;
        result = instance.put(c);
        assertEquals(expResult, result);
        assertEquals(instance.size(), 2);
        assertEquals(instance.min(), b);
        assertEquals(instance.max(), c);

        expResult = c;
        result = instance.get(2);
        assertEquals(expResult, result);

        expResult = c;
        result = instance.remove(2);
        assertEquals(expResult, result);
        assertEquals(instance.size(), 1);

        expResult = null;
        result = instance.remove(2);
        assertEquals(expResult, result);
        assertEquals(instance.size(), 1);
        assertEquals(instance.min(), b);

        instance.remove(1);
        assertEquals(instance.size(), 0);
        assertTrue(instance.isEmpty());

    }

}
