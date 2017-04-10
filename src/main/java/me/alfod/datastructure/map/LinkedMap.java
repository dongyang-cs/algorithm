package me.alfod.datastructure.map;

import me.alfod.utils.Prime;

import java.io.Serializable;


public class LinkedMap<K extends Serializable, V> extends BaseHash<K, V> {

    private int index;


    @SuppressWarnings(value = "unchecked")
    public LinkedMap() {
        table = new HashNode[defaultLength];
    }

    public void add(K k, V v, HashNode<K, V>[] nodes) {
        index = getIndex(k);
        if (nodes[index] == null) {
            nodes[index] = new HashNode<>(k, v);
        } else {
            int count = 0;
            HashNode<K, V> node = table[index];
            while (node.getNext() != null) {
                if (node.getK().equals(k)) {
                    node.setV(v);
                    return;
                }
                node = node.getNext();
                count++;
            }
            if (node.getK().equals(k)) {
                node.setV(v);
            } else {
                node.setNext(new HashNode<>(k, v));
            }
            if (count > Math.sqrt(table.length)) {
                extend();
            }
        }
    }

    @Override
    public void add(K k, V v) {
        add(k, v, table);
    }

    @SuppressWarnings("unchecked")
    private HashNode<K, V>[] extend(HashNode<K, V>[] hashNodes) {
        HashNode<K, V>[] newTable = new HashNode[Prime.getNext(2 * hashNodes.length)];
        for (HashNode<K, V> node : table) {
            while (node != null) {
                add(node.getK(), node.getV(), newTable);
                node = node.getNext();
            }
        }
        return newTable;
    }

    private void extend() {
        table = extend(table);
    }

    @Override
    public V get(K k) {
        index = getIndex(k);
        HashNode<K, V> node = table[index];
        while (node != null) {
            if (node.getK().equals(k)) {
                return node.getV();
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public void del(K k) {
        index = getIndex(k);
        HashNode<K, V> node = table[index];
        if (node == null) {
            return;
        }
        if (node.getK().equals(k)) {
            table[index] = (null == node.getNext() ? null : node.getNext());
            return;
        }
        while (node.getNext() != null) {
            if (node.getNext().getK().equals(k)) {
                if (node.getNext().getNext() == null) {
                    node.setNext(null);
                } else {
                    node.setNext(node.getNext().getNext());
                }
                return;
            }
            node = node.getNext();
        }

    }

    @Override
    public void set(K k, V v) {
        add(k, v);
    }


}


