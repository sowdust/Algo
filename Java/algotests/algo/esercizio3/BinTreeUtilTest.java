package algo.esercizio3;

import algo.esercizio3.BinTree;
import algo.esercizio3.BinTreeUtil;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mat
 */
public class BinTreeUtilTest {

    public BinTreeUtilTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        int elem = 0;
        BinTree t = null;
        String path = "QUALSIASI";
        int expResult = elem;
        BinTree result = BinTreeUtil.add(elem, t, path);
        assertEquals(expResult, result.element);
    }

    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        BinTree t = new BinTree(0);
        BinTree u = new BinTree(0, t, null);
        boolean expResult = false;
        boolean result = BinTreeUtil.isLeaf(u);
        assertEquals(expResult, result);
        expResult = true;
        result = BinTreeUtil.isLeaf(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testSize1() {
        System.out.println("size1");
        BinTree t = null;
        int expResult = 0;
        int result = BinTreeUtil.size(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testSize2() {
        System.out.println("size2");
        BinTree t = new BinTree(0, new BinTree(1), null);
        int expResult = 2;
        int result = BinTreeUtil.size(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        System.out.println("sum");
        BinTree t = new BinTree(2, new BinTree(5), new BinTree(3));
        BinTree u = new BinTree(1);
        int expResult = 10;
        int result = BinTreeUtil.sum(t);
        assertEquals(expResult, result);
        assertEquals(BinTreeUtil.sum(u), 1);
    }

    @Test
    public void testSumEmpty() {
        System.out.println("sum empty");
        BinTree t = null;
        int expResult = Integer.MIN_VALUE;
        int result = BinTreeUtil.sum(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testHeightEmpty() {
        System.out.println("height Empty");
        BinTree t = null;
        int expResult = -1;
        int result = BinTreeUtil.height(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testHeight() {
        System.out.println("height");
        BinTree t = new BinTree(0, new BinTree(0, new BinTree(0), new BinTree(0)), null);
        int expResult = 2;
        int result = BinTreeUtil.height(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxElem() {
        System.out.println("maxElem");
        BinTree t = new BinTree(0, new BinTree(8), new BinTree(2));
        int expResult = 8;
        int result = BinTreeUtil.maxElem(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxElemEmpty() {
        System.out.println("maxElemEmpty");
        BinTree t = null;
        int expResult = Integer.MIN_VALUE;
        int result = BinTreeUtil.maxElem(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxPositivePath() {
        System.out.println("maxPositivePath");
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2, new BinTree(5), new BinTree(8)));
        int expResult = 10;
        int result = BinTreeUtil.maxPath(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxPositivePathEmpty() {
        System.out.println("maxPositivePath Empty");
        BinTree t = null;
        int expResult = 0;
        int result = BinTreeUtil.maxPositivePath(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxPath() {
        System.out.println("maxPath");
        BinTree t = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(5), new BinTree(8)));
        int expResult = 10;
        int result = BinTreeUtil.maxPath(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumberOfLeavesE() {
        System.out.println("numberOfLeaves empty");
        BinTree t = null;
        int expResult = 0;
        int result = BinTreeUtil.numberOfLeaves(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumberOfLeaves() {
        System.out.println("numberOfLeaves");
        BinTree t = new BinTree(0);
        int expResult = 1;
        int result = BinTreeUtil.numberOfLeaves(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testIncrement() {
        System.out.println("increment");
        BinTree t = new BinTree(1);
        BinTreeUtil.increment(t);
        assertEquals(t.element, 1 + 1);
    }

    @Test
    public void testIncrementLeaves() {
        System.out.println("incrementLeaves");
        BinTree u = new BinTree(2);
        BinTree t = new BinTree(0, u, null);
        BinTreeUtil.incrementLeaves(t);
        assertEquals(u.element, 2 + 1);
        assertEquals(t.element, 0);
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        int x = 0;
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2));
        boolean expResult = true;
        boolean result = BinTreeUtil.search(x, t);
        assertEquals(expResult, result);
    }

    @Test
    public void testSearch2() {
        System.out.println("search 2");
        int x = 5;
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2));
        boolean expResult = false;
        boolean result = BinTreeUtil.search(x, t);
        assertEquals(expResult, result);
    }

    @Test
    public void testFind() {
        System.out.println("find");
        int x = 0;
        BinTree t = null;
        BinTree expResult = null;
        BinTree result = BinTreeUtil.find(x, t);
        assertEquals(expResult, result);
    }

    @Test
    public void testFind2() {
        System.out.println("find 2 ");
        int x = 2;
        BinTree u = new BinTree(2);
        BinTree t = new BinTree(5, null, u);
        BinTree expResult = u;
        BinTree result = BinTreeUtil.find(x, t);
        assertEquals(expResult, result);
    }

    @Test
    public void testAreEqual() {
        System.out.println("areEqual");
        BinTree t1 = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(5), new BinTree(8)));
        BinTree t2 = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(5), new BinTree(8)));
        boolean expResult = true;
        boolean result = BinTreeUtil.areEqual(t1, t2);
        assertEquals(expResult, result);
    }

    @Test
    public void testAreEqualF() {
        System.out.println("areEqual False");
        BinTree t1 = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(1), new BinTree(8)));
        BinTree t2 = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(5), new BinTree(8)));
        boolean expResult = false;
        boolean result = BinTreeUtil.areEqual(t1, t2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCopy() {
        System.out.println("copy");
        BinTree t = new BinTree(0, new BinTree(-1), new BinTree(2, new BinTree(1), new BinTree(8)));
        BinTree result = BinTreeUtil.copy(t);
        assertTrue(BinTreeUtil.areEqual(t, result));
    }

    @Test
    public void testMirrorCopy() {
        System.out.println("mirrorCopy");
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2, new BinTree(3), null));
        BinTree result1 = BinTreeUtil.mirrorCopy(t);
        BinTree result = BinTreeUtil.mirrorCopy(result1);
        assertTrue(BinTreeUtil.areEqual(t, result));
    }

    @Test
    public void testMirrorInPlace() {
        System.out.println("mirrorInPlace");
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2, new BinTree(3), null));
        BinTree mirror = BinTreeUtil.mirrorCopy(t);
        BinTreeUtil.mirrorInPlace(t);
        assertTrue(BinTreeUtil.areEqual(t, mirror));
    }

    @Test
    public void testSumDescendants1() {
        System.out.println("sumDescendants Empty");
        int x = 0;
        BinTree t = null;
        int expResult = Integer.MIN_VALUE;
        int result = BinTreeUtil.sumDescendants(x, t);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumDescendants() {
        System.out.println("sumDescendants");
        int x = 2;
        BinTree t = new BinTree(0, new BinTree(1), new BinTree(2, new BinTree(3), new BinTree(2)));
        int expResult = 7;
        int result = BinTreeUtil.sumDescendants(x, t);
        //int result = BinTreeUtil.sum(t);
        assertEquals(expResult, result);
    }

    @Test
    public void testTrimmed() {
        System.out.println("trimmed");
        int h = 2;
        BinTree u = new BinTree(5);
        BinTree v = new BinTree(0, new BinTree(1), new BinTree(2, new BinTree(3), new BinTree(2)));
        BinTree t = BinTreeUtil.add(5, v, "RRR");
        BinTree result = BinTreeUtil.trimmed(h, t);
        assertTrue(BinTreeUtil.areEqual(v, result));
    }

    @Test
    public void testPrintCentralNodes() {
        System.out.println("printCentralNodes");
        BinTree t = null;
        BinTreeUtil.printCentralNodes(t);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
