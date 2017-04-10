package me.alfod.datastructure.tree;

import me.alfod.algorithm.sort.Quick;

public class AVL<V extends Comparable<? super V>> extends BaseTree<V> {
    public final int length;
    public final int height;
    private TreeNode<V> root;

    public AVL(V[] values) {
        root = balanceInit(values);
        length = values.length;
        height = getHeight(root, 1);
    }

    @Override
    public void del(V v) {

    }

    @Override
    public boolean contain(V v) {
        return true;
    }

    public void add(V value) {
        TreeNode<V> node = root;
        while (true) {
            if (value.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null)
                    node = node.getRight();
                else {
                    node.setRightValue(value);

                    break;
                }
            } else if (value.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null)
                    node = node.getLeft();
                else {
                    node.setLeftValue(value);
                    break;
                }
            } else break;
        }
    }

    private void checkBalance(TreeNode<V> node) {
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        if (leftHeight - rightHeight > 1) {
            leftRotate(node);

        }
        if (rightHeight - leftHeight > 1) {
            rightROtate(node);
        }
    }

    public void add(V[] values) {
        for (V i : values) {
            add(i);
        }
    }


    public TreeNode balanceInit(V[] values) {
        values = Quick.sort(values);
        return balanceInit(values, values.length / 2, values.length - 1, 0);
    }


    private TreeNode balanceInit(V[] values, int index, int rightBorder, int leftBorder) {
        TreeNode<V> node;
        if (index < 0 || index >= values.length) return null;
        else node = new TreeNode<>(values[index]);

        if (rightBorder - index > 1) {
            node.setRight(balanceInit(values, (rightBorder + index + 1) / 2, rightBorder, index + 1));
        } else if (rightBorder - index == 1) {
            node.setRight(new TreeNode<>(values[rightBorder]));
        }

        if (index - leftBorder > 1) {
            node.setLeft(balanceInit(values, (leftBorder + index - 1) / 2, index - 1, leftBorder));
        } else if (index - leftBorder == 1) {
            node.setLeft(new TreeNode<>(values[leftBorder]));
        }
        return node;
    }

}
