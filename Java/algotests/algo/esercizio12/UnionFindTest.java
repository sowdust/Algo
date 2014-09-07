package algo.esercizio12;

import algo.esercizio12.UnionFind;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mat
 */
public class UnionFindTest {

    public UnionFindTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        UnionFind instance = new UnionFind(5);
        int expResult = 5;
        int result = instance.getCapacity();
        assertEquals(expResult, result);

    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testSetCapacity() {
        System.out.println("setCapacity");
        UnionFind instance = new UnionFind(5);
        try {
            instance.setCapacity(4);
        } catch (IllegalArgumentException e) {
            ;
        }
        int expResult = 5;
        int result = instance.getCapacity();
        assertEquals(expResult, result);
        instance.setCapacity(6);
        expResult = 6;
        result = instance.getCapacity();
        assertEquals(expResult, result);

    }

    @Test
    public void testFind() {
        System.out.println("find & union");
        int e = 0;
        UnionFind instance = new UnionFind(5);
        int expResult = 4;
        int result = instance.find(4);
        assertEquals(expResult, result);
        assertTrue(instance.union(0, 1));
        assertEquals(instance.find(1), 0);
        assertTrue(!instance.union(0, 1));
        assertTrue(instance.union(1, 4));
        assertEquals(instance.find(4), 0);

    }

    @Test
    public void testPrint() {
        System.out.println("Print");
        int e = 0;
        UnionFind uf = new UnionFind(5);
        uf.union(1, 2);
        System.out.println("union 1 2");
        System.out.println(uf);

        uf.union(1, 2);
        System.out.println("union 1 2");
        System.out.println(uf);

        uf.union(3, 4);
        System.out.println("union 3 4");
        System.out.println(uf);

        uf.union(1, 0);
        System.out.println("union 1 0");
        System.out.println(uf);

        uf.union(1, 3);
        System.out.println("union 1 3");
        System.out.println(uf);

        uf.find(4);
        System.out.println("find 4");
        System.out.println(uf);

    }

}
