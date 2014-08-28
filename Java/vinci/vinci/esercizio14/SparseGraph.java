package vinci.esercizio14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SparseGraph<V, E> implements Graph {

    private final List<Node> nodes;
    private final HashMap<V, Integer> position;

    SparseGraph() {
        this.nodes = new ArrayList<>();
        this.position = new HashMap<>();
    }

    /**
     * adds vertex to nodes list if it does not exist already.
     *
     * @param vertex to be added
     * @return true if vertex was added
     */
    @Override
    public boolean addVertex(Object vertex) {
        Integer pos = position.get((V) vertex);
        //  if vertex already exists
        if (null != pos) {
            return false;
        }
        //  create node and add it to graph
        nodes.add(new Node((V) vertex));
        //  add node index into position dictionary
        position.put((V) vertex, nodes.size() - 1);

        return true;
    }

    /**
     * adds edge (v1, v2) to the graph, assigning default weight 1 and
     * information info, only if the edge does not exist.
     *
     * @param v1 First vertex belonging to the edge
     * @param v2 Second vertex belonging to the edge
     * @param info edge extra info. It can be null.
     * @return true if vertex was added, false otherwise
     * @throws IllegalArgumentException if a vertex doest not belong to the
     * graph
     */
    @Override
    public boolean addEdge(Object v1, Object v2, Object info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pv1);
        Node n2 = nodes.get(pv2);
        // check if node is already adjacent
        for (Object adj : n1.incident) {
            if (((Edge) adj).isSecond(n2)) {
                return false;
            }
        }
        //  create the new edge and add it to n1 incident list
        Edge e = new Edge(n1, n2, (E) info);
        n1.incident.add(e);

        return true;
    }

    @Override
    public boolean addEdge(Object v1, Object v2, double weight, Object info) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUndirectedEdge(Object v1, Object v2, Object info) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addEdgeUndirected(Object v1, Object v2, double weight, Object info) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Check for vertex existance
     *
     * @param vertex to be found
     * @return true if vertex exists, false otherwise
     */
    @Override
    public boolean hasVertex(Object vertex) {
        return position.get((V) vertex) != null;
    }

    /**
     * Check for edge existance
     *
     * @param v1
     * @param v2
     * @return true if edge found, false otherwise
     * @throws IllegalArgumentException if vertices not in graph
     */
    @Override
    public boolean hasEdge(Object v1, Object v2) throws IllegalArgumentException {
        Integer pos1 = position.get((V) v1);
        Integer pos2 = position.get((V) v2);
        if (pos1 == null || pos2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pos1);
        Node n2 = nodes.get(pos2);
        for (Object adj : n1.incident) {
            if (((Edge) adj).isSecond(n2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getWeight(Object from, Object to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList vertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList neighbours(Object vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class Edge<E> {

        E info;
        Node node1;
        Node node2;

        Edge(Node node1, Node node2, E info) {
            this.node1 = node1;
            this.node2 = node2;
            this.info = info;
        }

        boolean isFirst(Node n) {
            return n == node1;
        }

        boolean isSecond(Node n) {
            return n == node2;
        }

        boolean isNode(Node n) {
            return n == node1 || n == node2;
        }
    }

    private class WEdge<E> extends Edge<E> {

        double weight;

        public WEdge(Node node1, Node node2, E info, double weight) {
            super(node1, node2, info);
            this.weight = weight;
        }
    }

    private class Node<V> {

        V node;
        List<Edge> incident;

        Node(V node) {
            this.node = node;
            this.incident = new LinkedList<>();
        }
    }

}
