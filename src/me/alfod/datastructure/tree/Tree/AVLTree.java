package me.alfod.datastructure.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */
public class AVLTree {
    public final int length;
    public final int height;
    private TreeNode root;

    public AVLTree(Integer[] integers) {
        root = balanceInit(integers);
        length = integers.length;
        height = getHeight(root, 1);
    }

    public static void main(String[] args) {

        System.out.println('d'+"k");
//        ArrayManager arrayManager = ArrayManager.getInstance();
//        Integer[] integers = arrayManager.getSets(arrayManager.getArray(10));
//        arrayManager.arrayPrint(integers);
//        AVLTree tree = new AVLTree(integers);
//        arrayManager.arrayPrint(tree.quickSort(integers));
//        //tree.println(tree.getIndexInSort(integers, integers.length / 2));
//        Integer[] integers1=arrayManager.getArrayUnique(5,10);
//        arrayManager.arrayPrint(integers1);
//        tree.print();
//        tree.add(integers1);
//        tree.print();
    }

    //region basic operation
    public void add(Integer integer) {
        TreeNode node = root;
        while (true) {
            if (integer > node.getValue()) {
                if (node.getRight() != null)
                    node = node.getRight();
                else {
                    node.setRightValue(integer);
                    break;
                }
            } else if (integer < node.getValue()) {
                if (node.getLeft() != null)
                    node = node.getLeft();
                else {
                    node.setLeftValue(integer);
                    break;
                }
            } else break;
        }
    }

    public void add(Integer[] integers){
        for(Integer i:integers) {
            add(i);
        }
    }
    //endregion
    //region sort related function
    private Integer getIndexInSort(Integer[] currentArray, Integer index) {
        return getIndexInSort(currentArray, index, 0, currentArray.length - 1);
    }


    public Integer[] quickSort(Integer[] integers) {
        return quickSort(integers, 0, integers.length);
    }

    private Integer[] quickSort(Integer[] integers, final int HEAD, final int TAIL) {
        // println("Head: " + HEAD + "   " + "Eail: " + TAIL);
        if (TAIL - HEAD < 1) return integers;
        final int axle = integers[HEAD];
        int tmpHead = 0, tmpTail = TAIL - HEAD - 1;
        Integer[] tmpIntegerArray = new Integer[TAIL - HEAD];
        for (int i = HEAD; i < TAIL; i++) {
            if (integers[i] > axle) {
                tmpIntegerArray[tmpTail--] = integers[i];
            }
            if (integers[i] < axle) {
                tmpIntegerArray[tmpHead++] = integers[i];
            }
        }

        //println(integers);
        for (int i = HEAD; i < TAIL; i++) {
            if (tmpIntegerArray[i - HEAD] != null) integers[i] = tmpIntegerArray[i - HEAD];
            else integers[i] = axle;
        }
        //println("HEAD: " + HEAD + "   TAIL:  " + TAIL);
        integers = quickSort(integers, HEAD, tmpHead + HEAD);
        integers = quickSort(integers, tmpTail + 1 + HEAD, TAIL);
        return integers;
    }

    private Integer getIndexInSort(Integer[] currentArray, Integer index, Integer head, Integer tail) {
        Integer[] nextArray = new Integer[currentArray.length];
        Integer[] tmpArray;
        int axle, lastHead, lastTail, tmpIndex, int1, int2, int3;
        while (true) {
            int1 = currentArray[head];
            int2 = currentArray[tail];
            int3 = currentArray[(head + tail) / 2];
            axle = int1 > int2 ? int1 : int2;
            axle = axle > int3 ? int3 : axle;

            lastHead = head;
            lastTail = tail;
            //println("head: " + head + " tail:" + tail);
            for (tmpIndex = lastHead; tmpIndex <= lastTail; tmpIndex++) {
                if (currentArray[tmpIndex] > axle) nextArray[head++] = currentArray[tmpIndex];
                else if (currentArray[tmpIndex] < axle) nextArray[tail--] = currentArray[tmpIndex];
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

    //endregion

    //region  initialize functions
    private TreeNode rudeInit(Integer[] integers) {
        final TreeNode root = new TreeNode(getCenterValue(integers));
        TreeNode tmpNode;
        for (Integer integer : integers) {
            tmpNode = root;
            while (true) {
                if (integer > tmpNode.getValue()) {
                    if (tmpNode.getLeft() != null) {
                        tmpNode = tmpNode.getLeft();
                    } else {
                        tmpNode.setLeft(new TreeNode(integer));
                        break;
                    }
                } else if (integer < tmpNode.getValue()) {
                    if (tmpNode.getRight() != null)
                        tmpNode = tmpNode.getRight();
                    else {
                        tmpNode.setRight(new TreeNode(integer));
                        break;
                    }
                } else break;
            }
        }
        return root;
    }

    public TreeNode balanceInit(Integer[] integers) {
        integers = quickSort(integers);
        return balanceInit(integers, integers.length / 2, integers.length - 1, 0);
    }


    private TreeNode balanceInit(Integer[] integers, int index, int rightBorder, int leftBorder) {
        TreeNode node;
        if (index < 0 || index >= integers.length) return null;
        else node = new TreeNode(integers[index]);

        if (rightBorder - index > 1) {
            node.setRight(balanceInit(integers, (rightBorder + index + 1) / 2, rightBorder, index + 1));
        } else if (rightBorder - index == 1) {
            node.setRight(new TreeNode(integers[rightBorder]));
        }

        if (index - leftBorder > 1) {
            node.setLeft(balanceInit(integers, (leftBorder + index - 1) / 2, index - 1, leftBorder));
        } else if (index - leftBorder == 1) {
            node.setLeft(new TreeNode(integers[leftBorder]));
        }
        return node;
    }

    //endregion

    //region tools

    private Integer getCenterValue(Integer[] integers) {
        return getIndexInSort(integers, integers.length / 2);
    }

    private String getBlank(int blankNumber) {
        return getDuplicateString(' ', blankNumber);
    }

    private String getDuplicateString(char c, int count) {
        if (count < 0) count = 0;
        char[] chars = new char[count];
        for (int i = 0; i < count; i++) {
            if (c == '-' && i == chars.length / 2) chars[i] = '|';
            else chars[i] = c;
        }
        return new String(chars);
    }

    private int getHeight(TreeNode node) {
        return getHeight(node, 1);
    }

    private int getHeight(TreeNode node, int i) {
        if (node.getRight() == null && node.getLeft() == null) return i;
        int rightHeight = 0, leftHeight = 0;
        if (node.getRight() != null)
            rightHeight = getHeight(node.getRight(), ++i);
        if (node.getLeft() != null)
            leftHeight = getHeight(node.getLeft(), ++i);
        return rightHeight > leftHeight ? rightHeight : leftHeight;

    }

    //endregion

    //region print functions
    public void print(TreeNode root) {
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

    private void print(Object o) {
        System.out.print(o);
    }

    public void print() {
        print(root);
    }

    private void println(Object[] o) {
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

    private void println(Object o) {
        System.out.println(o);
    }

    private void println() {
        System.out.println();
    }

    //endregion

    //region basic functions

    private int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }

    private int log(int a, int b) {
        return (int) (Math.log(a) / Math.log(b));
    }
    //endregion
}
