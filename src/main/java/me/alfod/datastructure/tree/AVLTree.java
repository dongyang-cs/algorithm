package me.alfod.datastructure.tree;

public class AVLTree<V extends Comparable<? super V>> extends BaseTree<V>  {
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
        TreeNode<V> node = root;
        TreeNode<V> tmp;
        while (true) {
            if (node == null) {
                break;
            }
            if (node.getValue().compareTo(v) > 0) {
                node = node.getLeft();
                continue;
            }
            if (node.getValue().compareTo(v) < 0) {
                node = node.getRight();
                continue;
            }
            //when node`s value is equal to v, replace the node`s right child`s most-left leave
            if (getHeight(node.getLeft()) >= getHeight(node.getRight())) {
                tmp = node.getLeft();
                if (tmp == null) {
                    node.getParent().replace(node, null);
                    checkBalance(node.getParent().getParent());
                    return;
                }
                while (true) {
                    if (tmp.getRight() == null) {
                        node.setValue(tmp.getValue());
                        tmp.getParent().replace(tmp, null);
                        checkBalance(tmp.getParent().getRight());
                        return;
                    }
                    tmp = tmp.getRight();

                }
            } else {
                tmp = node.getRight();
                while (true) {
                    if (tmp.getLeft() == null) {
                        node.setValue(tmp.getValue());
                        tmp.getParent().replace(tmp, null);
                        checkBalance(tmp.getParent().getRight());
                        return;
                    }
                    tmp = tmp.getLeft();

                }
            }


        }
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
