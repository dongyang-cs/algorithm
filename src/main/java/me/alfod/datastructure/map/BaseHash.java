package me.alfod.datastructure.map;

/**
 * Created by arvin
 */
public abstract class BaseHash<K, V> implements Map<K, V> {
    protected final int defaultLength = 11;
    private final int hashLengthLimit = 10;
    protected HashNode<K, V>[] table;


    protected final int getIndex(K k) {
        return (int) (k.hashCode() % table.length);
    }

    protected final int getIndex(K k, HashNode<K, V>[] hashNodes) {
        return (int) (k.hashCode() % hashNodes.length);
    }

}
