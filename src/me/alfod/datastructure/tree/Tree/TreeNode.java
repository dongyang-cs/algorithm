package me.alfod.datastructure.tree.Tree;

/**
 * Created by Administrator on 2017/2/10.
 */
public class TreeNode {
    private TreeNode right;
    private TreeNode left;
    private Integer value;

    public TreeNode(Integer value) {
        this.value = value;
    }


    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRightValue(Integer integer) {
        setRight(new TreeNode(integer));
    }

    public Integer getRightValue() {
        if (right != null) return right.value;
        else return null;
    }

    public void setLeftValue(Integer integer) {
        setLeft(new TreeNode(integer));
    }

    public Integer getLeftValue() {
        if (left != null) return left.value;
        else return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
