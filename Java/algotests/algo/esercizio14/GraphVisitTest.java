package algo.esercizio14;

import algo.esercizio14.VertexAnalyzer;
import algo.esercizio14.Graph;
import algo.esercizio14.GraphVisit;
import algo.esercizio14.GraphConcreteVisit;
import algo.esercizio14.SparseGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphVisitTest {

    public GraphVisitTest() {

    }

    @Test
    public void testBreadthFirst() {

    }

    @Test
    public void testDepthFirst() throws Exception {
        System.out.println("depthFirst");
        Graph<String, String> expResult = new SparseGraph<String, String>();
        GraphVisit<String, String> instance = new GraphConcreteVisit<>();
        Graph<String, String> graph = new SparseGraph<String, String>();
        VertexAnalyzer<String> va = new Analyzer<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "B", "");
        graph.addEdge("A", "G", "");
        graph.addEdge("B", "A", "");
        graph.addEdge("B", "C", "");
        graph.addEdge("B", "H", "");
        graph.addEdge("C", "B", "");
        graph.addEdge("C", "G", "");
        graph.addEdge("C", "D", "");
        graph.addEdge("D", "C", "");
        graph.addEdge("D", "H", "");
        graph.addEdge("D", "F", "");
        graph.addEdge("E", "F", "");
        graph.addEdge("E", "H", "");
        graph.addEdge("F", "G", "");
        graph.addEdge("F", "D", "");
        graph.addEdge("F", "E", "");
        graph.addEdge("G", "A", "");
        graph.addEdge("G", "C", "");
        graph.addEdge("G", "F", "");
        graph.addEdge("H", "D", "");
        graph.addEdge("H", "E", "");
        graph.addEdge("H", "B", "");

        Graph<String, String> result = instance.depthFirst(graph, "A", va);

        System.out.println("Vertici:");
        for (String s : result.vertices()) {
            System.out.print(s + ":\t");
            for (String k : result.neighbours(s)) {
                System.out.print(k + ", ");
            }
            System.out.println();
        }

        result = instance.breadthFirst(graph, "A", va);

        System.out.println("Vertici:");
        for (String s : result.vertices()) {
            System.out.print(s + ":\t");
            for (String k : result.neighbours(s)) {
                System.out.print(k + ", ");
            }
            System.out.println();
        }

        graph = SparseGraph.loadFromFile("/home/mat/school/Algo/vinci/Java/vinci/vinci/esercizio14/grafo.txt");
        System.out.println("Ampiezza");
        result = instance.breadthFirst(graph, "A", va);
        System.out.println("Profondità");
        result = instance.depthFirst(graph, "A", va);
        /*
         System.out.println("Vertici:");
         for (String s : result.vertices()) {
         System.out.print(s + ":\t");
         for (String k : result.neighbours(s)) {
         System.out.print(k + ", ");
         }
         System.out.println();
         }
         */

    }

    public class Analyzer<V> implements VertexAnalyzer<V> {

        @Override
        public void analyze(V vertex) {
            System.out.println(vertex);
        }

    }

}
