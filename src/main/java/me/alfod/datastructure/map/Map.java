package me.alfod.datastructure.map;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface Map<K, V> {
    void add(K k, V v);

    V get(K k);

    void del(K k);

    void set(K k, V v);
}
