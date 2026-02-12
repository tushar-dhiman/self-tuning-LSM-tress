package com.tushar.lsm;

import java.util.*;

public class LSMTree {

    private final MemTable memTable;
    private final List<SSTable> ssTables = new ArrayList<>();
    private final WorkloadMonitor monitor = new WorkloadMonitor();
    private final CompactionEngine compactionEngine = new CompactionEngine();
    private final TierManager tierManager = new TierManager(3);

    public LSMTree(int memTableSize) {
        this.memTable = new MemTable(memTableSize);
    }

    public void put(String key, String value) {
        memTable.put(key, value);
        monitor.recordWrite();

        if (memTable.isFull()) {
            flush();
        }
    }

    public String get(String key) {
        monitor.recordRead();

        if (memTable.contains(key)) {
            return memTable.get(key);
        }

        for (SSTable table : ssTables) {
            String val = table.get(key);
            if (val != null) {
                tierManager.evaluate(table);
                return val;
            }
        }

        return null;
    }

    private void flush() {
        SSTable table = new SSTable(memTable.flush());
        ssTables.add(table);
        selfTuneCompaction();
    }

    private void selfTuneCompaction() {
        if (monitor.isWriteHeavy()) {
            compactionEngine.setStrategy(new SizeTieredCompaction());
        } else {
            compactionEngine.setStrategy(new LeveledCompaction());
        }

        if (ssTables.size() > 2) {
            List<SSTable> compacted = compactionEngine.compact(ssTables);
            ssTables.clear();
            ssTables.addAll(compacted);
        }

        monitor.reset();
    }

    public List<SSTable> getSSTables() {
        return ssTables;
    }
}
