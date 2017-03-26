package me.alfod.datastructure.hash.HashTable;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface HashOperate<Key, Value> {
    void add(Key key, Value value);

    Value get(Key key);

    void del(Key key);
}
