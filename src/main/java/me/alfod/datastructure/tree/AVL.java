package me.alfod.datastructure.tree;

import me.alfod.algorithm.sort.Quick;

public class AVL<V extends Comparable<? super V>> extends BaseTree {
    public final int length;
    public final int height;
    private TreeNode root;

    public AVL(Integer[] integers) {
        root = balanceInit(integers);
        length = integers.length;
        height = getHeight(root, 1);
    }


    public void add(V integer) {
        TreeNode<V> node = root;
        while (true) {
            if (integer.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null)
                    node = node.getRight();
                else {
                    node.setRightValue(integer);
                    break;
                }
            } else if (integer.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null)
                    node = node.getLeft();
                else {
                    node.setLeftValue(integer);
                    break;
                }
            } else break;
        }
    }

    public void add(V[] integers) {
        for (V i : integers) {
            add(i);
        }
    }


    public TreeNode balanceInit(Integer[] integers) {
        integers = Quick.sort(integers);
        return balanceInit(integers, integers.length / 2, integers.length - 1, 0);
    }


    private TreeNode balanceInit(Integer[] integers, int index, int rightBorder, int leftBorder) {
        TreeNode node;
        if (index < 0 || index >= integers.length) return null;
        else node = new TreeNode(integers[index]);

        if (rightBorder - index > 1) {
            node.setRight(balanceInit(integers, (rightBorder + index + 1) / 2, rightBorder, index + 1));
        } else if (rightBorder - index == 1) {
            node.setRight(new TreeNode(integers[rightBorder]));
        }

        if (index - leftBorder > 1) {
            node.setLeft(balanceInit(integers, (leftBorder + index - 1) / 2, index - 1, leftBorder));
        } else if (index - leftBorder == 1) {
            node.setLeft(new TreeNode(integers[leftBorder]));
        }
        return node;
    }
    
}
