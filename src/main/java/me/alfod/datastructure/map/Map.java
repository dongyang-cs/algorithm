package me.alfod.datastructure.map;


public interface Map<Key, Value> {
    void add(Key key, Value value);

    Value get(Key key);

    void del(Key key);

    void set(Key key, Value value);
}
