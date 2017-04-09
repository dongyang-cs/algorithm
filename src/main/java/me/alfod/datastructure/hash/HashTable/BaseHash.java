package me.alfod.datastructure.hash.HashTable;

/**
 * Created by arvin
 */
public abstract class BaseHash<Key, Value> {
    protected final int defaultLength = 11;
    private final int hashLengthLimit = 10;
    protected HashNode<Key, Value>[] table;

    protected final long hash(Key key) {
        if (key instanceof String) {
            int address = 0;

            char[] chars = ((String) key).toCharArray();
            int limit = (hashLengthLimit > chars.length ? chars.length : hashLengthLimit);
            for (int i = 0; i < limit; i++) {
                address += chars[i] * (32 << i);
            }
            return Math.abs(address);
        } else if (key instanceof Integer) {
            return Math.abs((Integer) key);
        } else if (key instanceof Boolean) {
            if ((Boolean) key) return 0;
            else return 1L;
        } else if (key instanceof Long) {
            return Math.abs((Long) key);
        } else {
            return Math.abs(key.hashCode());
        }
    }

    protected final int getIndex(Key key) {
        return (int) (hash(key) % table.length);
    }

    protected final int getIndex(Key key, HashNode<Key, Value>[] hashNodes) {
        return (int) (hash(key) % hashNodes.length);
    }

}
