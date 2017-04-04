package me.alfod.algorithm.sort;

import java.util.LinkedList;
import java.util.List;

public class Shell {
    /**
     * @param incrementName increment sequence name
     * @param arrayLength   sorted array`s length
     * @return an increment sequence which length is half of the array`length
     */
    private static Integer[] getIncrementSequenceByName(Sequence incrementName, int arrayLength) {
        final Sequence defaultSequence = Sequence.SEDGEWICK;
        switch (incrementName) {
            case SEDGEWICK: {
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
                return linkedList.toArray(new Integer[linkedList.size()]);

            }
            default: {
                return getIncrementSequenceByName(defaultSequence, arrayLength);
            }
        }

    }

    /**
     * @param inputArray array which is going to be sorted
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] inputArray) {
        return sort(inputArray, true, Sequence.SEDGEWICK);
    }

    /**
     * @param inputArray   array which is going to be sorted
     * @param isAsc        order, true: asc  false: desc
     * @param sequenceName name of increment sequence, default and recommend is 'sedgewick'
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] inputArray, final boolean isAsc, Sequence sequenceName) {
        Integer[] sequence = getIncrementSequenceByName(Sequence.SEDGEWICK, inputArray.length);

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

    /**
     * increment sequence of shell, default and recommended is sedgewick
     */
    public enum Sequence {
        /**
         * sedgewick is turned out to be the most efficient sequence in practice
         */
        SEDGEWICK
    }
}
