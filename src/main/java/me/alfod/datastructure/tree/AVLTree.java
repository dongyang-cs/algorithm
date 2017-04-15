package me.alfod.datastructure.tree;

public class AVLTree<V extends Comparable<? super V>> extends BaseTree<V> {
    private TreeNode<V> root;


    public AVLTree() {
    }

    public AVLTree(V v) {
        root = new TreeNode<>(v);
    }

    public AVLTree(V[] v) {
        add(v);
    }

    public TreeNode<V> getRoot() {
        return root;
    }

    @Override
    public void del(V v) {

    }

    @Override
    public boolean contain(final V v) {
        return contain(root, v);
    }

    private boolean contain(final TreeNode<V> node, final V v) {

        return node != null
                && (node.getValue().compareTo(v) == 0
                || contain(node.getLeft(), v)
                || contain(node.getRight(), v));

    }

    public void add(final V value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return;
        }
        TreeNode<V> node = root;
        final TreeNode<V> valueNode = new TreeNode<>(value);
        while (true) {
            if (value.compareTo(node.getValue()) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                    continue;
                } else {
                    node.setRight(valueNode);
                    checkBalance(node.getParent());
                    return;
                }
            } else if (value.compareTo(node.getValue()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    continue;
                } else {
                    node.setLeft(valueNode);
                    checkBalance(node.getParent());
                    return;
                }
            }
            return;
        }

    }

    public void add(final V[] values) {
        for (V v : values) {
            add(v);
        }
    }

    private void checkBalance(TreeNode<V> node) {
        if (node == null) {
            return;
        }
        while (true) {
            //when node is root, need special treatment
            if (node.getParent() == null) {
                root = getBalance(node);
                return;
            }
            node = node.getParent();

            node.setRight(getBalance(node.getRight()));
            node.setLeft(getBalance(node.getLeft()));
        }
    }


    /**
     * <p> give a TreeNode<V> instance, this method will decide whether to take a rotation operation by check
     * the difference of the height of his each two children and return pointer of result </p>
     *
     * @param treeNode be operated TreeNode<V> instance
     * @return rotation operation result or @treeNode itself
     */
    private TreeNode<V> getBalance(TreeNode<V> treeNode) {
        if (treeNode == null) {
            return null;
        }

        int leftHeight = getHeight(treeNode.getLeft());
        int rightHeight = getHeight(treeNode.getRight());

        if (leftHeight - rightHeight >= 2) {
            treeNode = leftReduce(treeNode);
        }
        if (leftHeight - rightHeight <= -2) {
            treeNode = rightReduce(treeNode);
        }

        //when difference of height is 0 or 1
        return treeNode;


    }


}
