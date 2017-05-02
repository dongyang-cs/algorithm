package me.alfod.datastructure.tree;

import me.alfod.datastructure.tree.TreeNode.Color;

public class RBTree<V extends Comparable<? super V>> extends BaseTree<V> {

    private TreeNode<V> root;

    @Override
    public void add(final V v) {
        if (root == null) {
            root = new TreeNode<>(v, Color.BLACK);
            return;
        }
        TreeNode<V> node = root;
        TreeNode<V> valueNode = new TreeNode<>(v);
        while (node != null) {
            if (node.getValue().compareTo(v) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                    continue;
                }
                valueNode.setColor(TreeNode.Color.RED);
                node.setRight(valueNode);
                if (node.getColor() == TreeNode.Color.RED) {
                    colorCheck(node);
                }                return;
            }
            if (node.getValue().compareTo(v) < 0) {
                node = node.getLeft();
            }
        }

    }

    private void colorCheck(TreeNode<V> node) {

        boolean isRightRed = (node.getRight() == null || node.getRight().getColor() == Color.RED);
        boolean isLeftRed = (node.getLeft() == null || node.getLeft().getColor() == Color.RED);
        if (node.getColor() == Color.RED
                && (isRightRed
                || isLeftRed)) {

            if (node.isRightOfParent()) {
                if (node.getParent().getLeft() == null) {
                    node = rightReduce(node.getParent());
                    node.setColor(Color.BLACK);
                    node.getRight().setColor(Color.RED);
                    node.getLeft().setColor(Color.RED);
                }

            }
        }
    }

    @Override
    public void del(V v) {

    }

    @Override
    public boolean contain(V v) {
        return false;
    }


}
