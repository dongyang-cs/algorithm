package me.alfod.datastructure.tree;

/**
 * Created by Arvin Alfod on 2017/5/14.
 */
public class SplayTree<V extends Comparable<? super V>> extends BaseTree<V> {


    @Override
    public void add(V v) {
        if (root == null) {
            root = new TreeNode<>(v);
            return;
        }
        TreeNode<V> valueNode = new TreeNode<>(v);
        findThenApply(v, (TreeNode<V> node) -> {
            node.setRight(valueNode);
        }, (TreeNode<V> node) -> {
            node.setValue(v);
        }, (TreeNode<V> node) -> {
            node.setLeft(valueNode);
        });
    }

    @Override
    public void del(V v) {
        findEqualThenApply(v, (TreeNode<V> node) -> {
            TreeNode<V> suspect = node;
            if (suspect.getLeft() != null) {
                suspect = findUniformRight(node.getLeft());
                node.setValue(suspect.getValue());
                suspect.delete();
            } else if (suspect.getRight() != null) {
                suspect = findUniformLeft(node.getRight());
                node.setValue(suspect.getValue());
                suspect.delete();
            } else {
                node.delete();
            }
        });
    }


    @Override
    public boolean contain(V v) {
        return findEqualThenApply(v, (TreeNode<V> n) -> {
            if (root != n) {
                if (n.isRight()) {
                    rotateLeft(n.getParent());
                } else {
                    rotateRight(n.getParent());
                }
            }
        }) != null;
    }
}
