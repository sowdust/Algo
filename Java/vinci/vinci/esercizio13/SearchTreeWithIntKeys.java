package vinci.esercizio13;

/**
 * Implements a DictionaryWithIntKeys using ObjectsWithIntKeys
 */
public class SearchTreeWithIntKeys implements DictionaryIntKeys {

    Node root;

    public SearchTreeWithIntKeys() {
        this.root = null;
    }

    /**
     * Calls the static method passing root as node parameter (if not null)
     * because Java cannot pass references
     *
     * @param k chiave
     * @return elemento di chiave k; null se k non presente
     */
    @Override
    public ObjectWithIntKey get(int k) {
        if (null == root) {
            return null;
        }
        return get(k, root);
    }

    /**
     * Inserts object e into the search tree. Maintains the order. Implemented
     * through a recursive static method.
     *
     * @param e element to be added
     * @return previous element with same key; null otherwise
     */
    @Override
    public ObjectWithIntKey put(ObjectWithIntKey e) {
        if (root == null) {
            root = new Node(e);
            return null;
        }

        return put(e, root);
    }

    /**
     * Removes from BST element with key k
     *
     * @param k key of element to be removed
     * @return element removed; null if not existing
     */
    @Override
    public ObjectWithIntKey remove(int k) {

        if (root == null) {
            return null;
        }

        if (k < root.key()) {
            if (root.left != null) {
                return remove(k, root, true, root.left);
            }
            return null;
        }

        if (k > root.key()) {
            if (root.right != null) {
                return remove(k, root, false, root.right);
            }
            return null;
        }

        ObjectWithIntKey t = root.e;

        if (root.right == null) {
            root = root.left;
            return t;
        }

        if (root.left == null) {
            root = root.right;
            return t;
        }

        root.e = extractMin(root.right);
        return t;
    }

    /**
     * Must be implemented as a procedure instead of simply returning a value
     * that gets updated during each procedure. This is beacuase all main
     * methods are static and don't have a reference to the original tree.
     *
     * @return # nodes in the BST
     */
    @Override
    public int size() {
        if (null == root) {
            return 0;
        }
        return root.size();
    }

    /**
     *
     * @return true if BST empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     *
     * @return element with lowest key, null if empty
     */
    @Override
    public ObjectWithIntKey min() {
        if (root == null) {
            return null;
        }
        return findMin(root);
    }

    /**
     *
     * @return element with highest key, null if empty
     */
    @Override
    public ObjectWithIntKey max() {
        if (root == null) {
            return null;
        }
        return findMax(root);
    }

    private static ObjectWithIntKey get(int k, Node n) {
        if (n.key() == k) {
            return n.e;
        }
        if (k < n.key() && n.left != null) {
            return get(k, n.left);
        } else if (k > n.key() && n.right != null) {
            return get(k, n.right);
        } else {
            return null;
        }
    }

    private static ObjectWithIntKey put(ObjectWithIntKey e, Node n) {
        if (e.key() == n.key()) {
            ObjectWithIntKey temp = n.e;
            n.e = e;
            return temp;
        }
        if (e.key() < n.key()) {
            if (n.left == null) {
                n.setLeft(e);
                return null;
            }
            return put(e, n.left);
        }

        // if(e.key() > n.key())
        if (n.right == null) {
            n.setRight(e);
            return null;
        }
        return put(e, n.right);
    }

    /**
     * Auxiliary procedure. Returns element with min key removing the node that
     * hosted it.
     */
    private static ObjectWithIntKey extractMin(Node n) {
        ObjectWithIntKey t = findMin(n);
        deleteMin(n);
        return t;
    }

    /**
     * Auxiliary procedure. Returns element with min key. PRECOND: n is not null
     */
    private static ObjectWithIntKey findMin(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n.e;
    }

    /**
     * Auxiliary procedure. Returns element with max key. PRECOND: n is not null
     */
    private static ObjectWithIntKey findMax(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n.e;
    }

    /**
     * Auxiliary procedure. Deletes node with min key. PRECOND: n is not null
     */
    private static Node deleteMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = deleteMin(n.left);
        return n;
    }

    /**
     * Static function that recursively removes a node with given key.
     *
     * @param k
     * @param parent
     * @param isLeft
     * @param n
     * @return
     */
    private static ObjectWithIntKey remove(int k, Node parent, boolean isLeft, Node n) {
        if (n == null) {
            return null;
        }

        if (k < n.key()) {
            if (n.left != null) {
                return remove(k, n, true, n.left);
            }
            return null;
        }

        if (k > n.key()) {
            if (n.right != null) {
                return remove(k, n, false, n.right);
            }
            return null;
        }

        ObjectWithIntKey t = n.e;

        if (n.right == null) {
            if (isLeft) {
                parent.left = n.left;
            } else {
                parent.right = n.left;
            }
            return t;
        }

        if (n.left == null) {
            if (isLeft) {
                parent.left = n.right;
            } else {
                parent.right = n.right;
            }
            return t;
        }

        n.e = extractMin(n.right);
        return t;
    }

    class Node {

        Node left;
        Node right;
        ObjectWithIntKey e;

        Node(ObjectWithIntKey e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }

        Node(ObjectWithIntKey e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

        int key() {
            return e.key();
        }

        void setLeft(ObjectWithIntKey n) {
            this.left = new Node(n);
        }

        void setRight(ObjectWithIntKey n) {
            this.right = new Node(n);
        }

        int size() {
            int l = (left == null) ? 0 : left.size();
            int r = (right == null) ? 0 : right.size();
            return 1 + Math.max(l, r);
        }

    }

}
