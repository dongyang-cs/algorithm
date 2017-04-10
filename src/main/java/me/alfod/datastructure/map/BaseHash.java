package me.alfod.datastructure.map;

/**
 * Created by arvin
 */
public abstract class BaseHash<K, V> implements Map<K, V> {
    protected final int defaultLength = 11;
    private final int hashLengthLimit = 10;
    protected HashNode<K, V>[] table;

    protected final long hash(K k) {
        if (k instanceof String) {
            int address = 0;

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
            else return 1L;
        } else if (k instanceof Long) {
            return Math.abs((Long) k);
        } else {
            return Math.abs(k.hashCode());
        }
    }

    protected final int getIndex(K k) {
        return (int) (hash(k) % table.length);
    }

    protected final int getIndex(K k, HashNode<K, V>[] hashNodes) {
        return (int) (hash(k) % hashNodes.length);
    }

}
