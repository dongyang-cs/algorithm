package me.alfod.datastructure.tree;

/**
 * Created by arvin
 */
public abstract class BaseTree<V extends Comparable<? super V>> implements Tree<V> {


    public static int getHeight(TreeNode node) {
        return getHeight(node, 1);
    }

    protected static int getHeight(TreeNode node, int i) {
        if (node == null || node.getValue() == null) {
            return 0;
        }
        if (node.getRight() == null && node.getLeft() == null) return i;
        int rightHeight = 0, leftHeight = 0;
        if (node.getRight() != null)
            rightHeight = getHeight(node.getRight(), ++i);
        if (node.getLeft() != null)
            leftHeight = getHeight(node.getLeft(), ++i);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

    }

    protected TreeNode<V> leftRotate(TreeNode<V> node) {
        TreeNode<V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        return head;
    }

    protected TreeNode<V> rightROtate(TreeNode<V> node) {
        TreeNode<V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        return head;
    }

    @SuppressWarnings("unchecked")
    private V getIndexInSort(V[] currentArray, Integer index, Integer head, Integer tail) {
        V[] nextArray = (V[]) (new Object[currentArray.length]);
        V[] tmpArray;
        V axle, int1, int2, int3;
        Integer lastHead, lastTail, tmpIndex;
        while (true) {
            int1 = currentArray[head];
            int2 = currentArray[tail];
            int3 = currentArray[(head + tail) / 2];
            axle = int1.compareTo(int2) > 0 ? int1 : int2;
            axle = axle.compareTo(int3) > 0 ? int3 : axle;

            lastHead = head;
            lastTail = tail;
            //println("head: " + head + " tail:" + tail);
            for (tmpIndex = lastHead; tmpIndex <= lastTail; tmpIndex++) {
                if (currentArray[tmpIndex].compareTo(axle) > 0) nextArray[head++] = currentArray[tmpIndex];
                else if (currentArray[tmpIndex].compareTo(axle) < 0) nextArray[tail--] = currentArray[tmpIndex];
            }
            if ((tail - head) > -1) {
                for (tmpIndex = head; tmpIndex <= tail; tmpIndex++) nextArray[tmpIndex] = axle;
            }
            if (head > index) {
                tail = head;
                head = lastHead;
            } else if (tail < index) {
                head = tail;
                tail = lastTail;
            } else return axle;
            tmpArray = currentArray;
            currentArray = nextArray;
            nextArray = tmpArray;
        }

    }

    private V getIndexInSort(V[] currentArray, Integer index) {
        return getIndexInSort(currentArray, index, 0, currentArray.length - 1);
    }

}
