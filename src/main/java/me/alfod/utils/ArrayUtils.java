package me.alfod.utils;

/**
 * Created by Arvin Alfod on 2017/5/14.
 */
public class ArrayUtils {
    @SuppressWarnings("unchecked")
    private <V extends Comparable<? super V>> V getIndexInSort(V[] currentArray, Integer index, Integer head, Integer tail) {
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

    private <V extends Comparable<? super V>> V getIndexInSort(V[] currentArray, Integer index) {
        return getIndexInSort(currentArray, index, 0, currentArray.length - 1);
    }

}
