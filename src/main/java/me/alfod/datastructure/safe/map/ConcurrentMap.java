package me.alfod.datastructure.safe.map;

import sun.misc.Unsafe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Arvin Alfod on 2017/6/8.
 */
public class ConcurrentMap<K, V> implements Map<K, V> {
    private static final int CAT_FAILED_THEN_SLEEP_THRESHOLD = 10;
    private static final int CAT_FAILED_THEN_SLEEP_TIME_CAP = 10;
    private static final int CAT_FAILED_THEN_SLEEP_TIME_FLOOR = 10;
    Unsafe unsafe = Unsafe.getUnsafe();
    private int defaultSegmentLength = 16;
    private int size;
    private Node<K, V>[] nodes;
    private Class kClass = null;
    private Class vClass = null;

    public ConcurrentMap() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        kClass = (Class) types[0];
        vClass = (Class) types[1];

        nodes = new Node[defaultSegmentLength];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (getNode(key) == null) {
            return false;
        }
        return getNode(key).findKey(key) != null;
    }

    private Node<K, V> getNode(Object o) {
        return nodes[(o.hashCode() - 1) & nodes.length];
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        if (getNode(key).findKey(key) == null) {
            return null;
        } else {
            return getNode(key).findKey(key).v;
        }
    }

    @Override
    public V put(K key, V value) {
        if (getNode(key) == null) {
            synchronized (getNode(key)) {
                if (getNode(key) == null) {
                    nodes[key.hashCode() % nodes.length] = new Node<>(key, value);
                    return get(key);
                } else {
                    return getNode(key).put(key, value).getValue();
                }
            }
        } else {
            return getNode(key).put(key, value).getValue();
        }

    }

    @Override
    public V remove(Object key) {
        if (getNode(key) == null) {
            return null;
        } else {
            return getNode(key).remove(key);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySets = new LinkedHashSet<>();
        Node<K, V> node;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                node = nodes[i];
                while (node != null) {
                    keySets.add(node.getKey());
                    node = node.next;
                }
            }
        }
        return keySets;
    }

    @Override
    public Collection<V> values() {
        Set<V> valueSets = new LinkedHashSet<>();
        Node<K, V> node;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                node = nodes[i];
                while (node != null) {
                    valueSets.add(node.getValue());
                    node = node.next;
                }
            }
        }
        return valueSets;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new LinkedHashSet<>();
        Node<K, V> node;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                node = nodes[i];
                while (node != null) {
                    entrySet.add(node);
                    node = node.next;
                }
            }
        }
        return entrySet;
    }


    public static final class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        long vOffSet;
        K k;
        volatile V v;
        volatile Node<K, V> next;
        volatile Node<K, V> last;
        Unsafe unsafe = Unsafe.getUnsafe();

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.hash = k.hashCode();
            try {
                vOffSet = unsafe.objectFieldOffset(Node.class.getDeclaredField("v"));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        private void assignNext(Node<K, V> next) {
            this.next = next;
            if (next != null) {
                next.last = this;
            }
        }

        public V remove(Object k) {
            Node<K, V> point = this;
            while (true) {
                if (point.k.equals(k)) {
                    V value = point.v;
                    if (point.last == null) {
                        point.v = null;
                    } else {
                        point.last.assignNext(point.next);
                    }
                    return value;
                }
                if (point.next == null) {
                    synchronized (this) {
                        if (point.next == null) {
                            return null;
                        } else {
                            point = point.next;
                        }
                    }
                }
            }
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            V v;
            int count = 0;
            while (true) {
                v = this.v;
                if (unsafe.compareAndSwapObject(this, vOffSet, v, value)) {
                    return v;
                }
                if (count > CAT_FAILED_THEN_SLEEP_THRESHOLD) {
                    sleep();
                    count = 0;
                }
                count++;
            }
        }

        public Node<K, V> put(K k, V v) {
            Node<K, V> point = this;
            while (true) {
                if (point.k.equals(k)) {
                    point.setValue(v);
                    return point;
                }
                if (point.next == null) {
                    synchronized (this) {
                        if (point.next == null) {
                            point.assignNext(new Node<>(k, v));
                            return point.next;
                        } else {
                            point = point.next;
                        }
                    }
                }
            }
        }

        private void sleep() {
            try {
                Thread.sleep(CAT_FAILED_THEN_SLEEP_TIME_FLOOR + (long) (Math.random() * CAT_FAILED_THEN_SLEEP_TIME_CAP));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        public Node<K, V> findValue(Object v) {
            return find(v, false);
        }

        private Node<K, V> find(Object o, boolean beKey) {
            Node<K, V> point = this;
            while (true) {
                if ((beKey && point.k.equals(o))
                        || (!beKey && point.v.equals(o))) {
                    return point;
                }
                if (point.next == null) {
                    synchronized (this) {
                        if (point.next == null) {
                            return null;
                        } else {
                            point = point.next;
                        }
                    }
                }
            }
        }

        public Node<K, V> findKey(Object k) {
            return find(k, true);
        }

    }

}