package me.alfod.datastructure.map;

import me.alfod.utils.Prime;

import java.io.Serializable;

public class TableMap<Key extends Serializable, Value> extends BaseHash<Key, Value> {

    @SuppressWarnings("unchecked")
    public TableMap() {
        table = new HashNode[defaultLength];
    }


    @SuppressWarnings({"unchecked", "null"})
    private HashNode<Key, Value>[] capacityExtension(HashNode[] hashNodes) {
        HashNode<Key, Value>[] newTable = new HashNode[Prime.getNext(2 * hashNodes.length)];
        Integer index;
        for (HashNode<Key, Value> node : table) {
            if (node != null && node.getActive()) {
                index = findNext(node.getK(), newTable);
                if (index != null) {
                    newTable[index] = node;
                } else {
                    newTable = capacityExtension(newTable);
                    break;
                }
            }
        }
        return newTable;
    }

    private void capacityExtension() {
        table = capacityExtension(table);
    }

    @Override
    public Value get(Key key) {
        int tmpIndex = findKey(key);
        if (table[tmpIndex] == null || !table[tmpIndex].getActive()) return null;
        else return table[tmpIndex].getV();
    }

    @Override
    @SuppressWarnings("null")
    public void add(Key key, Value value) {
        Integer index = findNext(key);
        if (index == null) {
            capacityExtension();
            add(key, value);
            return;
        }
        if (table[index] == null) {
            table[index] = new HashNode<>(key, value);
        } else {
            table[index].setK(key);
            table[index].setV(value);
            table[index].active();
        }

    }

    @Override
    public void set(Key key, Value value) {
        add(key, value);
    }

    @Override
    public void del(Key key) {
        Integer delIndex = findKey(key);
        if (table[delIndex] != null) table[delIndex].delete();
    }


    private Integer findKey(Key key) {
        return findKey(key, table);
    }

    private Integer findNext(Key key) {
        return findNext(key, table);
    }

    /**
     * @param key   key
     * @param nodes operated table
     * @return Integer that can be used to stored a new key-value
     */
    private Integer findNext(Key key, HashNode<Key, Value>[] nodes) {
        Integer index = findKey(key, nodes);
        if (index != null) return index;

        int localIndex = getIndex(key, nodes);
        int halfLength = nodes.length / 2;

        for (int i = 1; i < halfLength; i++) {
            localIndex += i * i;
            if (nodes[localIndex] == null
                    && !nodes[localIndex].getActive()) {
                return localIndex;
            }
        }

        return null;
    }

    /**
     * @param key   key
     * @param nodes operated table
     * @return null: no thar key in table; Integer: index of the key
     */
    private Integer findKey(Key key, HashNode<Key, Value>[] nodes) {
        int localIndex = getIndex(key);
        if (nodes[localIndex] == null) {
            return localIndex;
        }


        int halfLength = nodes.length / 2;
        for (int i = 1; i < halfLength; i++) {
            localIndex += i * i;
            if (nodes[localIndex] == null
                    || nodes[localIndex].getK().equals(key)) {
                return localIndex;
            }
        }

        return null;
    }

}
