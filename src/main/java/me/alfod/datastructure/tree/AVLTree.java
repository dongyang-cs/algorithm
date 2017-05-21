package me.alfod.datastructure.tree;

public class AVLTree<V extends Comparable<? super V>> extends BaseTree<V> {

    public AVLTree() {
    }

    public AVLTree(V v) {
        root = new TreeNode<>(v);
    }

    public AVLTree(V[] v) {
        add(v);
    }

    @Override
    public void del(V v) {

        findEqualThenApply(v, (TreeNode<V> node) -> {
            TreeNode<V> tmp, tmpParent;
            //when node`s value is equal to v, replace the node`s right child`s most-left leave
            if (getHeight(node.getLeft()) >= getHeight(node.getRight())) {
                tmp = node.getLeft();
                if (tmp == null) {
                    node.getParent().replace(node, null);
                    checkBalance(node.getParent().getParent());
                    return;
                }
                tmp = findUniformRight(tmp);
                node.setValue(tmp.getValue());
                tmpParent = tmp.getParent();
                tmp.delete();
                checkBalance(tmpParent.getParent());
            } else {
                tmp = node.getRight();
                tmp = findUniformLeft(tmp);
                node.setValue(tmp.getValue());
                tmpParent = tmp.getParent();
                tmp.delete();
                checkBalance(tmpParent.getParent());
            }
        });
    }


    @Override
    public boolean contain(final V v) {
        return findEqualThenApply(v, null) != null;
    }


    public void add(final V value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return;
        }
        final TreeNode<V> valueNode = new TreeNode<>(value);
        findThenApply(value,
                (TreeNode<V> node) -> {
                    node.setRight(valueNode);
                    checkBalance(node.getParent());
                }, (TreeNode<V> node) -> {
                    node.setValue(value);
                },
                (TreeNode<V> node) -> {
                    node.setLeft(valueNode);
                    checkBalance(node.getParent());
                });
    }


    /**
     * @param node node should be parent of the node whose son changed
     */
    private void checkBalance(TreeNode<V> node) {
        if (node == null) {
            return;
        }
        while (true) {
            //when node is root, need special treatment
            if (node.getParent() == null) {
                root = makeBalance(node);
                return;
            }
            node = node.getParent();

            node.setRight(makeBalance(node.getRight()));
            node.setLeft(makeBalance(node.getLeft()));
        }
    }


    /**
     * <p> give a TreeNode<V> instance, this method will decide whether to take a rotation operation by check
     * the difference of the height of his each two children and return pointer of result </p>
     *
     * @param treeNode be operated TreeNode<V> instance
     * @return rotation operation result or @treeNode itself
     */
    private TreeNode<V> makeBalance(TreeNode<V> treeNode) {
        if (treeNode == null) {
            return null;
        }

        int leftHeight = getHeight(treeNode.getLeft());
        int rightHeight = getHeight(treeNode.getRight());

        if (leftHeight - rightHeight >= 2) {
            treeNode = rotateRight(treeNode);
        }
        if (leftHeight - rightHeight <= -2) {
            treeNode = rotateLeft(treeNode);
        }

        //when difference of height is 0 or 1
        return treeNode;


    }


}
