package me.alfod.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arvin
 */
public abstract class BaseTree<V extends Comparable<? super V>> {
    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object[] o) {
        println();
        print("Array[");
        for (int i = 0; i < o.length; i++) {
            print(o[i]);
            if (i < o.length - 1)
                print(" ,");
        }
        print("]");
        println();
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void println() {
        System.out.println();
    }

    protected V getCenterValue(V[] integers) {
        return getIndexInSort(integers, integers.length / 2);
    }

    protected String getBlank(int blankNumber) {
        return getDuplicateString(' ', blankNumber);
    }

    protected String getDuplicateString(char c, int count) {
        if (count < 0) count = 0;
        char[] chars = new char[count];
        for (int i = 0; i < count; i++) {
            if (c == '-' && i == chars.length / 2) chars[i] = '|';
            else chars[i] = c;
        }
        return new String(chars);
    }

    protected int getHeight(TreeNode node) {
        return getHeight(node, 1);
    }

    protected int getHeight(TreeNode node, int i) {
        if (node.getRight() == null && node.getLeft() == null) return i;
        int rightHeight = 0, leftHeight = 0;
        if (node.getRight() != null)
            rightHeight = getHeight(node.getRight(), ++i);
        if (node.getLeft() != null)
            leftHeight = getHeight(node.getLeft(), ++i);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

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

    public void print(TreeNode<V> root) {
        List<TreeNode> currentNodes = new ArrayList<>();
        currentNodes.add(root);
        List<TreeNode> lastNodes = new ArrayList<>();
        List<TreeNode> tmpNodes;
        StringBuilder stringBuilder = new StringBuilder();
        TreeNode currentNode;
        final int fixedLength = 2, height = getHeight(root);
        final int originBlankWidth = fixedLength * pow(2, height);
        List<String> tierContents = new LinkedList<>();
        int currentLength, currentTier = 1, numberLength, blankWidth;
        while (true) {
            lastNodes.clear();
            stringBuilder.delete(0, stringBuilder.length());
            if (currentTier == 1) currentLength = 0;
            else
                currentLength = originBlankWidth / (pow(2, currentTier));
            for (int a = 0; a < currentNodes.size(); a++) {
                currentNode = currentNodes.get(a);

                if (currentNode.getLeft() != null)
                    lastNodes.add(currentNode.getLeft());
                else lastNodes.add(new TreeNode(null));

                if (currentNode.getRight() != null)
                    lastNodes.add(currentNode.getRight());
                else lastNodes.add(new TreeNode(null));


                char linkChar;
                if (a % 2 == 0) linkChar = '-';
                else linkChar = ' ';
                if (currentNode.getValue() != null) {
                    numberLength = String.valueOf(currentNode.getValue()).length();
                    stringBuilder.append(currentNode.getValue()).append(getDuplicateString(linkChar, currentLength - numberLength));
                } else {
                    if (a % 2 == 0 && currentNodes.get(a + 1).getValue() == null)
                        stringBuilder.append(' ').append(getDuplicateString(' ', currentLength - 1));
                    else if (a % 2 == 0 && currentNodes.get(a + 1).getValue() != null)
                        stringBuilder.append("-").append(getDuplicateString('-', currentLength - 1));
                    else stringBuilder.append(" ").append(getDuplicateString(' ', currentLength - 1));
                }

            }
            if (currentTier > height) break;
            blankWidth = fixedLength * (pow(2, (height - currentTier++) - 1));
            tierContents.add(getBlank(blankWidth) + stringBuilder.toString());
            tmpNodes = currentNodes;
            currentNodes = lastNodes;
            lastNodes = tmpNodes;
        }

        int miniBlankWidth = Integer.MAX_VALUE;
        int tmpMiniBlankWidth = 0;
        int linkCharWidth = 0;
        for (int i = 0; i < tierContents.size(); i++) {
            tmpMiniBlankWidth = 0;
            for (int j = 0; j < tierContents.get(i).length(); j++) {
                if (tierContents.get(i).charAt(j) != ' ') break;
                tmpMiniBlankWidth = j;
            }
            if (tmpMiniBlankWidth < miniBlankWidth) miniBlankWidth = tmpMiniBlankWidth;
        }
        for (int i = 0; i < tierContents.size(); i++) {
            println(tierContents.get(i).substring(miniBlankWidth, tierContents.get(i).length()));
        }

    }

    private int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }

    private int log(int a, int b) {
        return (int) (Math.log(a) / Math.log(b));
    }


}
