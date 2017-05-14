package me.alfod.datastructure.tree;


public class TreeNode<V extends Comparable<? super V>> {
    private TreeNode<V> right;
    private TreeNode<V> left;
    private TreeNode<V> parent;
    private V value;
    private Color color;

    public TreeNode(V value) {
        this.value = value;
    }

    public TreeNode(V value, Color color) {
        this.value = value;
        this.color = color;
    }

    public TreeNode() {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void delete() {
        if (parent == null) {
            return;
        }
        if (isRight()) {
            parent.setRight(null);
        } else {
            parent.setLeft(null);
        }
    }

    public boolean isRight() {
        if (parent == null || parent.getRight() == null) {
            return false;
        }
        return parent.getRight() == this;
    }

    public boolean isLeft() {
        if (parent == null || parent.getLeft() == null) {
            return false;
        }
        return parent.getLeft() == this;
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
        if (parent != null) {
            if (parent == right) {
                right = null;
            }
            if (parent == left) {
                left = null;
            }
        }
    }

    public TreeNode<V> getUncle() {
        if (parent == null || parent.getParent() == null) {
            return null;
        }
        if (parent.isRight()) {
            return parent.getParent().getRight();
        } else {
            return parent.getParent().getLeft();
        }
    }

    public TreeNode<V> getBrother() {
        if (parent == null) {
            return null;
        }
        if (isRight()) {
            return parent.getLeft();
        } else {
            return parent.getRight();
        }
    }

    public TreeNode<V> getRight() {
        return right;
    }

    public void setRight(TreeNode<V> right) throws RuntimeException {
        this.right = right;

        if (right != null) {
            if (right == parent) {
                parent = null;
            }
            right.setParent(this);
        }
    }

    public TreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<V> left) throws RuntimeException {
        this.left = left;
        if (left != null) {
            if (left == parent) {
                parent = null;
            }
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
        final StringBuilder sb = new StringBuilder("TreeNode{");
        sb.append("right=").append(right == null ? "null" : right.getValue());
        sb.append(", left=").append(left == null ? "null" : left.getValue());
        sb.append(", value=").append(value);
        sb.append(", color=").append(color);
        sb.append(", parent=").append(parent);
        sb.append('}');
        return sb.toString();
    }

    public enum Color {
        RED("R"), BLACK("B");
        private String name;

        Color(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
