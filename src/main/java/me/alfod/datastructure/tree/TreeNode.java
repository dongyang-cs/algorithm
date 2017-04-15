package me.alfod.datastructure.tree;


public class TreeNode<V extends Comparable<? super V>> {
    private TreeNode<V> right;
    private TreeNode<V> left;
    private TreeNode<V> parent;
    //true left, false right
    private boolean position;

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    private V value;

    public TreeNode(V value) {
        this.value = value;
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

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public V getRightValue() {
        if (right != null) return right.value;
        else return null;
    }



    public V getLeftValue() {
        if (left != null) return left.value;
        else return null;
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
