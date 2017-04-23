package me.alfod.datastructure.tree;

public class RBTree<V extends Comparable<? super V>> extends BaseTree<V> {

    private int blackLine = 1;

    @Override
    public void add(final V v) {
        if (root == null) {
            root = new TreeNode<>(v, true);
            return;
        }
        int blackCount = 0;
        TreeNode<V> node = root;
        TreeNode<V> valueNode = new TreeNode<>(v);
        while (node != null) {
            if (node.isBlack()) {
                blackCount++;
            }
            if (node.getValue().compareTo(v) > 0) {
                if (node.getRight() != null) {
                    node = node.getRight();
                    continue;
                }
                if (node.isBlack()) {
                    valueNode.setBlack(false);
                    node.setRight(valueNode);
                    return;
                } else {
                    if (node.isRightOfParent()) {
                        valueNode.setBlack(true);
                    }
                }

                if (!checkBlack(blackCount)) {

                }
                return;
            }
            if (node.getValue().compareTo(v) < 0) {
                node = node.getLeft();
            }
        }

    }

    private boolean checkBlack(int blackCount) {
        return (blackCount >= blackLine && blackCount <= 2 * blackLine);
    }

    private void colorCheck(TreeNode<V> node) {

    }

    @Override
    public void del(V v) {

    }

    @Override
    public boolean contain(V v) {
        return false;
    }
}
