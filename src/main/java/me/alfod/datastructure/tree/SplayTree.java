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
        TreeNode<V> node = root;
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    node.setRight(valueNode);
                }
            } else if (v.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    node.setLeft(valueNode);
                }
            } else {
                return;
            }
        }
    }

    @Override
    public void del(V v) {
        TreeNode<V> node = root;
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                node = node.getRight();
            } else if (v.compareTo(node.getValue()) < 0) {
                node = node.getLeft();
            } else {
                TreeNode<V> suspect = node;
                if (suspect.getLeft() != null) {
                    while (suspect.getRight() != null) {
                        suspect = suspect.getRight();
                    }
                    node.setValue(suspect.getValue());
                    suspect.delete();
                } else if (suspect.getRight() != null) {
                    while (suspect.getLeft() != null) {
                        suspect = suspect.getLeft();
                    }
                    node.setValue(suspect.getValue());
                    suspect.delete();
                } else {
                    node.delete();
                }
                return;
            }
        }
    }

    @Override
    public boolean contain(V v) {
        TreeNode<V> node = root;
        exec(v, null, (TreeNode<V> n) -> {
            if (root != n) {
                if (n.isRight()) {
                    rotateLeft(n.getParent());
                } else {
                    rotateRight(n.getParent());
                }
            }
            return n;
        }, null);
//        while (node != null) {
//            if (v.compareTo(node.getValue()) > 0) {
//                node = node.getRight();
//            } else if (v.compareTo(node.getValue()) < 0) {
//                node = node.getLeft();
//            } else {
//                if (root != node) {
//                    if (node.isRight()) {
//                        rotateLeft(node.getParent());
//                    } else {
//                        rotateRight(node.getParent());
//                    }
//                }
//                return true;
//            }
//        }
        return false;
    }
}
