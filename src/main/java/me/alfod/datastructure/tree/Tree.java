package me.alfod.datastructure.tree;

/**
 * Created by arvin
 */
public interface Tree<V extends Comparable<? super V>> {
    void add(final V v);

    void del(final V v);

    boolean contain(final V v);

    TreeNode<V> getRoot();
}
