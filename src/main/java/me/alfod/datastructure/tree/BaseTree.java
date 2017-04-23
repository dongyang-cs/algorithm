package me.alfod.datastructure.tree;

/**
 * Created by arvin
 */
public abstract class BaseTree<V extends Comparable<? super V>> implements Tree<V> {
    protected TreeNode<V> root;


    public static int getHeight(final TreeNode node) {
        return getHeight(node, 1);
    }

    protected static int getHeight(final TreeNode node, final int height) {
        if (node == null) return height - 1;
        int rightHeight = getHeight(node.getRight(), height + 1);
        int leftHeight = getHeight(node.getLeft(), height + 1);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

    }

    protected TreeNode<V> rightReduce(TreeNode<V> node) {
        TreeNode<V> head = node.getRight();
        head.setParent(node.getParent());

        node.setRight(head.getLeft());
        head.setLeft(node);
        return head;
    }

    protected TreeNode<V> leftReduce(TreeNode<V> node) {
        TreeNode<V> head = node.getLeft();
        head.setParent(node.getParent());

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

    public void add(V[] vs) {
        for (int i = 0; i < vs.length; i++) {
            add(vs[i]);
        }
    }

}
