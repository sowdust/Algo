package vinci.esercizio18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import vinci.esercizio12.UnionFind;
import vinci.esercizio14.Graph;
import vinci.esercizio14.SparseGraph;
import vinci.esercizio14.SparseGraph.Edge;
import vinci.esercizio14.SparseGraph.WEdge;

/**
 * This class provides a method for computing the MST of an UNDIRECTED graph
 * through the Kruskal algorithm.
 *
 * @param <V> type of vertices content
 * @param <E> type of edges info
 */
public class MinimalSpanningTreeKruskal<V extends Comparable<V>, E> {

    /**
     * Executes a method for computing the Kruskal algorithm on a given graph.
     *
     * @param graph
     * @return collection of edges of type E that is a Minimum Spanning Tree for
     * graph
     */
    public ArrayList<E> build(Graph<V, E> graph) {

        //  prepare data structure for result
        ArrayList<E> mar = new ArrayList<>();

        //  get all edges and vertices of graph
        List<Edge> edges = graph.edges();
        ArrayList<V> nodes = graph.vertices();

        //  create union find struct as big as the # of vertices
        UnionFind set = new UnionFind(nodes.size());

        //  use an hash map to convert nodes to int
        HashMap<V, Integer> position = new HashMap<>(nodes.size());
        for (int i = 0; i < nodes.size(); ++i) {
            position.put(nodes.get(i), i);
        }

        //  sort all the edges by their weight
        Comparator<Edge> comparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge c1, Edge c2) {
                if (!(c1 instanceof SparseGraph.WEdge && c2 instanceof SparseGraph.WEdge)) {
                    return 0;
                }
                if (((WEdge) c1).getWeight() > ((WEdge) c2).getWeight()) {
                    return 1;
                }
                if (((WEdge) c1).getWeight() < ((WEdge) c2).getWeight()) {
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(edges, comparator);

        int counter = 0;
        //  PRECOND: edges is ordered by weight
        for (int i = 0; counter < nodes.size() - 1; ++i) {

            //  get integer map of vertices of current edge
            Edge current = edges.get(i);
            int v1 = position.get((V) current.getFirst().getVertex());
            int v2 = position.get((V) current.getSecond().getVertex());

            //  if the two edges are still separate by a cut in the graph
            //      this union returns false if find(v1)==find(v2)
            if (set.union(v1, v2)) {
                mar.add((E) current.getInfo());
                ++counter;
            }
        }
        return mar;
    }
}
