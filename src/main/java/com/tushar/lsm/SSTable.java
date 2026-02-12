package com.tushar.lsm;

import java.util.Map;

public class SSTable {

    private final Map<String, String> data;
    private StorageTier tier;
    private int accessCount = 0;

    public SSTable(Map<String, String> data) {
        this.data = data;
        this.tier = StorageTier.COLD;
    }

    public String get(String key) {
        if (data.containsKey(key)) {
            accessCount++;
        }
        return data.get(key);
    }

    public Map<String, String> getData() {
        return data;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public StorageTier getTier() {
        return tier;
    }

    public void setTier(StorageTier tier) {
        this.tier = tier;
    }
}
