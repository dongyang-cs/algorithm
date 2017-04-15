package me.alfod.datastructure.tree;

/**
 * Created by arvin
 */
public interface Tree<V> {
    void add(V v);

    void add(V[] v);

    void del(V v);

    boolean contain(V v);
}
