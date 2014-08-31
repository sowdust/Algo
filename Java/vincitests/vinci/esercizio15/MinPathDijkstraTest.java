package vinci.esercizio15;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinci.esercizio14.Graph;
import vinci.esercizio14.SparseGraph;
import vinci.esercizio14.SparseGraph.Edge;

/**
 *
 * @author mat
 */
public class MinPathDijkstraTest {

    public MinPathDijkstraTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testMinPath_3args() throws Exception {
        System.out.println("minPath");
        MinPathDijkstra instance = new MinPathDijkstra();
        Graph<String, String> graph;
        graph = SparseGraph.loadFromFile("/home/mat/school/Algo/vinci/Java/vinci/vinci/esercizio15/grafo.txt");
        Graph<String, String> result = instance.minPath(graph, "A");
        for (Edge e : result.edges()) {
            System.out.println(e);
        }

        result = instance.minPath(graph, "B", "A");
        for (Edge e : result.edges()) {
            System.out.println(e);
        }

    }

    @Test
    public void testMinPath_Graph_GenericType() {
        System.out.println("minPath");
        MinPathDijkstra instance = new MinPathDijkstra();
        Graph expResult = null;
        //Graph result = instance.minPath(null);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
