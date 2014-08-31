package vinci.esercizio15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import vinci.esercizio14.Graph;
import vinci.esercizio14.SparseGraph;

/**
 * Implements Dijkstra algorithm to find minimum path over a positive-weighted
 * graph.
 *
 * I wanted to try using Java PriorityQueue API
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

        //  set up data structures
        ArrayList<V> nodes = g.vertices();
        ArrayList<V> father = new ArrayList(nodes.size());
        HashMap<V, Integer> position = new HashMap<>();
        double[] distance = new double[nodes.size()];
        //  "definitive" necessary because using Java PriorityQueue api.
        //  It does not offer an "update" (moveUp) method, so we need to add
        //  a new element to queue each time instead of updating the original
        boolean[] definitive = new boolean[nodes.size()];

        //  space for the result: (and initialize it)
        Graph<V, E> result = new SparseGraph<>();
        for (V n : nodes) {
            result.addVertex(n);
        }

        //  comparator to be used with Java Priority queue
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
        //  and the actual queue
        PriorityQueue<VwPrior> queue = new PriorityQueue<>(nodes.size(), comparator);

        //  INITIALIZATION
        for (int i = 0; i < nodes.size(); ++i) {
            V n = nodes.get(i);
            distance[i] = Double.POSITIVE_INFINITY;
            definitive[i] = false;
            father.add(i, null);
            result.addVertex(nodes.get(i));
            position.put(n, i);
        }
        int startPos = position.get(s);
        distance[startPos] = 0;

        //  fill the queue with all the nodes
        for (V n : nodes) {
            int pos = position.get(n);
            VwPrior c = new VwPrior(n, distance[pos]);
            queue.offer(c);
        }

        //  keep going until we "finish" with destination node
        while (true) {
            VwPrior c = queue.poll();
            V currentVertex = (V) c.vertex;
            int pos = position.get(currentVertex);
            definitive[pos] = true;

            //  check for exit condition
            if (currentVertex == d) {
                break;
            }
            //  for each adjacent node, see if there is a less expensive path
            //  to it through this one
            for (V adj : g.neighbours(currentVertex)) {
                int adjPos = position.get(adj);
                //  if the neighbour hasn't been "closed" yet
                if (!definitive[adjPos]) {
                    double weight = g.getWeight(currentVertex, adj) + distance[pos];
                    //  if the path to currentVertex is less than its current distance
                    if (weight < distance[adjPos]) {
                        distance[adjPos] = weight;
                        father.set(adjPos, currentVertex);
                        //  java priority queue does not provide an update method!!
                        VwPrior update = new VwPrior(adj, weight);
                        queue.offer(update);
                    }

                }
            }
        }

        //  now build the tree
        for (int i = 0; i < nodes.size(); ++i) {
            V current = nodes.get(i);
            V dad = nodes.get(position.get(current));
            result.addEdge(dad, current, null);
        }
        return result;

    }

    /**
     * PRECONDITION: all weights are POSITIVE
     *
     * @param g input graph
     * @param s starting vertex
     * @return shortest path from s
     */
    public Graph<V, E> minPath(Graph<V, E> g, V s) {

        //  set up data structures
        ArrayList<V> nodes = g.vertices();
        ArrayList<V> father = new ArrayList(nodes.size());
        HashMap<V, Integer> position = new HashMap<>();
        double[] distance = new double[nodes.size()];
        //  "definitive" necessary because using Java PriorityQueue api.
        //  It does not offer an "update" (moveUp) method, so we need to add
        //  a new element to queue each time instead of updating the original
        boolean[] definitive = new boolean[nodes.size()];

        //  space for the result: (and initialize it)
        Graph<V, E> result = new SparseGraph<>();
        for (V n : nodes) {
            result.addVertex(n);
        }

        //  comparator to be used with Java Priority queue
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
        //  and the actual queue
        PriorityQueue<VwPrior> queue = new PriorityQueue<>(nodes.size(), comparator);

        //  INITIALIZATION
        for (int i = 0; i < nodes.size(); ++i) {
            V n = nodes.get(i);
            distance[i] = Double.POSITIVE_INFINITY;
            definitive[i] = false;
            father.add(i, null);
            result.addVertex(nodes.get(i));
            position.put(n, i);
        }
        int startPos = position.get(s);
        distance[startPos] = 0;

        //  fill the queue with all the nodes
        for (V n : nodes) {
            int pos = position.get(n);
            VwPrior c = new VwPrior(n, distance[pos]);
            queue.offer(c);
        }

        int counter = 0;    // <-- necessary because of java PQ api!
        //  keep going until all nodes are in MST (MAR)
        while (true) {
            VwPrior c = queue.poll();
            V currentVertex = (V) c.vertex;
            int pos = position.get(currentVertex);
            definitive[pos] = true;
            System.out.println(currentVertex + " diventato definitivo");

            //  check for exit condition
            if (++counter == nodes.size()) {
                break;
            }
            //  for each adjacent node, see if there is a less expensive path
            //  to it through this one
            for (V adj : g.neighbours(currentVertex)) {
                int adjPos = position.get(adj);
                //  if the neighbour hasn't been "closed" yet
                if (!definitive[adjPos]) {
                    double weight = g.getWeight(currentVertex, adj) + distance[pos];
                    //  if the path to currentVertex is less than its current distance
                    if (weight < distance[adjPos]) {
                        distance[adjPos] = weight;
                        father.set(adjPos, currentVertex);
                        //  java priority queue does not provide an update method!!
                        VwPrior update = new VwPrior(adj, weight);
                        queue.offer(update);
                    }

                }
            }
        }

        //  now build the tree
        for (int i = 0; i < nodes.size(); ++i) {
            V current = nodes.get(i);
            V dad = father.get(i);
            if (dad != null) {
                result.addEdge(dad, current, null);
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
