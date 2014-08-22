package vinci.esercizio8;

import java.util.HashMap;

public class HeapPriorityQueue implements PriorityQueue {

    private HashMap<String, Integer> position;
    private int last;
    private Element[] heap;

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String first() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String pop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param element
     * @param priority
     * @return
     */
    @Override
    public boolean insert(String element, double priority) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(String element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean decreasePriority(String element, double newPriority) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * PRECONDITION: the element should not be moved down (ie it has higher
     * priority (lower numerical value) than its successors in the heap
     *
     * Moves the element with index i up to its correct place in the heap
     *
     * @param i
     */
    private void moveUp(int i) {

        if (i > last) {
            throw new IllegalArgumentException();
        }

        Element e = heap[i];
        // until we find the right place for the new element
        while (i > 0 && e.priority < heap[(i - 1) / 2].priority) {
            // swap the empty place (index i) with its father element
            heap[i] = heap[(i - 1) / 2];
            i = (i - 1) / 2;
        }
        heap[i] = e;
    }

    private void moveDown(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class Element {

        String string;
        double priority;

        Element(String string, double priority) {
            this.string = string;
            this.priority = priority;
        }
    }

}
