package me.alfod.datastructure.tree;

import me.alfod.datastructure.tree.TreeNode.Color;
import me.alfod.utils.Print;

public class RBTree<V extends Comparable<? super V>> extends BaseTree<V> {


    @Override
    public void add(final V v) {
        if (root == null) {
            root = new TreeNode<>(v, Color.BLACK);
            return;
        }
        TreeNode<V> node = root;
        TreeNode<V> valueNode = new TreeNode<>(v, Color.RED);
        while (node != null) {
            if (v.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                    continue;
                }
                node.setRight(valueNode);
                if (node.getColor() == Color.RED) {
                    colorCheck(valueNode);
                }
                return;
            }
            if (v.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    continue;
                }
                node.setLeft(valueNode);
                if (node.getColor() == Color.RED) {
                    colorCheck(valueNode);
                }
                return;
            }
            return;
        }

    }

    private void colorCheck(TreeNode<V> node) {
        if (node == null || node.getColor() == Color.BLACK) {
            return;
        }
        Print.println(node);
        TreeNode<V> parent = node.getParent();
        if (parent == null) {
            Print.println("parent null");
            node.setColor(Color.BLACK);
            root = node;
            return;
        }
        if (parent.getColor() == Color.BLACK) {
            return;
        }
        TreeNode<V> grandPa = parent.getParent();
        if (grandPa == null) {
            parent.setColor(Color.BLACK);
            root = parent;
            return;
        }
        if (parent.isRightOfParent()) {
            TreeNode<V> uncle = grandPa.getLeft();
            if (node.isRightOfParent()) {
                if (grandPa.getLeft() == null) {
                    Print.println("right right left_null");
                    rightReduce(grandPa);
                    grandPa.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    colorCheck(parent);
                    return;
                }
                if (grandPa.getLeft().getColor() == Color.RED) {
                    Print.println("right right left_red");
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                    return;
                }
                if (grandPa.getLeft().getColor() == Color.BLACK) {
                    Print.println("right right left_black");
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    rightReduce(grandPa);

                }
            } else {
                if (uncle == null || uncle.getColor() == Color.BLACK) {
                    Print.println("right left left_null");
                    if (grandPa.getParent() != null) {
                        if (grandPa.isRightOfParent()) {
                            grandPa.getParent().setRight(node);
                        } else {
                            grandPa.getParent().setLeft(node);
                        }
                    } else {
                        root = node;
                    }
                    parent.setLeft(node.getRight());
                    grandPa.setRight(node.getLeft());
                    node.setRight(parent);
                    node.setLeft(grandPa);
                    node.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);

                } else {
                    uncle.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                }

            }
        } else {

            TreeNode<V> uncle = grandPa.getRight();
            if (node.isRightOfParent()) {
                if (uncle == null || uncle.getColor() == Color.BLACK) {
                    Print.println("left right ");
                    if (grandPa.getParent() != null) {
                        if (grandPa.isRightOfParent()) {
                            grandPa.getParent().setRight(node);
                        } else {
                            grandPa.getParent().setLeft(node);
                        }
                    } else {
                        root = node;
                    }
                    parent.setRight(node.getLeft());
                    grandPa.setLeft(node.getRight());
                    node.setLeft(parent);
                    node.setRight(grandPa);
                    node.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                } else {
                    uncle.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                }
            } else {

                if (grandPa.getRight() == null) {
                    Print.println("left left right_null");
                    leftReduce(grandPa);
                    grandPa.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    return;
                }
                if (grandPa.getRight().getColor() == Color.RED) {
                    Print.println("left left right_red");
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    colorCheck(grandPa);
                    return;
                }
                if (grandPa.getRight().getColor() == Color.BLACK) {
                    Print.println("left left right_black");
                    parent.setColor(Color.BLACK);
                    grandPa.setColor(Color.RED);
                    leftReduce(grandPa);
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
