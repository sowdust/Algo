package vinci.esercizio14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SparseGraph implements Graph {

    private final List<Node> nodes;
    private final HashMap<Node, Integer> position;

    SparseGraph() {
        this.nodes = new ArrayList<>();
        this.position = new HashMap<>();
    }

    @Override
    public boolean addVertex(Object vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addEdge(Object v1, Object v2, Object info) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean hasVertex(Object vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasEdge(Object v1, Object v2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
