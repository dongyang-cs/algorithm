package me.alfod.algorithm.sort;

/**
 * Created by Arvin Alfod on 2016/11/5.
 */

import java.util.LinkedList;
import java.util.List;

public class Shell {
    private static int[] getShellIncrementArrayByName(String incrementName, int arrayLength) {
        switch (incrementName) {
            case "Sedgewick": {
                int i = 1, j = 1;
                List<Integer> linkedList = new LinkedList<>();
                while (i < arrayLength / 2) {
                    linkedList.add(i);
                    j++;
                    i = (int) (Math.pow(4, j) - 3 * Math.pow(2, j) + 1);
                }
                Integer[] a = linkedList.toArray(new Integer[linkedList.size()]);
                int[] b = new int[linkedList.size()];
                for (i = 0; i < b.length; i++) {
                    b[i] = a[i];
                }
                return b;

            }
        }

        return null;
    }


    public static <T extends Comparable<? super T>> T[] shell(T[] inputArray) {
        return shell(inputArray, true);
    }

    public static <T extends Comparable<? super T>> T[] shell(T[] inputArray, boolean isAsc) {
        int sequence[] = getShellIncrementArrayByName("Sedgewick", inputArray.length);
        int tmpSecondForIndex, currentSequenceElement;
        T currentValue;
        boolean order = true;
        int firstForIndex, secondForIndex;
        for (firstForIndex = sequence.length - 1; firstForIndex >= 0; firstForIndex--) {
            currentSequenceElement = sequence[firstForIndex];
            for (secondForIndex = 1; secondForIndex <= ((inputArray.length - 1) / currentSequenceElement); secondForIndex++) {
                tmpSecondForIndex = secondForIndex;
                currentValue = inputArray[secondForIndex * currentSequenceElement];

                while (tmpSecondForIndex > 0 && inputArray[(tmpSecondForIndex - 1) * currentSequenceElement].compareTo(currentValue) > 0) {
                    inputArray[tmpSecondForIndex * currentSequenceElement] = inputArray[(tmpSecondForIndex - 1) * currentSequenceElement];
                    --tmpSecondForIndex;
                }
                inputArray[tmpSecondForIndex * currentSequenceElement] = currentValue;
            }
        }
        
        return inputArray;

    }

    public static void main(String[] args) {
        Integer[] tmps = {1, 3, 2, 6, 3, 5, 0, 9, 4, 8};
        tmps = Shell.shell(tmps);
        for (int i = 0; i < tmps.length; i++) {
            System.out.println(tmps[i]);
        }
    }
}
