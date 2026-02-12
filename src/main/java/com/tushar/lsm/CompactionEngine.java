package com.tushar.lsm;

import java.util.List;

public class CompactionEngine {

    private CompactionStrategy strategy;

    public void setStrategy(CompactionStrategy strategy) {
        this.strategy = strategy;
    }

    public List<SSTable> compact(List<SSTable> tables) {
        return strategy.compact(tables);
    }
}
