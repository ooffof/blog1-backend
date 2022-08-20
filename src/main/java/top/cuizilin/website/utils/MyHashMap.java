package top.cuizilin.website.utils;

import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K,V> {

    public MyHashMap<K, V> putValue(K key, V value) {
        super.put(key, value);
        return this;
    }
}
