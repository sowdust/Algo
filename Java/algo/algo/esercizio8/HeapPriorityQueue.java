package algo.esercizio8;

import java.util.HashMap;

/**
 * Priority queue implemented as a heap.
 *
 * INVARIANT: no duplicates Ɐi . 1 ≤ i ≤ last -> heap[i-1/2].priority ≤ heap[i]
 * priority && Ɐi last < i < heap.length -> heap[i] = null;
 *
 */
public class HeapPriorityQueue implements PriorityQueue {

    private final HashMap<String, Integer> position;
    private int last;
    private Element[] heap;
    private static final int DEFAULT_SIZE = 10;

    public HeapPriorityQueue() {
        last = -1;
        position = new HashMap<>();
        heap = new Element[DEFAULT_SIZE];
    }

    /**
     *
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return last == -1;
    }

    /**
     *
     * Does not modify the queue
     *
     * @return first element, null if queue empty
     */
    @Override
    public String first() {
        if (last < 0) {
            return null;
        }
        return heap[0].getString();
    }

    /**
     * removes and returns first element of the queue (the one having highest
     * priority - lowest numerical value)
     *
     * @return first element, null if queue empty
     */
    @Override
    public String pop() {
        if (last < 0) {
            return null;
        }
        String first = heap[0].getString();
        //  remove index from hashmap
        position.remove(first);
        Element ultimo = heap[last];
        heap[last--] = null;
        if (last >= 0) {
            heap[0] = ultimo;
            moveDown(0);
        }
        return first;
    }

    /**
     * Adds an element to the queue respecting the implementation invariant
     *
     * @param element
     * @param priority
     * @return position of inserted element
     */
    @Override
    public int insert(String element, double priority) {
        //  if element already exists
        Integer pos = position.get(element);
        if (null != pos) {
            return pos;
        }
        //  if heap out of memory
        if (last == heap.length - 1) {
            reallocate(true);
        }
        //  add the element as a leaf
        heap[++last] = new Element(element, priority);
        //  move element to its right place
        moveUp(last);

        return last;
    }

    /**
     * removes an element from the queue (if the element exists)
     *
     * @param element value of element to be removed
     * @return true if element removed, false if it was not in the queue
     */
    @Override
    public boolean remove(String element) {
        Integer i = position.get(element);
        if (null == i) {
            return false;
        }
        position.remove(element);
        heap[i] = heap[last--];
        if (i < last) {
            moveDown(i);
        }
        if (last < (heap.length - 1) / 4) {
            reallocate(false);
        }
        return true;
    }

    /**
     * Decreases the priority of an element (if the element exists).
     *
     * If the new priority is not lower than the original (or equal), throws an
     * exception
     *
     * @param element value of element whose priority needs to be decreased
     * @param newPriority new priority to be assigned
     * @return true if the priority has changed; false if the element did not
     * exist
     */
    @Override
    public boolean decreasePriority(String element, double newPriority) {

        Integer pos = position.get(element);
        if (null == pos) {
            return false;
        }

        Element e = heap[pos];
        if (e.getPrior() < newPriority) {
            throw new IllegalArgumentException();
        }

        e.setPrior(newPriority);

        if (pos > 0) {
            moveUp(pos);
        }

        return true;

    }

    /**
     *
     * PRECONDITION: the element should not be moved down (ie it has higher
     * priority (lower numerical value) than its successors in the heap
     *
     * LOOP INVARIANT: i is index of the currently empty element
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
            //  update index table
            position.put(heap[i].getString(), i);
            i = (i - 1) / 2;
        }
        heap[i] = e;
        //  update index table
        position.put(heap[i].getString(), i);
    }

    /**
     *
     * PRECONDITION: there is at least one element with lower (higher numerical
     * value) priority
     *
     * LOOP INVARIANT: i is index of the currently empty element
     *
     * @param i
     */
    private void moveDown(int i) {
        if (i > last) {
            throw new IllegalArgumentException();
        }

        Element e = heap[i];
        int child = 2 * i + 1;

        while (child <= last) {
            //  if right child has higher (lower numerical) priority than left
            if (((child + 1) <= last) && heap[child + 1].getPrior() <= heap[child].getPrior()) {
                ++child;
            }
            //  check whether we are in the correct place; if so, exit the loop
            if (e.getPrior() <= heap[child].getPrior()) {
                break;
            }
            //  keep swapping empty place with highest priority child
            heap[i] = heap[child];
            //  update index table
            position.put(heap[i].getString(), i);
            i = child;
            child = 2 * i + 1;
        }
        heap[i] = e;
        //  update index table
        position.put(heap[i].getString(), i);
    }

    /**
     * changes the heap size: reduces it by half when last element is lass than
     * 1/4 of size; doubles it when array is full
     *
     * Respects the invariant for which all a[i] with i > last are null
     *
     * @param more
     */
    private void reallocate(boolean more) {
        Element[] t;
        if (more) {
            t = new Element[heap.length * 2];
        } else {
            t = new Element[heap.length / 2];
        }
        for (int i = 0; i <= last; ++i) {
            t[i] = heap[i];
        }
        for (int i = last + 1; i < t.length; ++i) {
            t[i] = null;
        }
        heap = t;
    }

    private void stampa() {
        System.out.println("Last: " + last);
        for (int i = 0; i <= last; ++i) {
            System.out.print("[" + i + "] " + heap[i].getString());
        }
        System.out.println();

    }

    private class Element {

        private String string;
        private double priority;

        Element(String string, double priority) {
            this.string = string;
            this.priority = priority;
        }

        public String getString() {
            return string;
        }

        public double getPrior() {
            return priority;
        }

        private void setPrior(double priority) {
            this.priority = priority;
        }
    }

}
