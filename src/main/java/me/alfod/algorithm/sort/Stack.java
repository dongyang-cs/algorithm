package me.alfod.algorithm.sort;

public class Stack {
    private Integer[] ints;

    private void sort() {
        int dynamicBorder = ints.length - 1;
        int parentNodeIndex, leftNodeIndex, rightNodeIndex, parentNodeTmp;
        while (dynamicBorder > 0) {
            swap(0, dynamicBorder);
            --dynamicBorder;
            parentNodeIndex = 0;
            leftNodeIndex = 1;
            rightNodeIndex = 2;

            while (leftNodeIndex <= dynamicBorder) {
                if (rightNodeIndex <= dynamicBorder) {
                    if (ints[parentNodeIndex] >= ints[leftNodeIndex]
                            && ints[parentNodeIndex] >= ints[rightNodeIndex])
                        break;

                    if (ints[leftNodeIndex] >= ints[rightNodeIndex]
                            && ints[leftNodeIndex] > ints[parentNodeIndex]) {
                        swap(leftNodeIndex, parentNodeIndex);
                        parentNodeIndex = leftNodeIndex;

                    } else if (ints[rightNodeIndex] >= ints[leftNodeIndex]
                            && ints[rightNodeIndex] > ints[parentNodeIndex]) {
                        swap(rightNodeIndex, parentNodeIndex);
                        parentNodeIndex = rightNodeIndex;
                    }
                } else {
                    if (ints[leftNodeIndex] > ints[parentNodeIndex]) {
                        swap(leftNodeIndex, parentNodeIndex);
                        parentNodeIndex = leftNodeIndex;
                    } else break;
                }

                rightNodeIndex = 2 * parentNodeIndex + 2;
                leftNodeIndex = 2 * parentNodeIndex + 1;
            }
        }
    }

    private void swap(Integer pos1, Integer pos2) {
        int tmp = ints[pos1];
        ints[pos1] = ints[pos2];
        ints[pos2] = tmp;
    }

    private void biggerThenSwap(Integer actives, Integer passive) {
        if (ints[actives] > ints[passive]) swap(actives, passive);
    }

    private void init() {
        Integer parentIndex, count = 0, leftNodeIndex, rightNodeIndex;
        final int LENGTH = ints.length;

        while (count < Math.log(LENGTH) / Math.log(2)) {
            parentIndex = 0;
            leftNodeIndex = 1;
            rightNodeIndex = 2;

            while (leftNodeIndex < LENGTH) {
                biggerThenSwap(leftNodeIndex, parentIndex);
                if (rightNodeIndex < LENGTH)
                    biggerThenSwap(rightNodeIndex, parentIndex);
                ++parentIndex;
                rightNodeIndex = 2 * parentIndex + 2;
                leftNodeIndex = 2 * parentIndex + 1;
            }
            ++count;
        }

    }


}
