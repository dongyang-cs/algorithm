package me.alfod.datastructure.tree;

import me.alfod.utils.Print;

public class AVLTree<V extends Comparable<? super V>> extends BaseTree<V> {
    private TreeNode<V> root;

    public AVLTree(V[] values) {
        add(values);
    }

    public AVLTree() {
    }

    public TreeNode<V> getRoot() {
        return root;
    }

    @Override
    public void del(V v) {

    }

    @Override
    public boolean contain(V v) {
        return true;
    }

    public void add(V value) {
        if(root==null){
            root=new TreeNode<>(value);
            return;
        }
        TreeNode<V> node = root;
        TreeNode<V> valueNode = new TreeNode<>(value);
        while (true) {
            if (value.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null)
                    node = node.getRight();
                else {
                    node.setRight(valueNode);
                    valueNode.setParent(node);
                    valueNode.setPosition(false);
                    checkBalance(node.getParent());
                    break;
                }
            } else if (value.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null)
                    node = node.getLeft();
                else {
                    node.setLeft(valueNode);
                    valueNode.setParent(node);
                    valueNode.setPosition(true);
                    checkBalance(node.getParent());
                    break;
                }
            } else{
                break;
            }
        }

    }

    private void checkBalance(TreeNode<V> node) {
        if(node==null){
            return;
        }
        while (node!=null) {
            int leftHeight = getHeight(node.getLeft());
            int rightHeight = getHeight(node.getRight());
            if ( rightHeight-leftHeight > 1) {
                if (node.getParent() != null) {
                    if (node.isPosition()) {
                        node.getParent().setLeft(leftRotate(node));
                    } else {
                        node.getParent().setRight(leftRotate(node));
                    }
                } else {
                    leftRotate(node);
                }
                node = node.getParent();
                continue;
            }
            if (leftHeight - rightHeight > 1) {
                if (node.getParent() != null) {
                    if (node.isPosition()) {
                        node.getParent().setLeft(rightRotate(node));
                    } else {
                        node.getParent().setRight(rightRotate(node));
                    }
                } else {
                    rightRotate(node);
                }
                node = node.getParent();
                continue;
            }
            break;
        }
    }

    public void add(V[] values) {
        for (V i : values) {
            add(i);
        }
    }


}
