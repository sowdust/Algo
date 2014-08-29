package vinci.esercizio14;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * SparseGraph implements the general Graph interface for, guess, Sparse Graphs.
 * It uses appropriate data types (adjacency - better incidency - lists instead
 * of adjacency matrix).
 *
 * To play with Java reflection I have implemented Weighted edges as a subclass
 * of regular Edges (which do not have a weight value at all)
 *
 * Design-wise it would have probably been better to create a single class with
 * a default weight value of 1 and an overloaded constructor
 *
 * @param <V> type of vertices
 * @param <E> type of edges
 */
public class SparseGraph<V, E> implements Graph<V, E> {

    private final List<Node> nodes;
    private final HashMap<V, Integer> position;

    public SparseGraph() {
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
    public boolean addVertex(V vertex) {
        Integer pos = position.get((V) vertex);
        //  if vertex already exists
        if (null != pos) {
            return false;
        }
        //  create vertex and add it to graph
        nodes.add(new Node((V) vertex));
        //  add vertex index into position dictionary
        position.put((V) vertex, nodes.size() - 1);

        return true;
    }

    /**
     * adds edge (v1, v2) to the graph, assigning information info, only if the
     * edge does not exist.
     *
     * @param v1 First vertex belonging to the edge
     * @param v2 Second vertex belonging to the edge
     * @param info edge extra info. It can be null.
     * @return true if vertex was added, false otherwise
     * @throws IllegalArgumentException if a vertex doest not belong to the
     * graph
     */
    @Override
    public boolean addEdge(V v1, V v2, E info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pv1);
        Node n2 = nodes.get(pv2);
        // check if vertex is already adjacent
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

    /**
     * adds edge (v1, v2) to the graph, assigning weight and information info,
     * only if the edge dows not exist.
     *
     * @param v1 First vertex belonging to the edge
     * @param v2 Second vertex belonging to the edge
     * @param weight Cost of the edge
     * @param info edge extra info. It can be null.
     * @return true if vertex was added, false otherwise
     * @throws IllegalArgumentException if a vertex doest not belong to the
     * graph
     */
    @Override
    public boolean addEdge(V v1, V v2, double weight, E info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pv1);
        Node n2 = nodes.get(pv2);
        // check if vertex is already adjacent
        for (Object adj : n1.incident) {
            if (((Edge) adj).isSecond(n2)) {
                return false;
            }
        }
        //  create the new edge and add it to n1 incident list
        Edge e = new WEdge(n1, n2, (E) info, weight);
        n1.incident.add(e);

        return true;
    }

    /**
     * adds undirected edge (v1, v2), (v2,v1) to the graph assigning information
     * info, only if the edge dows not exist.
     *
     * IMPLEMENTATION: It does NOT add the undirected edge if a directed edge
     * already existed
     *
     * @param v1 First vertex belonging to the edge
     * @param v2 Second vertex belonging to the edge
     * @param info edge extra info. It can be null.
     * @return true if vertex was added, false otherwise
     * @throws IllegalArgumentException if a vertex doest not belong to the
     * graph
     */
    @Override
    public boolean addUndirectedEdge(V v1, V v2, E info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pv1);
        Node n2 = nodes.get(pv2);
        // check if vertex is already adjacent
        for (Object adj : n1.incident) {
            if (((Edge) adj).isNode(n2)) {
                return false;
            }
        }
        for (Object adj : n2.incident) {
            if (((Edge) adj).isNode(n1)) {
                return false;
            }
        }
        //  create the new edge and add it to both incident lists
        Edge e1 = new Edge(n1, n2, (E) info);
        Edge e2 = new Edge(n2, n1, (E) info);
        n1.incident.add(e1);
        n2.incident.add(e2);

        return true;
    }

    /**
     * adds undirected edge (v1, v2), (v2,v1) to the graph assigning weight and
     * information info, only if the edge dows not exist.
     *
     * IMPLEMENTATION: It does NOT add the undirected edge if a directed edge
     * already existed
     *
     * @param v1 First vertex belonging to the edge
     * @param v2 Second vertex belonging to the edge
     * @param info edge extra info. It can be null.
     * @return true if vertex was added, false otherwise
     * @throws IllegalArgumentException if a vertex doest not belong to the
     * graph
     */
    @Override
    public boolean addUndirectedEdge(V v1, V v2, double weight, E info) throws IllegalArgumentException {
        Integer pv1 = position.get((V) v1);
        Integer pv2 = position.get((V) v2);
        if (pv1 == null || pv2 == null) {
            throw new IllegalArgumentException();
        }
        Node n1 = nodes.get(pv1);
        Node n2 = nodes.get(pv2);
        // check if vertex is already adjacent
        for (Object adj : n1.incident) {
            if (((Edge) adj).isNode(n2)) {
                return false;
            }
        }
        for (Object adj : n2.incident) {
            if (((Edge) adj).isNode(n1)) {
                return false;
            }
        }
        //  create the new edge and add it to both incident lists
        Edge e1 = new WEdge(n1, n2, (E) info, weight);
        Edge e2 = new WEdge(n2, n1, (E) info, weight);
        n1.incident.add(e1);
        n2.incident.add(e2);

        return true;
    }

    /**
     * Check for vertex existance
     *
     * @param vertex to be found
     * @return true if vertex exists, false otherwise
     */
    @Override
    public boolean hasVertex(V vertex) {
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
    public boolean hasEdge(V v1, V v2) throws IllegalArgumentException {
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

    /**
     * Returns weight of a single edge, if the edge is a WEdge, 1 otherwise
     *
     * @param v1 origin vertex
     * @param v2 destination vertes
     * @return edge's weight
     * @throws IllegalArgumentException if vertices or edge not in graph
     */
    @Override
    public double getWeight(V v1, V v2) throws IllegalArgumentException {
        Integer pos1 = position.get((V) v1);
        Integer pos2 = position.get((V) v2);
        if (null == pos1 || null == pos2) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        Node n1 = nodes.get(pos1);
        Node n2 = nodes.get(pos2);
        Edge e = null;
        for (Object adj : n1.incident) {
            if (((Edge) adj).isSecond(n2)) {
                e = (Edge) adj;
            }
        }
        if (null == e) {
            throw new IllegalArgumentException("Edge not in graph");
        }
        //  use reflection to check whether this is a weighted edge
        if (e instanceof WEdge) {
            return ((WEdge) e).getWeight();
        }

        return 1;

    }

    /**
     * @return list of all vertices in the graph
     */
    @Override
    public ArrayList<V> vertices() {
        ArrayList<V> vertices = new ArrayList(nodes.size());
        for (Node n : nodes) {
            vertices.add((V) n.vertex);
        }
        return vertices;
    }

    /**
     * @param vertex
     * @return adjacency list of vertex; null if vertex does not belong to the
     * graph
     */
    @Override
    public ArrayList<V> neighbours(V vertex) {
        Integer pos = position.get((V) vertex);
        if (pos == null) {
            return null;
        }
        Node n = nodes.get(pos);
        ArrayList<V> neighbours = new ArrayList<>();
        for (Object edge : n.incident) {
            neighbours.add((V) ((Edge) edge).getSecond().vertex);
        }

        return neighbours;
    }

    public ArrayList<Edge> edges() {
        HashMap<Edge, Integer> unique = new HashMap<>();
        ArrayList<Edge> edges = new ArrayList<>();

        for (Node n : nodes) {
            for (Object e : n.getEdges()) {
                if (unique.get((Edge) e) == null) {
                    unique.put((Edge) e, 0);
                    edges.add((Edge) e);
                }
            }
        }
        return edges;
    }

    //  LE CLASSI QUI SOTTO SONO DEFINITE COME PUBBLICHE
    //  SUCCESSIVAMENTE ALLO SVILUPPO DEL COMPITO
    //  PER ESSERE USATE NEGLI ESERCIZI SUCCESSIVI
    public class Edge<E> {

        E info;
        Node node1;
        Node node2;

        Edge(Node node1, Node node2, E info) {
            this.node1 = node1;
            this.node2 = node2;
            this.info = info;
        }

        boolean isSecond(Node n) {
            return n == node2;
        }

        boolean isNode(Node n) {
            return n == node1 || n == node2;
        }

        public Node getFirst() {
            return node1;
        }

        public Node getSecond() {
            return node2;
        }

        public E getInfo() {
            return info;
        }

        @Override
        public String toString() {
            return "[ " + node1 + " -> " + node2 + " ]" + info + " ";
        }

    }

    public class WEdge<E> extends Edge<E> {

        double weight;

        public WEdge(Node node1, Node node2, E info, double weight) {
            super(node1, node2, info);
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return super.toString() + " { " + weight + " } ";
        }

    }

    public class Node<V> {

        V vertex;
        List<Edge> incident;

        Node(V node) {
            this.vertex = node;
            this.incident = new LinkedList<>();
        }

        @Override
        public String toString() {
            return vertex.toString();
        }

        List<Edge> getEdges() {
            return incident;
        }

        public V getVertex() {
            return vertex;
        }
    }

    public static Graph<String, String> loadFromFile(String fileName) throws Exception {
        Graph<String, String> graph = new SparseGraph();
        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Scanner lineScan = new Scanner(line);
                if (lineScan.hasNext()) {
                    String nome1 = lineScan.next();
                    graph.addVertex(nome1);
                    if (lineScan.hasNext()) {
                        String nome2 = lineScan.next();
                        graph.addVertex(nome2);
                        if (lineScan.hasNextInt()) {
                            double peso = lineScan.nextInt();
                            graph.addEdge(nome1, nome2, peso, nome1 + " -> " + nome2 + " {" + peso + " }");
                        } else {
                            throw new Exception("Bad string format");
                        }
                    } else {
                        throw new Exception("Bad string format");
                    }
                } else {
                    throw new Exception("Bad string format");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }

}
