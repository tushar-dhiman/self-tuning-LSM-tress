package com.tushar.lsm;

import java.util.Map;
import java.util.TreeMap;

public class MemTable {

    private final TreeMap<String, String> data = new TreeMap<>();
    private final int maxSize;

    public MemTable(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    public boolean isFull() {
        return data.size() >= maxSize;
    }

    public Map<String, String> flush() {
        Map<String, String> flushed = new TreeMap<>(data);
        data.clear();
        return flushed;
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }
}
