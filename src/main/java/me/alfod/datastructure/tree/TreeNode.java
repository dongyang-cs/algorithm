package me.alfod.datastructure.tree;


public class TreeNode<V extends Comparable<? super V>> {
    private TreeNode<V> right;
    private TreeNode<V> left;
    private TreeNode<V> parent;
    private V value;

    public TreeNode(V value) {
        this.value = value;
    }

    public TreeNode() {

    }

    public void replace(TreeNode<V> old, TreeNode<V> now) {
        if (this.getLeft() == old) {
            this.setLeft(now);
        }
        if (this.getRight() == old) {
            this.setRight(now);
        }
    }

    public TreeNode<V> getParent() {
        return parent;
    }

    public void setParent(TreeNode<V> parent) {
        this.parent = parent;
    }

    public TreeNode<V> getRight() {
        return right;
    }

    public void setRight(TreeNode<V> right) throws RuntimeException {
        this.right = right;
        if (right != null) {
            right.setParent(this);
        }
    }

    public TreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<V> left) throws RuntimeException {
        this.left = left;
        if (left != null) {
            left.setParent(this);
        }

    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    @Override

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("value = ").append(value);

        if (right != null) stringBuilder.append(right.toString());
        else stringBuilder.append("right = null");
        stringBuilder.append("\n");

        if (left != null) stringBuilder.append(left.toString());
        else stringBuilder.append("left = null");

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
