package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5.
 */
public class ArrayManager {
    private static ArrayManager arrayManager;

    public static ArrayManager getInstance() {
        if (arrayManager == null) {
            arrayManager = new ArrayManager();
        }
        return arrayManager;
    }

    public Integer[] getArray(int length) {
        return getArray(length, length);
    }

    public Integer[] getArray(int length, int multiple, boolean unique) {
        Integer[] integers = new Integer[length];
        int randomNum, currentIndex = 0;
        while (true) {
            randomNum = (int) (Math.random() * multiple);
            if (!unique
                    || !arrayContain(integers, randomNum)) integers[currentIndex++] = randomNum;

            if (currentIndex >= length) break;
        }

        return integers;

    }

    private boolean arrayContain(Integer[] integers, Integer i) {
        for (Integer integer : integers) {
            if (integer != null && integer.equals(i)) return true;
        }
        return false;
    }

    public Integer[] getArray(int length, int multiple) {
        return getArray(length, multiple, false);
    }

    public Integer[] getArrayUnique(int length, int multiple) {
        return getArray(length, multiple, true);
    }

    public Integer[] getArray() {
        return getArray(1000);
    }

    public void arrayPrint(Integer[] array) {
        System.out.print(" " + array[0] + " ");
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n array length is : " + array.length + "\n\n");
    }

    public void arrayPrint(int[] array) {
        System.out.print(" " + array[0] + " ");
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n array length is : " + array.length);
    }

    public int[] getShellIncrementArrayByName(String incrementName, int arrayLength) {
        switch (incrementName) {
            case "Sedgewick": {
                int i = 1, j = 1;
                LinkedList<Integer> linkedList = new LinkedList<>();
                while (i < arrayLength / 2) {
                    linkedList.add(i);
                    j++;
                    i = (int) (Math.pow(4, j) - 3 * Math.pow(2, j) + 1);
                }
                Integer[] a = (Integer[]) linkedList.toArray(new Integer[linkedList.size()]);
                int[] b = new int[linkedList.size()];
                for (i = 0; i < b.length; i++) {
                    b[i] = a[i];
                }
                return b;
            }
        }

        return null;
    }

    public Integer[] getSets(Integer[] ints) {
        List<Integer> list = new ArrayList<>(ints.length);
        for (int i : ints) {
            if (!list.contains(i)) list.add(i);
        }
        Integer[] intgers = new Integer[list.size()];
        return list.toArray(intgers);
    }
}
