package vinci.esercizio13;

/**
 * Abstract Data Type Definition.
 *
 * Dictionary of Objecst having Integer keys.
 *
 */
public interface DictionaryIntKeys {

    /**
     *
     * @param k chiave
     * @return elemento di chiave k; null se k non presente
     */
    ObjectWithIntKey get(int k);

    /**
     * Inserts object e into dictionary.
     *
     * @param e element to be added
     * @return previous element with same key; null otherwise
     */
    ObjectWithIntKey put(ObjectWithIntKey e);

    /**
     * Removes from dictionary element with key k
     *
     * @param k key of element to be removed
     * @return element removed; null if not existing
     */
    ObjectWithIntKey remove(int k);

    /**
     *
     * @return # elements in dictionary
     */
    int size();

    /**
     *
     * @return true se dizionario è vuoto; false
     */
    boolean isEmpty();

    /**
     *
     * @return element with lowest key; null if empty
     */
    ObjectWithIntKey min();

    /**
     *
     * @return element with highest key, null if empty
     */
    ObjectWithIntKey max();

}
