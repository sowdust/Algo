package algo.esercizio3;

import static java.lang.Math.*;
import static java.lang.System.out;

/**
 * definisce alcuni metodi per gli alberi binari
 *
 * @author	mattia vinci
 * @version 0.9
 */
public class BinTreeUtil {

    private BinTreeUtil() {
    }

    /**
     * inserisce elem nell'albero t nel punto specificato dalla stringa path e
     * restituisce l'albero t modificato; se la stringa path conduce a un nodo
     * (anziché a un figlio vuoto di un nodo), elem sostituisce il valore del
     * nodo che è stato raggiunto.
     *
     * @param	elem	valore da aggiungere
     * @param	t	albero sul quale operare
     * @param	path	percorso secondo il quale posizionare il nuovo nodo
     * @return	puntatore all'albero modificato
     */
    public static BinTree add(int elem, BinTree t, String path) throws
            RuntimeException {

        if (null == path) {
            throw new RuntimeException("stringa non inizializzata");
        }
        if (null == t) {
            return new BinTree(elem);
        }
        if (path.length() == 0 || path.equals(" ")) {
            t.element = elem;
            return t;
        }
        if (path.length() == 1) {
            path += " ";
        }

        if (path.substring(0, 1).equals("L")) {
            t.left = add(elem, t.left, path.substring(1));
        }
        if (path.substring(0, 1).equals("R")) {
            t.right = add(elem, t.right, path.substring(1));
        }

        return t;

    }

    // metodo già dato
    public static void display(BinTree t) {
        display(t, 0);
    }

    private static void display(BinTree t, int k) {
        if (t != null) {
            display(t.right, k + 1);
            for (int i = 0; i < k; i++) {
                out.print("   ");
            }
            out.println(t.element);
            display(t.left, k + 1);
        }
    }

    /**
     * pre-cond: t non e' nullo da' come risultato true se t e' una foglia,
     * false altrimenti
     *
     * @param t albero su cui effettuare il controllo
     * @return true se t è una foglia, false altrimenti
     */
    public static boolean isLeaf(BinTree t) {
        return t.left == null && t.right == null;
    }

    /**
     * Stampa t sulla consolle L'ordine dei valori corrisponde ai valori che
     * vengono incontrati durante una visita anticipata (preorder).
     *
     * @param t Albero da stampare
     */
    public static void preorderPrint(BinTree t) {
        if (t == null) {
            return;
        }
        System.out.print(t.element + " ");
        preorderPrint(t.left);
        preorderPrint(t.right);
    }

    /**
     * Stampa t sulla console. L'ordine dei valori deve corrispondere ai valori
     * che vengono incontrati durante una visita simmetrica (inorder).
     *
     * @param t Albero da stampare
     */
    public static void inorderPrint(BinTree t) {
        if (t == null) {
            return;
        }
        inorderPrint(t.left);
        System.out.print(t.element + " ");
        inorderPrint(t.right);
    }

    /**
     * Stampa i valori nei nodi in t sulla console. L'ordine dei valori deve
     * corrispondere ai valori che vengono incontrati durante una visita
     * posticipata (postorder).
     *
     * @param t Albero da stampare
     */
    public static void postorderPrint(BinTree t) {
        if (t == null) {
            return;
        }
        postorderPrint(t.left);
        postorderPrint(t.right);
        System.out.print(t.element + " ");
    }

    /**
     * restituisce il numero dei nodi in t
     *
     * @param t Albero binario di input
     * @return numero di nodi in t
     */
    public static int size(BinTree t) {
        if (t == null) {
            return 0;
        }
        return 1 + size(t.left) + size(t.right);
    }

    /**
     * restituisce la somma dei valori di tutti gli elementi Restituisce
     * Integer.MIN_VALUE se l'albero è vuoto
     *
     * @param t Albero binario di input
     * @return	somma degli elementi. MIN_VALUE se albero vuoto
     */
    public static int sum(BinTree t) {
        if (t == null) {
            return (Integer.MIN_VALUE);
        }
        return _sum(t);
    }

    static int _sum(BinTree t) {
        if (t == null) {
            return 0;
        }
        return t.element + _sum(t.right) + _sum(t.left);
    }

    /**
     * restituisce l'altezza dell'albero
     *
     * @param t Albero binario di input
     * @return altezza albero (se vuoto = -1)
     */
    public static int height(BinTree t) {
        if (t == null) {
            return -1;
        }
        return (1 + max(height(t.left), height(t.right)));
    }

    /**
     * restituisce il massimo degli elementi; se l'albero e' vuoto da' come
     * risultato Integer.MIN_VALUE
     *
     * @param t Albero binario di input
     * @return valore massimo elemento; Integer.MIN_VALUE se vuoto
     *
     */
    public static int maxElem(BinTree t) {
        if (t == null) {
            return Integer.MIN_VALUE;
        }
        if (isLeaf(t)) {
            return t.element;
        }
        return max(t.element, max(maxElem(t.right), maxElem(t.left)));
    }

    /**
     * PRECOND: gli elementi contenuti nei nodi sono tutti >= 0;
     *
     * Restituisce il massimo dei pesi dei cammini dalla radice a una foglia
     * (non nulla) dove il peso del cammino e' la somma dei valori degli
     * elementi dei nodi lungo il cammino, e dove maxPath(null) vale 0
     *
     * @param t Albero di input
     * @return massimo dei pesi dei cammini da radice a foglia, 0 se t è nullo
     */
    public static int maxPositivePath(BinTree t) throws RuntimeException {
        if (null == t) {
            return 0;
        }
        if (t.element < 0) {
            throw new RuntimeException("pre-condizione violata");
        }
        if (isLeaf(t)) {
            return t.element;
        }
        return Math.max(maxPositivePath(t.left), maxPositivePath(t.right)) + t.element;
    }

    /**
     * Restituisce il massimo dei pesi dei cammini dalla radice a una foglia
     * (non nulla) (ci possono essere elementi negativi), dove il peso del
     * cammino e' la somma dei valori degli elementi dei nodi lungo il cammino,
     * e dove maxPath(null) vale Integer.MIN_VALUE
     *
     * @param t Albero binario di input
     * @return massimo dei pesi dei cammini da radice a foglia, MIN_VALUE se t è
     * nullo
     */
    public static int maxPath(BinTree t) {
        if (null == t) {
            return Integer.MIN_VALUE;
        }
        if (isLeaf(t)) {
            return t.element;
        }
        return Math.max(maxPath(t.left), maxPath(t.right)) + t.element;
    }

    /**
     * Restituisce il numero delle foglie
     *
     * @param t Albero binario di input
     * @return numero di foglie in t
     */
    public static int numberOfLeaves(BinTree t) {
        if (t == null) {
            return 0;
        }
        if (isLeaf(t)) {
            return 1;
        }
        return (numberOfLeaves(t.right) + numberOfLeaves(t.left));
    }

    /**
     * modifica l'albero incrementando di 1 i valori di tutti gli elementi
     *
     * @param t Albero binario da modificare
     */
    public static void increment(BinTree t) {
        if (t == null) {
            return;
        }
        t.element++;
        increment(t.left);
        increment(t.right);
    }

    /**
     * modifica l'albero incrementando di 1 i valori di tutte le foglie
     *
     * @param t Albero binario da modificare
     */
    public static void incrementLeaves(BinTree t) {
        if (t == null) {
            return;
        }
        if (isLeaf(t)) {
            t.element++;
        } else {
            incrementLeaves(t.right);
            incrementLeaves(t.left);
        }
    }

    /**
     * restituisce come risultato true se l'elemento x e' presente in t, false
     * altrimenti
     *
     * @param	x	elemento da cercare
     * @param	t	albero su cui effettuare la ricerca
     * @return	valore trovato?
     */
    public static boolean search(int x, BinTree t) {
        boolean l = false, r = false;
        if (t == null) {
            return false;
        }
        if (t.element == x) {
            return true;
        }
        if (t.left != null) {
            l = search(x, t.left);
        }
        if (t.right != null) {
            r = search(x, t.right);
        }
        return (l | r);

    }

    /**
     * Metodo per controllare se l'albero è pieno. Si chiede se il numero di
     * nodi di ogni sottoalbero sia uguale a 2^(altezza del sottoalbero).
     *
     * @return true se l'albero this è completo; altrimenti false.
     */
    public boolean isComplete(BinTree t) {
        return isComplete(t, this.height(t));
    }

    private boolean isComplete(BinTree nd, int h) {
        return this.numNodi(nd) == (power2(h) - 1);
    }

    /**
     * Metodo di utilità per calcolare piccole potenze di 2.
     *
     * @param exp l'esponente della potenza di 2 da calcolare
     * @return 2^exp.
     */
    public int power2(int exp) {
        int result = 2;
        for (int i = 0; i < exp; i++) {
            result *= 2;
        }
        return result;
    }

    /**
     * Metodo per calcolare il numero di nodi di un albero o sottoalbero.
     *
     * @param nd il nodo-radice da cui contare il numero di nodi
     * @return il numero di nodi del sottoalbero.
     */
    public int numNodi(BinTree nd) {
        return nd == null ? 0 : numNodi(nd.left) + numNodi(nd.right) + 1;
    }

    /**
     * restituisce il (o un) sottoalbero di t avente come radice x, se esiste
     * (senza creare nuovi nodi o un nuovo albero); se invece x non e' presente
     * in t, da' come risultato null l'implementazione restituisce il primo nodo
     * avente x in preorder
     *
     * @param	x	intero da cercare
     * @param	t	albero su cui effettuare la ricerca
     * @return	puntatore al primo nodo contenente x, null se non c'è x
     */
    public static BinTree find(int x, BinTree t) {
        if (t == null) {
            return null;
        }
        if (t.element == x) {
            return t;
        }

        if (t.left != null) {
            BinTree k = find(x, t.left);
            if (k != null) {
                return k;
            }
        }
        return find(x, t.right);
    }

    /**
     * restituisce true se t1 e t2 hanno la stessa struttura e gli stessi valori
     * false altrimenti
     *
     * @param t1 Albero da confrontare con t2
     * @param t2 Albero da confrontare con t1
     * @return true se t1 è uguale (come spec. sopra) a t2, false altrimenti
     */
    public static boolean areEqual(BinTree t1, BinTree t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.element != t2.element) {
            return false;
        }
        return (areEqual(t1.left, t2.left)
                && areEqual(t1.right, t2.right));
    }

    /**
     * crea e restituisce un nuovo albero copia di t
     *
     * @param	t	puntatore ad albero da copiare
     * @return	puntatore a nuovo albero uguale a t
     *
     */
    public static BinTree copy(BinTree t) {
        if (t == null) {
            return null;
        }
        BinTree r = new BinTree(t.element);
        _copy(t, r);
        return r;
    }

    // funzione ausuliaria per copy()
    static void _copy(BinTree t, BinTree r) {
        if (t == null) {
            return;
        }
        if (t.left != null) {
            r.left = new BinTree(t.left.element);
            _copy(t.left, r.left);
        }
        if (t.right != null) {
            r.right = new BinTree(t.right.element);
            _copy(t.right, r.right);
        }

    }

    /**
     * costruisce e restituisce un nuovo albero speculare di t
     *
     * @param t Albero bin di input
     * @return Albero speculare a t
     */
    public static BinTree mirrorCopy(BinTree t) {
        if (t == null) {
            return null;
        }
        BinTree r = new BinTree(t.element);
        _mirror_copy(t, r);
        return r;
    }

    // funzione ausiliaria per mirrorCopy
    public static void _mirror_copy(BinTree t, BinTree r) {
        if (t == null) {
            return;
        }
        if (t.left != null) {
            r.right = new BinTree(t.left.element);
            _mirror_copy(t.left, r.right);
        }
        if (t.right != null) {
            r.left = new BinTree(t.right.element);
            _mirror_copy(t.right, r.left);
        }

    }

    /**
     * modifica l'albero t, senza creare nuovi nodi, facendolo diventare il suo
     * speculare
     *
     * @param t Albero da modificare
     */
    public static void mirrorInPlace(BinTree t) {
        if (t == null) {
            return;
        }
        BinTree temp = t.left;
        t.left = t.right;
        t.right = temp;
        mirrorInPlace(t.left);
        mirrorInPlace(t.right);
    }

    /**
     * restituisce la somma dei valori di tutti i nodi di un sottoalbero di t di
     * radice x, includendo x stesso nella somma Se esistono piu' elementi di
     * valore x, restituisce il risultato relativo al primo elemento x che trova
     * Se il valore x non compare nell'albero, restituisce Integer.MIN_VALUE
     * Nota: puo' richiamare altri metodi di questa classe
     *
     * @param	x	elemento da cercare
     * @param t Albero in cui cercare x
     * @return	somma del valore degli elementi del sottoalbero di x + x stesso
     *
     */
    public static int sumDescendants(int x, BinTree t) {
        return sum(find(x, t));
    }

    /*
     * costruisce e restituisce un nuovo albero
     * uguale a t fino al livello h-1 incluso,
     * ma privo di tutti i nodi che si trovano
     * a un livello maggiore o uguale ad h,
     * senza modificare l'albero di partenza t.
     */
    public static BinTree trimmed(int h, BinTree t) {
        if (null == t || h < 1) {
            return null;
        }
        return new BinTree(t.element, trimmed(h - 1, t.left), trimmed(h - 1, t.right));
    }

    /**
     * modifica l'albero t eliminando tutti i nodi di livello >= h, e
     * restituisce l'albero stesso, modificato
     */
    static BinTree trim(int h, BinTree t) {
        if (null == t || h < 1) {
            return null;
        }
        if (1 == h) {
            t.left = null;
            t.right = null;
            return t;
        }
        t.left = trim(h - 1, t.left);
        t.right = trim(h - 1, t.right);
        return t;
    }

    /**
     * scrive sulla console i nodi-cardine dell'albero (vedi libro di testo pag.
     * 95): chiamiamo cardine un nodo tale che il suo livello nell'albero sia
     * uguale all'altezza del sottoalbero di cui esso e' radice, assumendo che
     * un (sotto)albero costituito da un solo nodo abbia altezza 0 (e quindi un
     * sottoalbero vuoto abbia altezza -1).
     */
    public static void printCentralNodes(BinTree t) {
        if (null == t) {
            return;
        }
        central_nodes(t, 0);
    }

    public static void central_nodes(BinTree t, int h) {
        if (height(t) == h) {
            System.out.println(t.element);
            return;
        }
        if (t.left != null) {
            central_nodes(t.left, h + 1);
        }
        if (t.right != null) {
            central_nodes(t.right, h + 1);
        }
    }
}
