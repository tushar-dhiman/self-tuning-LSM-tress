package com.tushar.lsm;

import java.util.*;

public class SizeTieredCompaction implements CompactionStrategy {

    @Override
    public List<SSTable> compact(List<SSTable> tables) {
        if (tables.size() < 2) return tables;

        Map<String, String> merged = new TreeMap<>();
        for (SSTable table : tables) {
            merged.putAll(table.getData());
        }

        return List.of(new SSTable(merged));
    }
}
