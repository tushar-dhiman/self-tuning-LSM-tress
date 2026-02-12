package com.tushar.lsm;

import java.util.List;

public interface CompactionStrategy {
    List<SSTable> compact(List<SSTable> tables);
}
