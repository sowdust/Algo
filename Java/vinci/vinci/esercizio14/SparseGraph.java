package vinci.esercizio14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * **
 * UNO SCHIFO! CREARE CLASSI INTERNE PER RAPPRESENTARE NODI<V> E ARCHI<E>
 *
 * @author mat
 * @param <V>
 * @param <E>
 */
public class SparseGraph<V, E> implements Graph {

    private final List<List<Integer>> nodes;
    //private final List<V> adjacent;
    private final HashMap<V, Integer> position;

    SparseGraph() {
        nodes = new ArrayList<>();
        position = new HashMap<>();
    }

    @Override
    public boolean addVertex(Object vertex) {
        Integer pos = position.get((V) vertex);
        if (null != pos) {
            return false;
        }
        //  add node list to vertices list
        List<Integer> l = new LinkedList<>();
        nodes.add(l);
        //  add its position to hashtable
        position.put((V) vertex, nodes.size() - 1);
        return true;
    }

    @Override
    public boolean addEdge(Object v1, Object v2, Object info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> adjacent = nodes.get(pv1);
        for (Integer v : adjacent) {
            //  if vertex (position) already in adjacency list
            if (v.equals(pv2)) {
                return false;
            }
        }
        adjacent.add(pv2);
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

}
