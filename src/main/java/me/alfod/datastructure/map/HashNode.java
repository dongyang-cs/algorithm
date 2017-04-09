package me.alfod.datastructure.map;


public class HashNode<Key, Value> {
    private HashNode<Key, Value> next;
    private Key key;
    private Value value;
    private boolean active = true;

    public HashNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public HashNode<Key, Value> getNext() {
        return next;
    }

    public void setNext(HashNode<Key, Value> next) {
        this.next = next;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
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
        return "TreeNode{ key= " + key +
                ", value= " + value +
                ", active= " + active + " }";
    }
}
