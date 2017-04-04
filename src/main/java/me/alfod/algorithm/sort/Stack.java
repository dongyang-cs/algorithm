package me.alfod.algorithm.sort;

public class Stack {

    /**
     * @param inputArray array going to be sorted
     * @param <T>        extends Comparable
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] inputArray) {
        inputArray = init(inputArray);
        int tailIndex = inputArray.length - 1;
        int parentNodeIndex, leftNodeIndex, rightNodeIndex, parentNodeTmp;
        while (tailIndex > 0) {
            swap(inputArray, 0, tailIndex);
            --tailIndex;
            parentNodeIndex = 0;
            leftNodeIndex = 1;
            rightNodeIndex = 2;


            while (leftNodeIndex <= tailIndex) {
                if (rightNodeIndex <= tailIndex) {
                    if (inputArray[parentNodeIndex].compareTo(inputArray[leftNodeIndex]) >= 0
                            && inputArray[parentNodeIndex].compareTo(inputArray[rightNodeIndex]) >= 0)
                        break;

                    if (inputArray[leftNodeIndex].compareTo(inputArray[rightNodeIndex]) > 0
                            && inputArray[leftNodeIndex].compareTo(inputArray[parentNodeIndex]) > 0) {
                        swap(inputArray, leftNodeIndex, parentNodeIndex);
                        parentNodeIndex = leftNodeIndex;

                    } else if (inputArray[rightNodeIndex].compareTo(inputArray[leftNodeIndex]) > 0
                            && inputArray[rightNodeIndex].compareTo(inputArray[parentNodeIndex]) > 0) {
                        swap(inputArray, rightNodeIndex, parentNodeIndex);
                        parentNodeIndex = rightNodeIndex;
                    }
                } else {
                    if (inputArray[leftNodeIndex].compareTo(inputArray[parentNodeIndex]) > 0) {
                        swap(inputArray, leftNodeIndex, parentNodeIndex);
                        parentNodeIndex = leftNodeIndex;
                    } else break;
                }

                leftNodeIndex = 2 * parentNodeIndex + 1;
                rightNodeIndex = 2 * parentNodeIndex + 2;

            }
        }
        return inputArray;
    }

    private static <T extends Comparable<? super T>> void swap(T[] inputArray, Integer pos1, Integer pos2) {
        T tmp = inputArray[pos1];
        inputArray[pos1] = inputArray[pos2];
        inputArray[pos2] = tmp;
    }

    private static <T extends Comparable<? super T>> void biggerThenSwap(T[] inputArray, Integer actives, Integer passive) {
        if (inputArray[actives].compareTo(inputArray[passive]) > 0) {
            swap(inputArray, actives, passive);
        }
    }

    private static <T extends Comparable<? super T>> T[] init(T[] inputArray) {
        int parentIndex, count = 0, leftNodeIndex, rightNodeIndex;
        final int LENGTH = inputArray.length;

        while (count < (Math.log(LENGTH) / Math.log(2))) {
            parentIndex = 0;
            leftNodeIndex = 1;
            rightNodeIndex = 2;

            while (leftNodeIndex < LENGTH) {
                biggerThenSwap(inputArray, leftNodeIndex, parentIndex);
                if (rightNodeIndex < LENGTH) {
                    biggerThenSwap(inputArray, rightNodeIndex, parentIndex);
                }
                ++parentIndex;
                leftNodeIndex = 2 * parentIndex + 1;
                rightNodeIndex = 2 * parentIndex + 2;
            }
            ++count;
        }
        return inputArray;
    }


}
