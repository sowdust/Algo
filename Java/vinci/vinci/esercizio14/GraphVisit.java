package vinci.esercizio14;

public interface GraphVisit<V, E> {

    /**
     * Makes a complete breadth-first visit to a graph starting from a given
     * node and performing an action on each visited node
     *
     * @param graph graph to be visited
     * @param s starting node
     * @param va determines the "analyze" method to be called upon each vertex
     * @return graph made from visit tree
     */
    Graph<V, E> breadthFirst(Graph<V, E> graph, V s, VertexAnalyzer<V> va);

    /**
     * Makes a complete depth-first visit to a graph starting from a given node
     * and performing an action on each visited node
     *
     * @param graph graph to be visited
     * @param s starting node
     * @param va determines the "analyze" method to be called upon each vertex
     * @return graph made from visit tree
     */
    Graph<V, E> depthFirst(Graph<V, E> graph, V s, VertexAnalyzer<V> va);

}
