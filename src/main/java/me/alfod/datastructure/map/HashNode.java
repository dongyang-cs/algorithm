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

    @Override
    public int hashCode() {
        if (k instanceof String) {
            int address = 0;
            int hashLengthLimit = 12;
            char[] chars = ((String) k).toCharArray();
            int limit = (hashLengthLimit > chars.length ? chars.length : hashLengthLimit);
            for (int i = 0; i < limit; i++) {
                address += chars[i] * (32 << i);
            }
            return Math.abs(address);
        } else if (k instanceof Integer) {
            return Math.abs((Integer) k);
        } else if (k instanceof Boolean) {
            if ((Boolean) k) return 0;
            else return 1;
        } else if (k instanceof Long) {
            return Math.abs(((Long) k).intValue());
        } else {
            return Math.abs(k.hashCode());
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HashNode) {
            if (((HashNode) obj).getK().equals(this.k)) {
                return true;
            }
        }
        return false;
    }
}
