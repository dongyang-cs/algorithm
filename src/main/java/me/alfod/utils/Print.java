package me.alfod.utils;

import me.alfod.datastructure.tree.BaseTree;
import me.alfod.datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arvin
 */
public class Print {

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object[] o) {
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

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void println() {
        System.out.println();
    }

    public static String getBlank(int blankNumber) {
        return getDuplicateString(' ', blankNumber);
    }

    public static String getDuplicateString(char c, int count) {
        if (count < 0) count = 0;
        char[] chars = new char[count];
        for (int i = 0; i < count; i++) {
            if (c == '-' && i == chars.length / 2) chars[i] = '|';
            else chars[i] = c;
        }
        return new String(chars);
    }

    public static <V extends Comparable<? super V>> void print(TreeNode<V> root) {
        if(root==null){
            print(null);
        }
        List<TreeNode<V>> currentNodes = new ArrayList<>();
        currentNodes.add(root);
        List<TreeNode<V>> lastNodes = new ArrayList<>();
        List<TreeNode<V>> tmpNodes;
        StringBuilder stringBuilder = new StringBuilder();
        TreeNode<V> currentNode;
        final int fixedLength = 2, height = BaseTree.getHeight(root);
        final int originBlankWidth = fixedLength * (int) Math.pow(2, height);
        List<String> tierContents = new LinkedList<>();
        int currentLength, currentTier = 1, numberLength, blankWidth;
        while (true) {
            lastNodes.clear();
            stringBuilder.delete(0, stringBuilder.length());
            if (currentTier == 1) currentLength = 0;
            else
                currentLength = originBlankWidth / (int) Math.pow(2, currentTier);
            for (int a = 0; a < currentNodes.size(); a++) {
                currentNode = currentNodes.get(a);

                if (currentNode.getLeft() != null)
                    lastNodes.add(currentNode.getLeft());
                else lastNodes.add(new TreeNode<V>(null));

                if (currentNode.getRight() != null)
                    lastNodes.add(currentNode.getRight());
                else lastNodes.add(new TreeNode<V>(null));


                char linkChar;
                if (a % 2 == 0) linkChar = '-';
                else linkChar = ' ';
                if (currentNode.getValue() != null) {
                    numberLength = String.valueOf(currentNode.getValue()).length();
                    stringBuilder.append(currentNode.getValue()+currentNode.getColor().getName()).append(getDuplicateString(linkChar, currentLength - numberLength));
                } else {
                    if (a % 2 == 0 && currentNodes.get(a + 1).getValue() == null)
                        stringBuilder.append(' ').append(getDuplicateString(' ', currentLength - 1));
                    else if (a % 2 == 0 && currentNodes.get(a + 1).getValue() != null)
                        stringBuilder.append("-").append(getDuplicateString('-', currentLength - 1));
                    else stringBuilder.append(" ").append(getDuplicateString(' ', currentLength - 1));
                }

            }
            if (currentTier > height) break;
            blankWidth = fixedLength * ((int) Math.pow(2, (height - currentTier++) - 1));
            tierContents.add(getBlank(blankWidth) + stringBuilder.toString());
            tmpNodes = currentNodes;
            currentNodes = lastNodes;
            lastNodes = tmpNodes;
        }

        int miniBlankWidth = Integer.MAX_VALUE;
        int tmpMiniBlankWidth;
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

}
