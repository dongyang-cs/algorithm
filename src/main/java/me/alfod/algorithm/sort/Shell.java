package me.alfod.algorithm.sort;

import java.util.LinkedList;
import java.util.List;

public class Shell {
    /**
     * @param incrementName increment sequence name
     * @param arrayLength   sorted array`s length
     * @return an increment sequence which length is half of the array`length
     */
    public static Integer[] getIncrementSequenceByName(String incrementName, int arrayLength) {
        final String defaultSequenceName = "sedgewick";
        switch (incrementName) {
            case "sedgewick": {
                int sequenceElement = 0, index = 1;
                final int quarterLength = arrayLength / 4;

                List<Integer> linkedList = new LinkedList<>();
                linkedList.add(1);
                while (sequenceElement < quarterLength) {

                    sequenceElement = (int) (Math.pow(4, index + 1) - 3 * Math.pow(2, index + 1) + 1);
                    linkedList.add(sequenceElement);

                    sequenceElement = (int) (9 * Math.pow(4, index) - 9 * Math.pow(2, index) + 1);
                    linkedList.add(sequenceElement);

                    index++;
                }
                Integer[] sequence = linkedList.toArray(new Integer[linkedList.size()]);
                return sequence;

            }
            default: {
                return getIncrementSequenceByName(defaultSequenceName, arrayLength);
            }
        }

    }


    /**
     * @param inputArray array which is going to be sorted
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] shell(T[] inputArray) {
        return shell(inputArray, true, "sedgewick");
    }

    /**
     * @param inputArray  array which is going to be sorted
     * @param isAsc  order, true: asc  false: desc
     * @param sequenceName  name of increment sequence, default and recommend is 'sedgewick'
     *
     * @return sorted array
     */
    private static <T extends Comparable<? super T>> T[] shell(T[] inputArray, final boolean isAsc, final String sequenceName) {

        Integer[] sequence = getIncrementSequenceByName(sequenceName, inputArray.length);

        int tmpSecondForIndex, currentSequenceElement;
        T currentValue;
        boolean order;
        int firstForIndex, secondForIndex;
        for (firstForIndex = sequence.length - 1; firstForIndex >= 0; firstForIndex--) {
            currentSequenceElement = sequence[firstForIndex];
            for (secondForIndex = 1; secondForIndex <= ((inputArray.length - 1) / currentSequenceElement); secondForIndex++) {
                tmpSecondForIndex = secondForIndex;
                currentValue = inputArray[secondForIndex * currentSequenceElement];

                while (tmpSecondForIndex > 0) {

                    if (isAsc) {
                        order = inputArray[(tmpSecondForIndex - 1) * currentSequenceElement].compareTo(currentValue) > 0;
                    } else {
                        order = inputArray[(tmpSecondForIndex - 1) * currentSequenceElement].compareTo(currentValue) < 0;
                    }
                    if (!order) {
                        break;
                    }
                    inputArray[tmpSecondForIndex * currentSequenceElement] = inputArray[(tmpSecondForIndex - 1) * currentSequenceElement];
                    --tmpSecondForIndex;

                }
                inputArray[tmpSecondForIndex * currentSequenceElement] = currentValue;

            }
        }

        return inputArray;

    }

    public static void main(String[] args) {
        getIncrementSequenceByName("sedgewick", 10000);
//        Integer[] tmps = {1, 3, 5, 7, 9, 0, 2, 4, 6, 8};
//        tmps = Shell.shell(tmps);
//        for (int i = 0; i < tmps.length; i++) {
//            System.out.println(tmps[i]);
//        }
    }
}
