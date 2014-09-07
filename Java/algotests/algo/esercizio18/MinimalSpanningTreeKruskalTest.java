package algo.esercizio18;

import algo.esercizio18.MinimalSpanningTreeKruskal;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import algo.esercizio14.Graph;
import algo.esercizio14.SparseGraph;

public class MinimalSpanningTreeKruskalTest {

    @Test
    public void testBuild() throws Exception {
        System.out.println("Testing kruskal...");
        Graph<String, String> graph;

        graph = SparseGraph.loadFromFile("/home/mat/school/Algo/vinci/Java/vinci/vinci/esercizio18/grafo.txt");

        MinimalSpanningTreeKruskal instance = new MinimalSpanningTreeKruskal();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.build(graph);

        for (String e : result) {
            System.out.println(e);
        }

    }

}
