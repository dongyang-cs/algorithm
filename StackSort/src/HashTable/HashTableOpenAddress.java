package HashTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/1.
 */
public class HashTableOpenAddress<Key extends Serializable, Value> implements HashOperate<Key, Value> {
    public static void main(String[] args) {
        HashTableOpenAddress<String, String> hashTableOpenAddress = new HashTableOpenAddress<String, String>();

        int length = 1000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            hashTableOpenAddress.add((long) (Math.random() * 1000000000000000000L) + "", "" + i);
        }
        System.out.println((float) hashTableOpenAddress.getCount() / length);
        long endTime = System.currentTimeMillis();
        System.out.println("run time: " + (double) (endTime - startTime) / 1000 + " s");
        System.out.println("sum time: " + (double) HashTableOpenAddress.time / (1000 * 1000 * 1000) + " s");
        hashTableOpenAddress.printStatus();

    }

    private HashNode<Key, Value>[] table;
    private final int defaultLength = 17;
    private static int count = 0;
    public static long time = 0L;

    public HashTableOpenAddress(int length) {
        table = new HashNode[length];
    }

    public HashTableOpenAddress() {
        table = new HashNode[defaultLength];
    }

    private int hash(Key key, int length) {
        if (key instanceof String) {
            int address = 0;
            char[] chars = ((String) key).toCharArray();
            int defaultLength = chars.length;
            for (int i = 0; i < defaultLength; i++) {
                address += chars[i] * (32 << i);
            }
            return Math.abs(address % length);
        } else if (key instanceof Integer) {
            return (Integer) key;
        } else if (key instanceof Boolean) {
            if ((Boolean) key) return 0;
            else return 1;
        } else return 0;
    }

    public float getPackingFactor() {
        final int count = 1000;
        final int loopCount = table.length > 1000 ? 1000 : table.length;
        final int step = table.length / loopCount;
        int sum = 0;
        for (int i = 0; i < loopCount; i += step) {
            if (table[i] != null && table[i].getActive()) sum++;
        }
        return (float) sum / loopCount;
    }

    private int getSum() {
        int sum = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ++sum;
            }
        }
        return sum;
    }

    private int getActive() {
        int sum = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getActive()) {
                ++sum;
            }
        }
        return sum;
    }

    private void capacityExtension() {
        HashNode<Key, Value>[] newTable = new HashNode[HashTableOpenAddress.getPrimeNumber(2 * table.length)];
        for (HashNode<Key, Value> node : table) {
            if (node != null && node.getActive()) {
                newTable[findNext(node.key, newTable)] = node;
            }
        }
        // System.out.println("table capacity extension from " + table.length + " to " + newTable.length);
        table = newTable;
    }

    private static int getPrimeNumber(int start) {
        if (start % 2 == 0) {
            start += 1;
        }
        while (!HashTableOpenAddress.checkPrimeNumber(start)) {
            start += 2;
        }
        return start;
    }

    private static boolean checkPrimeNumber(int number) {
        if (number % 2 == 0) return false;
        int dividend = 3;
        int upperLimit = (int) Math.sqrt(number) + 1;
        while (dividend < upperLimit) {
            if (number % dividend == 0) return false;
            dividend += 2;
        }
        return true;
    }

    public Value get(Key key) {
        int tmpIndex = findKey(key);
        //print(tmpIndex);
        if (table[tmpIndex] == null) return null;
        else return table[tmpIndex].value;
    }


    public void add(Key key, Value value) {
        Integer index = findNext(key);
        if (table[index] == null) {
            table[index] = new HashNode<Key, Value>(key, value);
        } else {
            table[index].key = key;
            table[index].value = value;
            table[index].active();
        }
        long start = System.nanoTime();
        if (getPackingFactor() > 0.5) {
            capacityExtension();
        }
        time += System.nanoTime() - start;
    }

    public void del(Key key) {
        Integer delIndex = findKey(key);
        if (table[delIndex] != null) table[delIndex].delete();
    }


    private Integer findKey(Key key) {
        return findKey(key, table);
    }

    private Integer findNext(Key key) {
        return findNext(key, table);
    }

    private Integer findNext(Key key, HashNode<Key, Value>[] nodes) {
        Integer index = findKey(key, nodes);
        if (index != null) return index;
        int localIndex = hash(key, nodes.length);
        int i = 1;
        int halfLength = nodes.length / 2;
        while (nodes[localIndex] != null
                && nodes[localIndex].getActive()) {
            localIndex += i * i++;
            localIndex = Math.abs(localIndex % nodes.length);
            if (i > halfLength) {
                capacityExtension();
                print("findKey failed : " + i);
                return findKey(key, nodes);
            }
        }
        count += i;
        return localIndex;
    }

    private Integer findKey(Key key, HashNode<Key, Value>[] nodes) {
        int localIndex = hash(key, nodes.length);
        int i = 1;
        int halfLength = nodes.length / 2;
        while (nodes[localIndex] != null
                && !nodes[localIndex].key.equals(key)) {
            localIndex += i * i++;
            localIndex = Math.abs(localIndex % nodes.length);
            if (i > halfLength) {
                print("findKey failed : " + i);
                return null;
            }
        }
        count += i;
        return localIndex;
    }


    public void printAll() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)
                System.out.println("index: " + i + "    " +
                        table[i].toString());
            else
                System.out.println("index: " + i +
                        "    key: " + null +
                        "    value:" + null +
                        "    exist:" + false);

        }
        System.out.println("\ntable length: " + table.length
                + "  sum:" + getSum() + "  active:" + getActive());
    }

    public void printStatus() {
        System.out.println("\ntable length: " + table.length
                + "  sum:" + getSum() + "  active:" + getActive());
    }

    private void print(Object o) {
        System.out.println(o);
    }

    public int getCount() {
        return count;
    }
}
