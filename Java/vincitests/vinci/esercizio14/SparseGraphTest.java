package vinci.esercizio14;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SparseGraphTest {

    Graph<String, String> graph;

    public SparseGraphTest() {
        graph = new SparseGraph();
    }

    @Test
    public void testAll() {

        ArrayList<String> nA = new ArrayList<>();
        ArrayList<String> nB = new ArrayList<>();
        ArrayList<String> nC = new ArrayList<>();
        ArrayList<String> nD = new ArrayList<>();

        System.out.println("addVertex");
        boolean expResult = true;
        boolean result = graph.addVertex("A");
        assertEquals(expResult, result);
        expResult = false;
        result = graph.addVertex("A");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.addVertex("B");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.addVertex("C");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.addVertex("D");
        assertEquals(expResult, result);

        System.out.println("addEdge");

        expResult = true;
        result = graph.addEdge("A", "B", "tra A e B");
        assertEquals(expResult, result);
        nA.add("B");
        expResult = true;
        result = graph.addEdge("B", "A", "tra B e A");
        assertEquals(expResult, result);
        nB.add("A");
        expResult = false;
        result = graph.addEdge("B", "A", "tra B e A doppio");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.addEdge("A", "A", "tra A e A");
        assertEquals(expResult, result);
        nA.add("A");
        expResult = true;
        result = graph.addEdge("A", "C", "tra A e C");
        assertEquals(expResult, result);
        nA.add("C");

        System.out.println("hasVertex");
        expResult = true;
        result = graph.hasVertex("C");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.hasVertex("A");
        assertEquals(expResult, result);
        expResult = false;
        result = graph.hasVertex("E");
        assertEquals(expResult, result);

        System.out.println("hasEdge");
        expResult = false;
        result = graph.hasEdge("C", "A");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.hasEdge("A", "C");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.hasEdge("A", "A");
        assertEquals(expResult, result);

        System.out.println("addUndirectedEdge");
        expResult = true;
        result = graph.addUndirectedEdge("C", "D", "da c ad a e da d a c");
        nC.add("D");
        nD.add("C");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.hasEdge("C", "D");
        assertEquals(expResult, result);
        expResult = true;
        result = graph.hasEdge("D", "C");
        assertEquals(expResult, result);

        System.out.println("getWeight");
        double exResult = 1;
        double res = graph.getWeight("C", "D");
        assertEquals(expResult, result);

        graph.addUndirectedEdge("A", "D", 12.5, "da A a D e da D ad A");
        exResult = 12.5;
        res = graph.getWeight("A", "D");
        assertEquals(expResult, result);
        nA.add("D");
        nD.add("A");
        res = graph.getWeight("D", "A");
        assertEquals(expResult, result);

        System.out.println("printVertices");
        for (String v : graph.vertices()) {
            System.out.print(v + " ");
        }

        System.out.println("printNeighbours");

        System.out.println("A");
        for (String v : graph.neighbours("A")) {
            System.out.print(v);
        }
        for (String v : nA) {
            System.out.print(v);
        }

        System.out.println("printNeighbours");
        System.out.println("B");
        for (String v : graph.neighbours("B")) {
            System.out.print(v);
        }
        for (String v : nB) {
            System.out.print(v);
        }

        System.out.println("printNeighbours");
        System.out.println("C");
        for (String v : graph.neighbours("C")) {
            System.out.print(v);
        }
        for (String v : nC) {
            System.out.print(v);
        }

        System.out.println("printNeighbours");
        System.out.println("D");
        for (String v : graph.neighbours("D")) {
            System.out.print(v);
        }
        for (String v : nD) {
            System.out.print(v);
        }

    }
}
