package algo.esercizio8;

/**
 * Abstract Data Type "Priority Queue"
 *
 * Elements of this particular priority queue are Strings, each of which has
 * associated its own priority
 *
 * Duplucates are not allowed
 *
 * Priority is defined as the relation "less than" on to the Complete Order of
 * the "double" data type
 *
 */
public interface PriorityQueue {

    /**
     * @return true if queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Does not modify the queue. First element is the one with highest priority
     *
     * @return first element of the queue. null if queue is empty
     */
    String first();

    /**
     * Removes the first element (with highest priority) of the queue
     *
     * @return first element of the queue. null if queue is empty
     */
    String pop();

    /**
     * Adds an element to the queue (if not already present)
     *
     * @param element the string to be added
     * @param priority priority associated to string to be added
     * @return index of insterted element
     */
    int insert(String element, double priority);

    /**
     * Removes an element from the queue (if the element exists)
     *
     * @param element element to be removed
     * @return true if element removed, false if was not in the queue
     */
    boolean remove(String element);

    /**
     * Decreases the priority of an element (if the element exists) Different
     * implementations might or might not check whether the new priority is in
     * fact lower than the old one before proceeding
     *
     * @param element element whose priority needs to be decreased
     * @param newPriority new priority to be assigned
     * @return true if the priority has changed; false if the element did not
     * exist or, varying on the implementation, if the new priority is higher
     * than the old one
     */
    boolean decreasePriority(String element, double newPriority);

}
