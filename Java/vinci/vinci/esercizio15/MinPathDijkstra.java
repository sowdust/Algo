package vinci.esercizio15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import vinci.esercizio14.Graph;
import vinci.esercizio14.SparseGraph;
import vinci.esercizio14.SparseGraph.Edge;

/**
 * Implements Dijkstra algorithm to find minimum path over a positive-weighted
 * graph
 *
 * @param <V> type of vertices
 * @param <E> type of edges
 */
public class MinPathDijkstra<V, E> {

    /**
     * PRECONDITION: all weights are POSITIVE
     *
     * @param g input graph
     * @param s starting vertex
     * @param d destination vertex
     * @return shortest path from s to d; null if it does not exist
     */
    public Graph<V, E> minPath(Graph<V, E> g, V s, V d) {

        Graph<V, E> result;

        return null;

    }

    /**
     * PRECONDITION: all weights are POSITIVE
     *
     * @param g input graph
     * @param s starting vertex
     * @return shortest path from s
     */
    public Graph<V, E> minPath(Graph<V, E> g, V s) {

        Graph<V, E> result = new SparseGraph<>();

        //  set up
        ArrayList<V> nodes = g.vertices();
        ArrayList<V> father = new ArrayList(nodes.size());
        HashMap<V, Integer> position = new HashMap<>();
        double[] distance = new double[nodes.size()];
        //  not strictly necessary now, but will be in Prim
        boolean[] definitive = new boolean[nodes.size()];

        Comparator<VwPrior> comparator = new Comparator<VwPrior>() {
            @Override
            public int compare(VwPrior c1, VwPrior c2) {

                if (c1.prior > c2.prior) {
                    return 1;
                }
                if (c1.prior < c2.prior) {
                    return -1;
                }
                return 0;
            }
        };

        PriorityQueue<VwPrior> queue = new PriorityQueue<>(nodes.size(), comparator);

        /**
         * DA RICOMINCIARE QUA!! *
         */
        //  init
        for (int i = 0; i < nodes.size(); ++i) {
            V n = nodes.get(i);
            distance[i] = Double.POSITIVE_INFINITY;
            definitive[i] = false;
            father.add(i, null);
            result.addVertex(nodes.get(i));
            position.put(n, i);
        }

        int startPos = position.get(s);
        father.set(startPos, s);
        distance[startPos] = 0;

        for (V n : nodes) {
            int pos = position.get(n);
            VwPrior c = new VwPrior(n, distance[pos]);
            queue.offer(c);
        }

        while (!queue.isEmpty()) {
            VwPrior c = queue.poll();
            V v = (V) c.vertex;
            int pos = position.get(c);
            definitive[pos] = true;

            for (Edge adj : g.edges(v)) {

                /**
                 * if (not definitivo[v] && dist[u] + c uv < dist[v] ) {
                 * padre[v] = u; dist[v] = dist[u] + c uv ; decreasePriority(v,
                 * dist[v], Q); cioè fa una moveUp
                 */
            }

        }

        return result;

    }

    class VwPrior<V> {

        V vertex;
        double prior;

        VwPrior(V vertex, double prior) {
            this.vertex = vertex;
            this.prior = prior;
        }
    }

}
