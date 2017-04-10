package me.alfod.datastructure.map;


public class HashNode<K, V> {
    private HashNode<K, V> next;
    private K k;
    private V v;
    private boolean active = true;

    public HashNode(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void delete() {
        setActive(false);
    }

    public void active() {
        setActive(true);
    }

    @Override
    public String toString() {
        return "TreeNode{ k= " + k +
                ", v= " + v +
                ", active= " + active + " }";
    }
}
