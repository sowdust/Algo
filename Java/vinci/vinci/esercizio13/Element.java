package vinci.esercizio13;

class Element implements ObjectWithIntKey {

    String val;
    int key;

    Element(int key, String elem) {
        this.key = key;
        this.val = elem;
    }

    @Override
    public int key() {
        return this.key;
    }

}
