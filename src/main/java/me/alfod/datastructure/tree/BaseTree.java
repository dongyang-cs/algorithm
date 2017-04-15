package me.alfod.datastructure.tree;

/**
 * Created by arvin
 */
public abstract class BaseTree<V extends Comparable<? super V>> implements Tree<V> {


    public static int getHeight(TreeNode node) {
        return getHeight(node, 1);
    }

    protected static int getHeight(TreeNode node, final int height) {
        if (node == null) return height - 1;
        int rightHeight = getHeight(node.getRight(), height + 1);
        int leftHeight = getHeight(node.getLeft(), height + 1);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

    }

    public static void main(String[] args) {
        TreeNode<Integer> i = new TreeNode<>(1);
        TreeNode<Integer> j = new TreeNode<>(2);
        TreeNode<Integer> k = new TreeNode<>(2);
        i.setRight(j);
        j.setLeft(k);
        System.out.println(getHeight(i));
    }

    protected TreeNode<V> leftRotate(TreeNode<V> node) {
        TreeNode<V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        return head;
    }

    protected TreeNode<V> rightRotate(TreeNode<V> node) {
        if(node.getLeft()!=null) {
            TreeNode<V> head = node.getLeft();
            node.setLeft(head.getRight());
            head.setRight(node);
            return head;
        }else{
            return node;
        }
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
