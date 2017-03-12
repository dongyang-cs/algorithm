package HashTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/30.
 */

public class HashTableSeparateChaining<Key extends Serializable, Value> implements HashOperate<Key, Value> {
    public static void main(String[] args) {
        HashTableSeparateChaining<Boolean, Integer> hash = new HashTableSeparateChaining<Boolean, Integer>();
        hash.add(true, 888);
        hash.add(false, 123232);
        print("true:" + hash.get(true));
        print("false:" + hash.get(false));
        hash.del(true);
        print("true:" + hash.get(true));
        hash.add(true, 8111188);
        print("true:" + hash.get(true));
    }

    private HashNode<Key, Value>[] table;
    private int index;
    private final int defaultLength = 17;

    @SuppressWarnings(value = "unchecked")
    public HashTableSeparateChaining(int length) {
        table = new HashNode[length];
    }

    public final int getIndex(Key key) {
        long sum = 0;
        if (key instanceof String) {
            char[] chars = ((String) key).toCharArray();
            int standardLength = chars.length > 6 ? 6 : chars.length;
            for (int i = 0; i < standardLength; i++) {
                sum += chars[i] * (32 << i);
            }
            return (int) (sum % table.length);
        } else if (key instanceof Integer) {
            return (Integer) key;
        } else if (key instanceof Boolean) {
            if ((Boolean) key) return 0;
            else return 1;
        } else return 0;
    }

    @SuppressWarnings(value = "unchecked")
    public HashTableSeparateChaining() {
        table = new HashNode[defaultLength];
    }

    public void add(Key key, Value value) {

        index = getIndex(key);
        if (table[index] == null) {
            table[index] = new HashNode<Key, Value>(key, value);
        } else {
            HashNode node = table[index];
            while (node.next != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            if (node.key.equals(key)) {
                node.value = value;
            } else {
                node.next = new HashNode<Key, Value>(key, value);
            }

        }
    }

    public Value get(Key key) {
        index = getIndex(key);
        HashNode<Key, Value> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void del(Key key) {
        index = getIndex(key);
        HashNode node = table[index];
        if (node != null && node.key.equals(key)) {
            table[index] = (null == node.next ? null : node.next);
            return;
        }
        while (node != null && node.next != null) {
            if (node.next.key.equals(key)) {
                if (node.next.next == null) node.next = null;
                else node.next = node.next.next;
                return;
            }
        }

    }

    public static void print(Object o) {
        System.out.println(o);
    }


}


