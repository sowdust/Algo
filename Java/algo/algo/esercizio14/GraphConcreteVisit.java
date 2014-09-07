package algo.esercizio14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GraphConcreteVisit<V, E> implements GraphVisit<V, E> {

    ArrayList<V> nodes;
    ArrayList<V> father;
    boolean[] visited;
    HashMap<V, Integer> position;
    Graph<V, E> result;
    Queue<V> frontier;

    /**
     * Implements a COMPLETE breadth first visit on a given graph performing the
     * analyze() method of a given vertex analyzer on each node when it is first
     * encountered (becomes "gray")
     *
     * @param graph graph to visit
     * @param s startin node
     * @param va class containing analyze method
     * @return tree visit
     */
    @Override
    public Graph<V, E> breadthFirst(Graph<V, E> graph, V s, VertexAnalyzer<V> va) {
        frontier = new LinkedList<>();
        nodes = graph.vertices();
        father = new ArrayList(nodes.size());
        visited = new boolean[nodes.size()];
        position = new HashMap<>();
        result = new SparseGraph();

        if (nodes.indexOf(s) == -1) {
            throw new IllegalArgumentException();
        }

        //  init basic data structure and result ASD
        for (int i = 0; i < nodes.size(); ++i) {
            position.put(nodes.get(i), i);
            visited[i] = false;
            father.add(i, null);
            result.addVertex(nodes.get(i));
        }

        bFirst(graph, s, va);

        for (int i = 0; i < nodes.size(); ++i) {
            if (!visited[i]) {
                bFirst(graph, nodes.get(i), va);
            }
        }

        for (int i = 0; i < nodes.size(); ++i) {
            V n = nodes.get(i);
            V f = father.get(i);
            if (f != null) {
                result.addEdge(f, n, null);
            }
        }
        return result;

    }

    /**
     * Implements a COMPLETE depth first (recursive) visit on a given graph
     * performing the analyze() method of a given vertex analyzer on each node
     * when it is first encountered (becomes "gray")
     *
     * @param graph graph to visit
     * @param s startin node
     * @param va class containing analyze method
     * @return tree visit
     */
    @Override
    public Graph<V, E> depthFirst(Graph<V, E> graph, V s, VertexAnalyzer<V> va) {
        //  set up basic data structures
        nodes = graph.vertices();
        father = new ArrayList(nodes.size());
        visited = new boolean[nodes.size()];
        position = new HashMap<>();
        result = new SparseGraph();

        if (nodes.indexOf(s) == -1) {
            throw new IllegalArgumentException();
        }

        //  init basic data structure and result ASD
        for (int i = 0; i < nodes.size(); ++i) {
            position.put(nodes.get(i), i);
            visited[i] = false;
            father.add(i, null);
            result.addVertex(nodes.get(i));
        }

        recVisit(graph, s, va);
        for (int i = 0; i < nodes.size(); ++i) {
            if (!visited[i]) {
                recVisit(graph, nodes.get(i), va);
            }
        }

        for (int i = 0; i < nodes.size(); ++i) {
            V n = nodes.get(i);
            V f = father.get(i);
            if (f != null) {
                result.addEdge(f, n, null);
            }
        }
        return result;
    }

    private void recVisit(Graph<V, E> graph, V s, VertexAnalyzer<V> va) {
        int u = position.get(s);
        va.analyze(s);
        // let us mark the node as "visited" : GRAY!
        visited[u] = true;
        // let's get node's adjacent vertices
        for (V adj : graph.neighbours(s)) {
            int pos = position.get(adj);
            if (!visited[pos]) {
                father.set(pos, s);
                recVisit(graph, adj, va);
            }
        }
    }

    private void bFirst(Graph<V, E> graph, V s, VertexAnalyzer<V> va) {

        int pos = position.get(s);
        visited[pos] = true;
        frontier.add(s);
        va.analyze(s);

        while (!frontier.isEmpty()) {

            V u = frontier.remove();
            //  u is now BLACK
            for (V adj : graph.neighbours(u)) {

                int adjPos = position.get(adj);
                if (!visited[adjPos]) {
                    va.analyze(adj);
                    visited[adjPos] = true;
                    //  adjacent is now GRAY
                    father.set(adjPos, u);
                    frontier.offer(adj);
                }
            }
        }
    }

}
