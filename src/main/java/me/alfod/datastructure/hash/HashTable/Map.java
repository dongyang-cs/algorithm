package me.alfod.datastructure.hash.HashTable;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface Map<Key, Value> {
    void add(Key key, Value value);

    Value get(Key key);

    void del(Key key);

    void set(Key key, Value value);
}
