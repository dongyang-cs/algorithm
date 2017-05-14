package me.alfod.datastructure.tree;

import java.util.function.Function;

/**
 * Created by arvin
 */
public abstract class BaseTree<V extends Comparable<? super V>> implements Tree<V> {

    protected TreeNode<V> root;

    public static int getHeight(final TreeNode node) {
        return getHeight(node, 1);
    }

    protected static int getHeight(final TreeNode node, final int height) {
        if (node == null) return height - 1;
        int rightHeight = getHeight(node.getRight(), height + 1);
        int leftHeight = getHeight(node.getLeft(), height + 1);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

    }

    public TreeNode<V> getRoot() {
        return root;
    }

    protected TreeNode<V> rotateLeft(TreeNode<V> node) {
        TreeNode<V> head = node.getRight();
        if (node.getParent() != null) {
            if (node.isRight()) {
                node.getParent().setRight(head);
            } else {
                node.getParent().setLeft(head);
            }
        } else {
            head.setParent(null);
            root = head;
        }
        node.setRight(head.getLeft());
        head.setLeft(node);

        return head;
    }

    protected TreeNode<V> rotateRight(TreeNode<V> node) {
        TreeNode<V> head = node.getLeft();
        if (node.getParent() != null) {
            if (node.isRight()) {
                node.getParent().setRight(head);
            } else {
                node.getParent().setLeft(head);
            }
        } else {
            head.setParent(null);
            root = head;
        }
        node.setLeft(head.getRight());
        head.setRight(node);
        return head;
    }

    protected TreeNode<V> doubleRotateLeft(TreeNode<V> node) {
        rotateRight(node.getRight());
        return rotateLeft(node);
    }

    protected TreeNode<V> doubleRotateRight(TreeNode<V> node) {
        rotateLeft(node.getLeft());
        return rotateRight(node);
    }

    protected void exec(V v, Function<TreeNode<V>, TreeNode<V>> greater,
                        Function<TreeNode<V>, TreeNode<V>> equal,
                        Function<TreeNode<V>, TreeNode<V>> less) {
        TreeNode<V> node = root;
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                node = node.getRight();
                if (greater != null) {
                    greater.apply(node);
                }
            } else if (v.compareTo(node.getValue()) == 0) {
                if (equal != null) {
                    equal.apply(node);
                }
            } else if (v.compareTo(node.getValue()) < 0) {
                node = node.getLeft();
                if (less != null) {
                    less.apply(node);
                }
            }
        }
    }

    public void add(V[] vs) {
        for (int i = 0; i < vs.length; i++) {
            add(vs[i]);
        }
    }

}
