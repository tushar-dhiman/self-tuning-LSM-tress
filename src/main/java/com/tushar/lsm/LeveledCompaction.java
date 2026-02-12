package com.tushar.lsm;

import java.util.*;

public class LeveledCompaction implements CompactionStrategy {

    @Override
    public List<SSTable> compact(List<SSTable> tables) {
        tables.sort(Comparator.comparingInt(SSTable::getAccessCount));

        Map<String, String> merged = new TreeMap<>();
        for (SSTable table : tables) {
            merged.putAll(table.getData());
        }

        return List.of(new SSTable(merged));
    }
}
